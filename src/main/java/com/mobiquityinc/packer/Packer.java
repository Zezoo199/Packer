package com.mobiquityinc.packer;

import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.service.DefaultPackerService;
import com.mobiquityinc.packer.service.PackerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Packer {

  public static String pack(String filePath) throws APIException {
    PackerService localService = new DefaultPackerService();
    return localService.pack(filePath);
  }

  public static void main(String[] args) {
    // Un comment to run file from resources
    /* try {
      ClassLoader classLoader = Packer.class.getClassLoader();
      System.out.println(pack(classLoader.getResource("sample.txt").getPath()));
    } catch (APIException e) {
      log.error("Exception during packing {}", e.getMessage());
    }*/
  }
}
