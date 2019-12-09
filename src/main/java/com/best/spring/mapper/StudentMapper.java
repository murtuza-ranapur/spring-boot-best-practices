package com.best.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.best.spring.domain.Student;
import com.best.spring.dto.StudentDTO;

@Mapper
public interface StudentMapper {
	
	StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
	
	Student toModel(StudentDTO studentDto);
	
	StudentDTO toDto(Student student);
}
