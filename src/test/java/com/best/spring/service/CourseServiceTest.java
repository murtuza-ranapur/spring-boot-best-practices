package com.best.spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.best.spring.domain.Course;
import com.best.spring.dto.CourseDTO;
import com.best.spring.mapper.CourseMapper;
import com.best.spring.repository.CourseRepository;
import com.best.spring.service.impl.CourseServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

  @Mock private CourseRepository courseRepositoryMock;

  @Mock private CourseMapper courseMapper;

  @InjectMocks private CourseServiceImpl courseServiceMock;

  @Test
  public void getAll_returnsListOfCourseDTO() {
    List<Course> courses = new ArrayList<>();
    Course course1 = new Course();
    course1.setName("Computer Science");
    course1.setId(1L);
    course1.setCode("CS-101");
    courses.add(course1);

    Course course2 = new Course();
    course2.setName("Mechanical Engineering");
    course2.setId(2L);
    course2.setCode("ME-876");
    courses.add(course2);

    Mockito.when(courseRepositoryMock.findAll()).thenReturn(courses);

    List<CourseDTO> coursesDTO = new ArrayList<>();
    CourseDTO coursedto1 = new CourseDTO();
    coursedto1.setName("Computer Science");
    coursedto1.setId(1L);
    coursedto1.setCode("CS-101");
    coursesDTO.add(coursedto1);

    CourseDTO courseDto2 = new CourseDTO();
    courseDto2.setName("Mechanical Engineering");
    courseDto2.setId(2L);
    courseDto2.setCode("ME-876");
    coursesDTO.add(courseDto2);

    Mockito.when(courseMapper.fromCourse(course1)).thenReturn(coursedto1);
    Mockito.when(courseMapper.fromCourse(course2)).thenReturn(courseDto2);

    List<CourseDTO> found = courseServiceMock.getAll();

    assertEquals(coursesDTO, found);
  }

  @Test
  public void get_givenExistingUserId_shouldReturnDTOOfId() {
    Course course1 = new Course();
    course1.setName("Computer Science");
    course1.setId(1L);
    course1.setCode("CS-101");

    Optional<Course> opCourse = Optional.of(course1);

    Mockito.when(courseRepositoryMock.findById(1L)).thenReturn(opCourse);

    CourseDTO coursedto1 = new CourseDTO();
    coursedto1.setName("Computer Science");
    coursedto1.setId(1L);
    coursedto1.setCode("CS-101");

    Mockito.when(courseMapper.fromCourse(course1)).thenReturn(coursedto1);

    CourseDTO found = courseServiceMock.get(1L);

    assertEquals(coursedto1, found);
  }

  @Test
  public void add_newCourse_shouldReturnCourseWithGeneratedId() {
    Course course1 = new Course();
    course1.setName("Computer Science");
    course1.setId(1L);
    course1.setCode("CS-101");

    CourseDTO coursedto1 = new CourseDTO();
    coursedto1.setName("Computer Science");
    coursedto1.setId(1L);
    coursedto1.setCode("CS-101");

    Course course2 = new Course();
    course2.setName("Mechanical Engineering");
    course2.setId(2L);
    course2.setCode("ME-876");

    CourseDTO courseDto2 = new CourseDTO();
    courseDto2.setName("Mechanical Engineering");
    courseDto2.setId(2L);
    courseDto2.setCode("ME-876");

    Mockito.when(courseMapper.fromCourse(course2)).thenReturn(courseDto2);
    Mockito.when(courseMapper.toCourse(coursedto1)).thenReturn(course1);

    Mockito.when(courseRepositoryMock.save(course1)).thenReturn(course2);

    CourseDTO found = courseServiceMock.add(coursedto1);
    assertEquals(courseDto2, found);
  }

  @Test
  public void update_existingCourse_shouldReturnCourseWithUpdatedValue() {
    Course course1 = new Course();
    course1.setName("Computer Science 2");
    course1.setId(1L);
    course1.setCode("CS-101");

    CourseDTO coursedto1 = new CourseDTO();
    coursedto1.setName("Computer Science 2");
    coursedto1.setId(1L);
    coursedto1.setCode("CS-101");

    Optional<Course> opCourse = Optional.of(course1);

    Mockito.when(courseMapper.toCourse(coursedto1)).thenReturn(course1);
    Mockito.when(courseMapper.fromCourse(course1)).thenReturn(coursedto1);

    Mockito.when(courseRepositoryMock.findById(1L)).thenReturn(opCourse);
    Mockito.when(courseRepositoryMock.save(course1)).thenReturn(course1);

    CourseDTO found = courseServiceMock.update(coursedto1);
    assertEquals(coursedto1, found);
  }

  @Test
  public void delete_existingCourse() {
    Course course1 = new Course();
    course1.setName("Computer Science 2");
    course1.setId(1L);
    course1.setCode("CS-101");

    CourseDTO coursedto1 = new CourseDTO();
    coursedto1.setName("Computer Science 2");
    coursedto1.setId(1L);
    coursedto1.setCode("CS-101");

    Optional<Course> opCourse = Optional.of(course1);

    Mockito.when(courseRepositoryMock.findById(1L)).thenReturn(opCourse);

    courseServiceMock.delete(1L);

    verify(courseRepositoryMock, times(1)).delete(course1);
  }
}
