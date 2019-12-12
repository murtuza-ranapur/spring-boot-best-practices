package com.best.spring.controller;

import com.best.spring.dto.PagedResponseDTO;
import com.best.spring.dto.StudentDTO;
import com.best.spring.dto.StudentRequestDto;
import com.best.spring.mapper.StudentRequestMapper;
import com.best.spring.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService;
  private final StudentRequestMapper studentMapper;

  public StudentController(StudentService studentService, StudentRequestMapper studentMapper) {
    this.studentService = studentService;
    this.studentMapper = studentMapper;
  }

  @GetMapping
  public PagedResponseDTO<List<StudentDTO>> getAllStudents(@RequestParam(defaultValue = "0") Integer pageNo,
                                                          @RequestParam(defaultValue = "2") Integer pageSize,
                                                          @RequestParam(defaultValue = "id") String sortBy,
                                                          @RequestParam(defaultValue = "true") boolean isAscending) {
    return studentService.getAll(pageNo, pageSize, sortBy, isAscending);
  }

  @PostMapping
  public StudentDTO addStudent(@RequestBody @Valid StudentRequestDto studentDTO) {
    return studentService.add(studentMapper.toStudentDto(studentDTO));
  }

  @PutMapping
  public StudentDTO updateStudent(@RequestBody @Valid StudentRequestDto studentDTO) {
    return studentService.update(studentMapper.toStudentDto(studentDTO));
  }

  @DeleteMapping("/{id}")
  public void deleteStudent(@PathVariable Long id) {
    studentService.delete(id);
  }
}
