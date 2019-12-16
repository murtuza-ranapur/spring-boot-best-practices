package com.best.spring.mapper;

import com.best.spring.domain.StudentCourseView;
import com.best.spring.dto.StudentCourseViewDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentCourseViewMapper {
  StudentCourseViewDTO toDto(StudentCourseView studentCourseView);
}
