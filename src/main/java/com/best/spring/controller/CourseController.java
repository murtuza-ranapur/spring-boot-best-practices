package com.best.spring.controller;

import com.best.spring.dto.CourseDTO;
import com.best.spring.dto.StudentDTO;
import com.best.spring.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDTO> getAllCourses(){
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public CourseDTO getCourse(@PathVariable Long id){
        return courseService.get(id);
    }

    @GetMapping("/{courseId}/students")
    public List<StudentDTO> getAllStudentsInCourse(@PathVariable Long courseId){
        return courseService.getStudentsInCourse(courseId);
    }

    @GetMapping("/{courseId}/students/{studentId}")
    public StudentDTO getStudentInCourse(@PathVariable Long courseId,
                                        @PathVariable Long studentId){
        return courseService.getStudentInCourse(courseId, studentId);
    }

    @PostMapping
    public CourseDTO addCourse(@RequestBody CourseDTO courseDTO){
        return courseService.add(courseDTO);
    }

    @PostMapping("/{courseId}/students")
    public StudentDTO addStudentInCourse(@PathVariable Long courseId,
                                        @RequestBody StudentDTO studentDTO){
        return courseService.addStudentToCourse(courseId,studentDTO);
    }

    @PutMapping
    public CourseDTO updateCourse(@RequestBody CourseDTO courseDTO){
        return courseService.update(courseDTO);
    }

    @PutMapping("/{courseId}/students")
    public StudentDTO updateStudentInCourse(@PathVariable Long courseId,
                                           @RequestBody StudentDTO studentDTO){
        return courseService.updateStudentInCourse(courseId,studentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id){
        courseService.delete(id);
    }

    @DeleteMapping("/{courseId}/students/{studentId}")
    public void deleteStudentInCourse(@PathVariable Long courseId,
                                      @PathVariable Long studentId){
        courseService.deleteStudentInCourse(courseId,studentId);
    }
}
