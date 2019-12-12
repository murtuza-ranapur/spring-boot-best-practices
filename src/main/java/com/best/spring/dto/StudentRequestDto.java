package com.best.spring.dto;

import javax.validation.constraints.*;
import lombok.Data;

@Data
public class StudentRequestDto {

  private Long id;

  @NotNull
  @Pattern(regexp = "^[a-zA-Z\\s][\\sa-zA-Z]*$", message = "{pattern.alphaspace}")
  @Size(max = 100)
  private String name;

  @NotNull
  @Min(1)
  @Max(12)
  private Long semester;

  @NotNull
  @Min(1975)
  private Long batch;

  @NotNull
  @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9]*$", message = "{pattern.alphaneumericnospace}")
  @Size(max = 3)
  private String group;

  private Long courseId;
}
