package com.best.spring.mapper;

import org.mapstruct.Mapper;

import com.best.spring.domain.Student;
import com.best.spring.dto.StudentDTO;

@Mapper
public interface StudentMapper {
	
	Student toModel(StudentDTO studentDto);
	
	StudentDTO toDto(Student student);
}
