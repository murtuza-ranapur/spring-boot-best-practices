package com.best.spring.service;

import com.best.spring.dto.PagedResponseDTO;
import com.best.spring.dto.StudentDTO;
import java.util.List;

public interface StudentService extends CrudService<StudentDTO, Long> {

  StudentDTO findByStudentIdAndCourseId(Long studentId, Long courseId);

  PagedResponseDTO<List<StudentDTO>> getAll(
      Integer pageNo, Integer pageSize, String sortBy, boolean isAscending, String search);
}
