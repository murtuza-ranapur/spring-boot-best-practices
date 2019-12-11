package com.best.spring.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CourseRequestDto {
    private Long id;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z\\s][\\sa-zA-Z]*$")
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 10)
    private String code;
}
