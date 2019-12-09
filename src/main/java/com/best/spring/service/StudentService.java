package com.best.spring.service;

import java.util.List;

import com.best.spring.dto.StudentDTO;

public interface StudentService {
	
	List<StudentDTO> getAll();
	
	StudentDTO get(Long id);
	
	StudentDTO add(StudentDTO studentDTO);
	
	StudentDTO update(StudentDTO studentDTO);
	
	void delete(Long id);
	
}
