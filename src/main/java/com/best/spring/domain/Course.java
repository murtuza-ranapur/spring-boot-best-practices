package com.best.spring.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

@Data
@Entity
@Indexed(index = "course")
@EqualsAndHashCode(callSuper = false)
public class Course extends Autditable {
  @Id @GeneratedValue private Long id;

  @Field private String name;

  @Column(unique = true)
  private String code;

  @OneToMany(mappedBy = "course")
  @IndexedEmbedded
  private List<Student> studentList;

  public Course() {
    this.studentList = new ArrayList<>();
  }

  public void addStudent(Student student) {
    student.setCourse(this);
    studentList.add(student);
  }

  public void removeStudent(Student student) {
    student.setCourse(null);
    studentList.remove(student);
  }
}
