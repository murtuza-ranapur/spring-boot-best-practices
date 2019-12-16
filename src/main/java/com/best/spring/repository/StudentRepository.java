package com.best.spring.repository;

import com.best.spring.domain.Student;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository
    extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

  @Query(
      "SELECT student from Student student where student.id = :studentId and student.course.id = :courseId")
  Optional<Student> findByIdAndCourseId(Long studentId, Long courseId);
}
