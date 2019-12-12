package com.best.spring.mapper;

import com.best.spring.dto.StudentDTO;
import com.best.spring.dto.StudentRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentRequestMapper {

  @Mapping(source = "courseId", target = "course.id")
  StudentDTO toStudentDto(StudentRequestDto studentRequestDto);
}
