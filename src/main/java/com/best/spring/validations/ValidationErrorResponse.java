package com.best.spring.validations;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorResponse {

  private List<Validation> validations = new ArrayList<>();
}
