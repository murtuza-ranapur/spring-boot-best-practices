package com.best.spring.mapper;

import com.best.spring.domain.Student;
import com.best.spring.dto.StudentDTO;
import org.mapstruct.Mapper;

@Mapper
public interface StudentMapper {

  Student toModel(StudentDTO studentDto);

  StudentDTO toDto(Student student);
}
