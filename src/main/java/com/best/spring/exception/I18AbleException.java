package com.best.spring.exception;

import lombok.Data;

@Data
public class I18AbleException extends RuntimeException {

  protected Object[] params;

  public I18AbleException(String key, Object... args) {
    super(key);
    params = args;
  }
}
