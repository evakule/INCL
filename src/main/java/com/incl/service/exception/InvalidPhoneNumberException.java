package com.incl.service.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidPhoneNumberException extends Exception {
  
  public InvalidPhoneNumberException(String message) {
    super(message);
  }
}
