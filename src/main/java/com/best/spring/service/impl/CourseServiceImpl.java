package com.best.spring.service.impl;

import com.best.spring.domain.Course;
import com.best.spring.dto.CourseDTO;
import com.best.spring.dto.StudentDTO;
import com.best.spring.mapper.CourseMapper;
import com.best.spring.mapper.StudentMapper;
import com.best.spring.repository.CourseRepository;
import com.best.spring.search.CourseSearchService;
import com.best.spring.service.CourseService;
import com.best.spring.service.StudentService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CourseServiceImpl extends IdCheckingService<Course, Long> implements CourseService {

  private final CourseRepository courseRepository;
  private final CourseMapper mapper;
  private final StudentService studentService;
  private final StudentMapper studentMapper;
  private final CourseSearchService searchService;

  public CourseServiceImpl(
      CourseRepository courseRepository,
      CourseMapper mapper,
      StudentService studentService,
      StudentMapper studentMapper,
      CourseSearchService searchService) {
    super(courseRepository);
    this.courseRepository = courseRepository;
    this.mapper = mapper;
    this.studentService = studentService;
    this.studentMapper = studentMapper;
    this.searchService = searchService;
  }

  @Override
  public List<CourseDTO> getAll() {
    return courseRepository.findAll().stream().map(mapper::fromCourse).collect(Collectors.toList());
  }

  @Cacheable("course")
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

  @Override
  public StudentDTO addStudentToCourse(Long courseId, StudentDTO studentDTO) {
    CourseDTO courseDTO = get(courseId);
    studentDTO.setCourse(courseDTO);
    return studentService.add(studentDTO);
  }

  @Override
  public StudentDTO updateStudentInCourse(Long courseId, StudentDTO studentDTO) {
    CourseDTO courseDTO = new CourseDTO();
    courseDTO.setId(courseId);
    getStudentInCourse(courseId, studentDTO.getId());
    studentDTO.setCourse(courseDTO);
    return studentService.update(studentDTO);
  }

  @Override
  public List<StudentDTO> getStudentsInCourse(Long courseId) {
    Course course = getIfExistById(courseId);
    return course.getStudentList().stream().map(studentMapper::toDto).collect(Collectors.toList());
  }

  @Override
  public List<CourseDTO> search(String field, String value) {
    return searchService.searchCourses(field, value).stream()
        .map(mapper::fromCourse)
        .collect(Collectors.toList());
  }

  @Override
  public StudentDTO getStudentInCourse(Long courseId, Long studentId) {
    return studentService.findByStudentIdAndCourseId(studentId, courseId);
  }

  @Override
  public void deleteStudentInCourse(Long courseId, Long studentId) {
    getStudentInCourse(courseId, studentId);
    studentService.delete(studentId);
  }
}
