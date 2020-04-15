package com.best.spring.validations;

import com.best.spring.exception.ErrorMessageResponse;
import com.best.spring.utils.ErrorTypes;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandlingControllerAdvice {

  @Autowired private MessageSource messageSource;

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

  @ExceptionHandler({Exception.class, RuntimeException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessageResponse<String> onDefaultException(Exception e) {
    String message =
        messageSource.getMessage(
            "api.unkown", new Object[] {e.getMessage()}, LocaleContextHolder.getLocale());
    return new ErrorMessageResponse<>(ErrorTypes.UNKNOWN, message);
  }
}
