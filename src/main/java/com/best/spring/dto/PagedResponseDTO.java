package com.best.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagedResponseDTO<T> {
  private Long total;
  private Integer pages;
  private Integer current;
  private T data;
}
