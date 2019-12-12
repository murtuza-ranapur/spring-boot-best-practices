package com.best.spring.validations;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ValidationHandlingControllerAdvice {

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ValidationErrorResponse onConstraintViolationException(ConstraintViolationException e) {
    ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
    for (ConstraintViolation violation : e.getConstraintViolations()) {
      validationErrorResponse
          .getValidations()
          .add(new Validation(violation.getPropertyPath().toString(), violation.getMessage()));
    }
    return validationErrorResponse;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    ValidationErrorResponse error = new ValidationErrorResponse();
    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      error
          .getValidations()
          .add(new Validation(fieldError.getField(), fieldError.getDefaultMessage()));
    }
    return error;
  }
}
