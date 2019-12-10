package com.best.spring.mapper;

import com.best.spring.domain.Course;
import com.best.spring.dto.CourseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
  Course toCourse(CourseDTO courseDTO);

  CourseDTO fromCourse(Course course);
}
