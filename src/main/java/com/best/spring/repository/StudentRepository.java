package com.best.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.best.spring.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
