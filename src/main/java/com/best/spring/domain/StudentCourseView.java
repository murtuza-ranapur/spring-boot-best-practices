package com.best.spring.domain;

import lombok.Getter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student_course_view")
@Immutable
@Getter
public class StudentCourseView {
  @Id private Long studentId;
  private String name;
  private String courseCode;
  private String courseName;
}
