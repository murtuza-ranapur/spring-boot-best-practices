package com.best.spring.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseRequestDto {
  private Long id;

  @NotNull
  @Pattern(regexp = "^[a-zA-Z\\s][\\sa-zA-Z]*$", message = "{pattern.alphaspace}")
  @Size(max = 100)
  private String name;

  @NotNull
  @Size(max = 10)
  private String code;
}
