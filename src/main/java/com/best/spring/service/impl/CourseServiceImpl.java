package com.best.spring.service.impl;

import com.best.spring.domain.Course;
import com.best.spring.dto.CourseDTO;
import com.best.spring.mapper.CourseMapper;
import com.best.spring.repository.CourseRepository;
import com.best.spring.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl extends IdCheckingService<Course, CourseDTO, Long>
    implements CourseService {

  private final CourseRepository courseRepository;
  private final CourseMapper mapper;

  public CourseServiceImpl(CourseRepository courseRepository, CourseMapper mapper) {
    super(courseRepository);
    this.courseRepository = courseRepository;
    this.mapper = mapper;
  }

  @Override
  public List<CourseDTO> getAll() {
    return courseRepository.findAll().stream().map(mapper::fromCourse).collect(Collectors.toList());
  }

  @Override
  public CourseDTO get(Long id) {
    Course course = getIfExistById(id);
    return mapper.fromCourse(course);
  }

  @Override
  public CourseDTO add(CourseDTO courseDTO) {
    Course course = mapper.toCourse(courseDTO);
    course = courseRepository.save(course);
    return mapper.fromCourse(course);
  }

  @Override
  public CourseDTO update(CourseDTO courseDTO) {
    getIfExistById(courseDTO.getId());
    Course courseUpdated = mapper.toCourse(courseDTO);
    courseUpdated = courseRepository.save(courseUpdated);
    return mapper.fromCourse(courseUpdated);
  }

  @Override
  public void delete(Long id) {
    Course course = getIfExistById(id);
    courseRepository.delete(course);
  }
}
