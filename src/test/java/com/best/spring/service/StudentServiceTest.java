package com.best.spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.best.spring.domain.Student;
import com.best.spring.dto.StudentDTO;
import com.best.spring.repository.StudentRepository;
import com.best.spring.service.impl.StudentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

	@Mock
	private StudentRepository studentRepository;

	@InjectMocks
	private StudentServiceImpl studentService;

	@Test
	public void getAll_returnsListOfStudentDTO() {
		List<Student> students = new ArrayList<>();
		Student student1 = new Student();
		student1.setName("Murtuza");
		student1.setCourse("CS");
		student1.setId(1L);
		student1.setSem(2L);
		student1.setYear(1L);
		students.add(student1);

		Student student2 = new Student();
		student2.setName("Mubaraka");
		student2.setCourse("CS");
		student2.setId(2L);
		student2.setSem(3L);
		student2.setYear(2L);
		students.add(student2);

		Mockito.when(studentRepository.findAll()).thenReturn(students);

		List<StudentDTO> studentsDTO = new ArrayList<>();
		StudentDTO studentdto1 = new StudentDTO();
		studentdto1.setName("Murtuza");
		studentdto1.setCourse("CS");
		studentdto1.setId(1L);
		studentdto1.setSem(2L);
		studentdto1.setYear(1L);
		studentsDTO.add(studentdto1);

		StudentDTO studentDto2 = new StudentDTO();
		studentDto2.setName("Mubaraka");
		studentDto2.setCourse("CS");
		studentDto2.setId(2L);
		studentDto2.setSem(3L);
		studentDto2.setYear(2L);
		studentsDTO.add(studentDto2);
		List<StudentDTO> found = studentService.getAll();

		assertEquals(studentsDTO, found);
	}

	@Test
	public void get_givenExistingUserId_shouldReturnDTOOfId() {
		Student student1 = new Student();
		student1.setName("Murtuza");
		student1.setCourse("CS");
		student1.setId(1L);
		student1.setSem(2L);
		student1.setYear(1L);

		Optional<Student> opStudent = Optional.of(student1);

		Mockito.when(studentRepository.findById(1L)).thenReturn(opStudent);

		StudentDTO studentdto1 = new StudentDTO();
		studentdto1.setName("Murtuza");
		studentdto1.setCourse("CS");
		studentdto1.setId(1L);
		studentdto1.setSem(2L);
		studentdto1.setYear(1L);

		StudentDTO found = studentService.get(1L);

		assertEquals(studentdto1, found);
	}

	@Test
	public void get_givenNonExistentUserId_shouldThrowExeption() {

		Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> {
			studentService.get(1L);
		});
	}

	@Test
	public void add_newStudent_shouldReturnStudentWithGeneratedId() {
		Student student1 = new Student();
		student1.setName("Murtuza");
		student1.setCourse("CS");
		student1.setSem(2L);
		student1.setYear(1L);

		StudentDTO studentdto1 = new StudentDTO();
		studentdto1.setName("Murtuza");
		studentdto1.setCourse("CS");
		studentdto1.setSem(2L);
		studentdto1.setYear(1L);

		Student student2 = new Student();
		student2.setName("Murtuza");
		student2.setId(1L);
		student2.setCourse("CS");
		student2.setSem(2L);
		student2.setYear(1L);

		StudentDTO studentdto2 = new StudentDTO();
		studentdto2.setName("Murtuza");
		studentdto2.setId(1L);
		studentdto2.setCourse("CS");
		studentdto2.setSem(2L);
		studentdto2.setYear(1L);
		
		Mockito.when(studentRepository.save(student1)).thenReturn(student2);

		StudentDTO found = studentService.add(studentdto1);
		assertEquals(studentdto2, found);
	}

	@Test
	public void update_existingStudent_shouldReturnStudentWithUpdatedValue() {
		Student student1 = new Student();
		student1.setName("Murtuza1");
		student1.setId(1L);
		student1.setCourse("CS");
		student1.setSem(2L);
		student1.setYear(1L);

		StudentDTO studentdto1 = new StudentDTO();
		studentdto1.setName("Murtuza1");
		studentdto1.setId(1L);
		studentdto1.setCourse("CS");
		studentdto1.setSem(2L);
		studentdto1.setYear(1L);

		Optional<Student> opStudent = Optional.of(student1);
		
		Mockito.when(studentRepository.findById(1L)).thenReturn(opStudent);
		Mockito.when(studentRepository.save(student1)).thenReturn(student1);

		StudentDTO found = studentService.update(studentdto1);
		assertEquals(studentdto1, found);
	}
	
	@Test
	public void delete_existingStudent() {
		Student student1 = new Student();
		student1.setName("Murtuza1");
		student1.setId(1L);
		student1.setCourse("CS");
		student1.setSem(2L);
		student1.setYear(1L);

		StudentDTO studentdto1 = new StudentDTO();
		studentdto1.setName("Murtuza1");
		studentdto1.setId(1L);
		studentdto1.setCourse("CS");
		studentdto1.setSem(2L);
		studentdto1.setYear(1L);

		Optional<Student> opStudent = Optional.of(student1);
		
		Mockito.when(studentRepository.findById(1L)).thenReturn(opStudent);
		
	    studentService.delete(1L);
		
		verify(studentRepository, times(1)).delete(student1);
	}
}
