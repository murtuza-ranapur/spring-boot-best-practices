package com.best.spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.best.spring.domain.Student;
import com.best.spring.dto.StudentDTO;
import com.best.spring.mapper.StudentMapper;
import com.best.spring.repository.StudentRepository;
import com.best.spring.service.impl.StudentServiceImpl;
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
public class StudentServiceTest {

  @Mock private StudentRepository studentRepositoryMock;

  @Mock private StudentMapper studentMapper;

  @InjectMocks private StudentServiceImpl studentServiceMock;

  @Test
  public void getAll_returnsListOfStudentDTO() {
    List<Student> students = new ArrayList<>();
    Student student1 = new Student();
    student1.setName("Murtuza");
    student1.setId(1L);
    student1.setSemester(2L);
    students.add(student1);

    Student student2 = new Student();
    student2.setName("Mubaraka");
    student2.setId(2L);
    student2.setSemester(3L);
    students.add(student2);

    Mockito.when(studentRepositoryMock.findAll()).thenReturn(students);

    List<StudentDTO> studentsDTO = new ArrayList<>();
    StudentDTO studentdto1 = new StudentDTO();
    studentdto1.setName("Murtuza");
    studentdto1.setId(1L);
    studentdto1.setSemester(2L);
    studentdto1.setYear(1L);
    studentsDTO.add(studentdto1);

    StudentDTO studentDto2 = new StudentDTO();
    studentDto2.setName("Mubaraka");
    studentDto2.setId(2L);
    studentDto2.setSemester(3L);
    studentDto2.setYear(2L);
    studentsDTO.add(studentDto2);

    Mockito.when(studentMapper.toDto(student1)).thenReturn(studentdto1);
    Mockito.when(studentMapper.toDto(student2)).thenReturn(studentDto2);

    List<StudentDTO> found = studentServiceMock.getAll();

    assertEquals(studentsDTO, found);
  }

  @Test
  public void get_givenExistingUserId_shouldReturnDTOOfId() {
    Student student1 = new Student();
    student1.setName("Murtuza");
    student1.setId(1L);
    student1.setSemester(2L);

    Optional<Student> opStudent = Optional.of(student1);

    Mockito.when(studentRepositoryMock.findById(1L)).thenReturn(opStudent);

    StudentDTO studentdto1 = new StudentDTO();
    studentdto1.setName("Murtuza");
    studentdto1.setId(1L);
    studentdto1.setSemester(2L);
    studentdto1.setYear(1L);

    Mockito.when(studentMapper.toDto(student1)).thenReturn(studentdto1);

    StudentDTO found = studentServiceMock.get(1L);

    assertEquals(studentdto1, found);
  }

  @Test
  public void add_newStudent_shouldReturnStudentWithGeneratedId() {
    Student student1 = new Student();
    student1.setName("Murtuza");
    student1.setSemester(2L);

    StudentDTO studentdto1 = new StudentDTO();
    studentdto1.setName("Murtuza");
    studentdto1.setSemester(2L);
    studentdto1.setYear(1L);

    Student student2 = new Student();
    student2.setName("Murtuza");
    student2.setId(1L);
    student2.setSemester(2L);

    StudentDTO studentdto2 = new StudentDTO();
    studentdto2.setName("Murtuza");
    studentdto2.setId(1L);
    studentdto2.setSemester(2L);
    studentdto2.setYear(1L);

    Mockito.when(studentMapper.toDto(student2)).thenReturn(studentdto2);
    Mockito.when(studentMapper.toModel(studentdto1)).thenReturn(student1);
    Mockito.when(studentRepositoryMock.save(student1)).thenReturn(student2);

    StudentDTO found = studentServiceMock.add(studentdto1);
    assertEquals(studentdto2, found);
  }

  @Test
  public void update_existingStudent_shouldReturnStudentWithUpdatedValue() {
    Student student1 = new Student();
    student1.setName("Murtuza1");
    student1.setId(1L);
    student1.setSemester(2L);

    StudentDTO studentdto1 = new StudentDTO();
    studentdto1.setName("Murtuza1");
    studentdto1.setId(1L);
    studentdto1.setSemester(2L);
    studentdto1.setYear(1L);

    Optional<Student> opStudent = Optional.of(student1);

    Mockito.when(studentMapper.toModel(studentdto1)).thenReturn(student1);
    Mockito.when(studentMapper.toDto(student1)).thenReturn(studentdto1);

    Mockito.when(studentRepositoryMock.findById(1L)).thenReturn(opStudent);
    Mockito.when(studentRepositoryMock.save(student1)).thenReturn(student1);

    StudentDTO found = studentServiceMock.update(studentdto1);
    assertEquals(studentdto1, found);
  }

  @Test
  public void delete_existingStudent() {
    Student student1 = new Student();
    student1.setName("Murtuza1");
    student1.setId(1L);
    student1.setSemester(2L);

    StudentDTO studentdto1 = new StudentDTO();
    studentdto1.setName("Murtuza1");
    studentdto1.setId(1L);
    studentdto1.setSemester(2L);
    studentdto1.setYear(1L);

    Optional<Student> opStudent = Optional.of(student1);

    Mockito.when(studentRepositoryMock.findById(1L)).thenReturn(opStudent);

    studentServiceMock.delete(1L);

    verify(studentRepositoryMock, times(1)).delete(student1);
  }
}
