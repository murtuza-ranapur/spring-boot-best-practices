package com.best.spring.dto;

import lombok.Data;

@Data
public class StudentDTO {

  private Long id;

  private String name;

  private Long semester;

  private Long year;

  private Long batch;

  private String group;

  private CourseDTO course;
}
