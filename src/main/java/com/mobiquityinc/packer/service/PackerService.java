package com.mobiquityinc.packer.service;

import com.mobiquityinc.packer.exception.APIException;

public interface PackerService {
  String pack(String filePath) throws APIException;
}
