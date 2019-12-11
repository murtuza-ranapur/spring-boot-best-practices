package com.best.spring.service;

import com.best.spring.dto.StudentDTO;

public interface StudentService extends CrudService<StudentDTO, Long> {

    StudentDTO findByStudentIdAndCourseId(Long studentId, Long courseId);

}
