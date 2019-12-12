package com.best.spring.exception;

import com.best.spring.utils.ErrorTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageResponse<Message> {
  private ErrorTypes errorType;
  private Message message;
}
