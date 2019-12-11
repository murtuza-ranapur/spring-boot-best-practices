package com.best.spring.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class StudentRequestDto {

    private Long id;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z\\s][\\sa-zA-Z]*$")
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
    @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9]*$")
    @Size(max = 3)
    private String group;

    private Long courseId;
}
