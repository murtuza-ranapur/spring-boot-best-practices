package com.best.spring.controller;

import com.best.spring.dto.CourseDTO;
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

    @PostMapping
    public CourseDTO addCourse(@RequestBody CourseDTO courseDTO){
        return courseService.add(courseDTO);
    }

    @PutMapping
    public CourseDTO updateCourse(@RequestBody CourseDTO courseDTO){
        return courseService.update(courseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(Long id){
        courseService.delete(id);
    }
}
