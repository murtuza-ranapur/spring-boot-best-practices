package com.best.spring.service.impl;

import com.best.spring.domain.Student;
import com.best.spring.dto.StudentDTO;
import com.best.spring.mapper.StudentMapper;
import com.best.spring.repository.StudentRepository;
import com.best.spring.service.StudentService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends IdCheckingService<Student, Long> implements StudentService {

  private final StudentRepository studentRepository;
  private final StudentMapper mapper;

  public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
    super(studentRepository);
    this.studentRepository = studentRepository;
    this.mapper = studentMapper;
  }

  @Override
  public List<StudentDTO> getAll() {
    return studentRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public StudentDTO get(Long id) {
    Student student = getIfExistById(id);
    return mapper.toDto(student);
  }

  @Override
  public StudentDTO add(StudentDTO studentDTO) {
    Student student = mapper.toModel(studentDTO);
    student = studentRepository.save(student);
    return mapper.toDto(student);
  }

  @Override
  public StudentDTO update(StudentDTO studentDTO) {
    getIfExistById(studentDTO.getId());
    Student studentUpdated = mapper.toModel(studentDTO);
    studentUpdated = studentRepository.save(studentUpdated);
    return mapper.toDto(studentUpdated);
  }

  @Override
  public void delete(Long id) {
    Student student = getIfExistById(id);
    studentRepository.delete(student);
  }

  @Override
  public StudentDTO findByStudentIdAndCourseId(Long studentId, Long courseId) {
    Student student =
        studentRepository
            .findByIdAndCourseId(studentId, courseId)
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        String.format(
                            "'Student' with id %s doesn't exist in 'Course' wiith id %s",
                            studentId, courseId)));
    return mapper.toDto(student);
  }
}
