package com.best.spring.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StudentDTO {
	
	private Long id;
	
	private String name;
	
	private String course;
	
	private Long year;
	
	private Long sem;
	
    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

}
