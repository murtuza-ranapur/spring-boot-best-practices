package com.best.spring.exception;

import com.best.spring.utils.ErrorTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class CustomExceptionHandlingControllerAdvice {

  @Autowired private MessageSource messageSource;

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessageResponse<String> onEntityNotFoundException(EntityNotFoundException e) {
    String message =
        messageSource.getMessage(e.getMessage(), e.getParams(), LocaleContextHolder.getLocale());
    return new ErrorMessageResponse<>(ErrorTypes.INVALID_ARGUMENT, message);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessageResponse<String> onMissingParameterException(
      MissingServletRequestParameterException e) {
    String param = e.getParameterName();
    String message =
        messageSource.getMessage(
            "api.param.missing", new Object[] {param}, LocaleContextHolder.getLocale());
    return new ErrorMessageResponse<>(ErrorTypes.ARGUMENT_MISSING, message);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessageResponse<String> onTypeMismatchException(
      MethodArgumentTypeMismatchException e) {
    String message =
        messageSource.getMessage(
            "api.type.mismatch",
            new Object[] {e.getName(), e.getRequiredType().getName()},
            LocaleContextHolder.getLocale());
    return new ErrorMessageResponse<>(ErrorTypes.INVALID_ARGUMENT, message);
  }
}
