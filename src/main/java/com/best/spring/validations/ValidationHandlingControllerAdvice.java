package com.best.spring.validations;

import com.best.spring.exception.ErrorMessageResponse;
import com.best.spring.utils.ErrorTypes;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandlingControllerAdvice {

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessageResponse<ValidationErrorResponse> onConstraintViolationException(
      ConstraintViolationException e) {
    ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
    for (ConstraintViolation violation : e.getConstraintViolations()) {
      validationErrorResponse
          .getErrors()
          .add(new Validation(violation.getPropertyPath().toString(), violation.getMessage()));
    }
    return new ErrorMessageResponse<>(ErrorTypes.VALIDATION, validationErrorResponse);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessageResponse<ValidationErrorResponse> onMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    ValidationErrorResponse error = new ValidationErrorResponse();
    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      error.getErrors().add(new Validation(fieldError.getField(), fieldError.getDefaultMessage()));
    }
    return new ErrorMessageResponse<>(ErrorTypes.VALIDATION, error);
  }
}
