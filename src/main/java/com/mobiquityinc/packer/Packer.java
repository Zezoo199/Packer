package com.mobiquityinc.packer;

import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.service.DefaultPackerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Packer {
  static Logger log = LoggerFactory.getLogger(Packer.class);

  public static String pack(String filePath) throws APIException {
    DefaultPackerService localService = new DefaultPackerService();
    return localService.pack(filePath);
  }

  public static void main(String[] args) {
    try {
      ClassLoader classLoader = Packer.class.getClassLoader();
      System.out.println(pack(classLoader.getResource("sample.txt").getPath()));
    } catch (APIException e) {
      log.error("Exception during packing {}", e.getMessage());
    }
  }
}
