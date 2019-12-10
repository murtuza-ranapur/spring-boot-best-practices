package com.best.spring.service;

import com.best.spring.dto.StudentDTO;
import java.util.List;

public interface StudentService {

  List<StudentDTO> getAll();

  StudentDTO get(Long id);

  StudentDTO add(StudentDTO studentDTO);

  StudentDTO update(StudentDTO studentDTO);

  void delete(Long id);
}
