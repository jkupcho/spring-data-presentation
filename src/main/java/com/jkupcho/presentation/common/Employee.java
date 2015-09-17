package com.jkupcho.presentation.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="FIRST_NM")
	private String first;
	
	@Column(name="MIDDLE_NM")
	private String middle;
	
	@Column(name="LAST_NM")
	private String last;
	
	public Long getId() {
		return id;
	}
	public String getFirst() {
		return first;
	}
	public String getMiddle() {
		return middle;
	}
	public String getLast() {
		return last;
	}

}
