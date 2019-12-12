package com.best.spring.exception;

public class EntityNotFoundException extends I18AbleException {
  public EntityNotFoundException(String key, Object... args) {
    super(key, args);
  }
}
