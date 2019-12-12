package com.best.spring.validations;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ValidationErrorResponse {

  private List<Validation> errors = new ArrayList<>();
}
