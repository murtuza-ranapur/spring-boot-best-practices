package com.best.spring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Student extends Autditable{

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String course;
	
	private Long year;
	
	private Long sem;
	
}
