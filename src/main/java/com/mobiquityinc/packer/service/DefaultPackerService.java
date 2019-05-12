package com.mobiquityinc.packer.service;

import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.model.Paket;
import com.mobiquityinc.packer.model.Thing;
import com.mobiquityinc.packer.repository.PackageRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** DefaultService for Packing (Business logic is here) */
@Service
public class DefaultPackerService implements PackerService {
  private Logger log = LoggerFactory.getLogger(DefaultPackerService.class);

  /**
   * Method pack Validate File , then load into Repo then Process packages and clear
   *
   * @param filePath
   * @return String formatted output
   * @throws APIException
   */
  @Override
  public String pack(String filePath) throws APIException {
    try {
      log.info("Pre validate file {}", filePath);
      preValidateFile(filePath);
      log.info("Loading file {} to memory..", filePath);
      loadFileIntoRepository(filePath);
      log.info("Processing file...");
      return processPakets(PackageRepository.findAll());
    } finally {
      PackageRepository.findAll().clear();
    }
  }

  /**
   * Method to validate file before processing
   *
   * @param filePath
   * @throws APIException
   */
  private void preValidateFile(String filePath) throws APIException {
    try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
      lines.forEach(this::mapPaketFromLine);
    } catch (Exception e) {
      log.info("Validation Failed with error {} {}", e.getClass().getSimpleName(), e.getMessage());
      throw new APIException(
          "Pre Validation Failed For file  "
              + filePath
              + " Reason : "
              + e.getClass().getSimpleName()
              + " "
              + e.getMessage());
    }
  }

  /**
   * Method to file into static repository
   *
   * @param filePath
   * @throws APIException
   */
  private void loadFileIntoRepository(String filePath) throws APIException {
    try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
      lines.forEach(line -> PackageRepository.save(mapPaketFromLine(line)));
    } catch (IOException e) {
      throw new APIException(
          "File Not found after validation!!  "
              + filePath
              + " Reason : "
              + e.getClass().getSimpleName()
              + " "
              + e.getMessage());
    }
  }

  /**
   * Method to parase and map packet line
   *
   * @param line
   * @return Paket mapped
   */
  private Paket mapPaketFromLine(String line) {
    Paket paket = new Paket();
    String[] split = line.split(":");
    paket.setWeightLimit(Double.parseDouble(split[0].trim()));
    String thingsString = split[1];
    Arrays.asList(thingsString.split(" ")).stream()
        .filter(s -> !s.equals(""))
        .forEach(thing -> paket.getThings().add(mapThingFromString(thing)));
    return paket;
  }

  /**
   * Method to process all packets from repository
   *
   * @param pakets
   * @return
   */
  private String processPakets(List<Paket> pakets) {
    return pakets.stream().map(this::calculateOutputString).collect(Collectors.joining("\n"));
  }

  /**
   * Method to return the string output of a packet by first sorting the things and then applying
   * Greedy Algorithm on sorted list
   *
   * @param paket
   * @return output string for the packet delimetted by Comma
   */
  private String calculateOutputString(Paket paket) {
    double maxWeight = paket.getWeightLimit();
    ArrayList<Thing> addedThings = new ArrayList<>();

    Collections.sort(paket.getThings());

    while (maxWeight >= 0 && !paket.getThings().isEmpty()) {
      Thing candidate = paket.getThings().get(0);
      paket.getThings().remove(candidate);
      if (candidate.getWeight() < maxWeight) {
        addedThings.add(candidate);
        maxWeight -= candidate.getWeight();
      }
    }

    if (addedThings.isEmpty()) return "-";
    return addedThings.stream()
        .map(thing -> Long.toString(thing.getIndex()))
        .collect(Collectors.joining(","));
  }

  private Thing mapThingFromString(String thingString) {
    Thing thing = new Thing();
    thingString = thingString.replace("(", "").replace(")", "").replace("€", "");
    String[] split = thingString.split(",");
    thing.setIndex(Long.parseLong(split[0]));
    thing.setWeight(Double.parseDouble(split[1]));
    thing.setPrice(Integer.parseInt(split[2]));
    return thing;
  }
}
