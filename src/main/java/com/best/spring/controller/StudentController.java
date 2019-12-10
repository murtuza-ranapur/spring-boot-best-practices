package com.best.spring.controller;

import com.best.spring.dto.StudentDTO;
import com.best.spring.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> getAllStudents(){
        return studentService.getAll();
    }

    @PostMapping
    public StudentDTO addStudent(@RequestBody StudentDTO studentDTO){
        return studentService.add(studentDTO);
    }

    @PutMapping
    public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO){
        return studentService.update(studentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(Long id){
        studentService.delete(id);
    }
}