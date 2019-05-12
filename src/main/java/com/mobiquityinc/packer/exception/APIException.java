package com.mobiquityinc.packer.exception;

/** API Exception to handle all exceptions and return descriptive message */
public class APIException extends Exception {
  public APIException() {
    super();
  }

  public APIException(String msg) {
    super(msg);
  }
}
