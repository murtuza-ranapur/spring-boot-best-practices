package com.best.spring.domain;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Student extends Autditable {

  @Id @GeneratedValue private Long id;

  private String name;

  private Long semester;

  @Setter(AccessLevel.NONE)
  private Long year;

  private Long batch;

  @Column(name = "class_section")
  private String group;

  @ManyToOne private Course course;

  public Long getSemester() {
    return semester;
  }

  public void setSemester(Long semester) {
    this.year = (long) Math.ceil(semester / 2D);
    this.semester = semester;
  }
}
