package com.best.spring.service;

import com.best.spring.dto.CourseDTO;
import com.best.spring.dto.StudentDTO;
import java.util.List;

public interface CourseService extends CrudService<CourseDTO, Long> {

  StudentDTO addStudentToCourse(Long courseId, StudentDTO studentDTO);

  StudentDTO updateStudentInCourse(Long courseId, StudentDTO studentDTO);

  List<StudentDTO> getStudentsInCourse(Long courseId);

  List<CourseDTO> search(String field, String value);

  StudentDTO getStudentInCourse(Long courseId, Long studentId);

  void deleteStudentInCourse(Long courseId, Long studentId);
}
