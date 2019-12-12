package com.best.spring.mapper;

import com.best.spring.dto.CourseDTO;
import com.best.spring.dto.CourseRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseRequestMapper {
  CourseDTO toCourseDto(CourseRequestDto courseRequestDto);
}
