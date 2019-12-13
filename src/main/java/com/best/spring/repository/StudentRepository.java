package com.best.spring.repository;

import com.best.spring.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository
    extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

  @Query(
      "SELECT student from Student student where student.id = :studentId and student.course.id = :courseId")
  Optional<Student> findByIdAndCourseId(Long studentId, Long courseId);
}
