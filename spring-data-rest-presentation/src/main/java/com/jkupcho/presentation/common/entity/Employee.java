package com.jkupcho.presentation.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="FIRST_NM")
	private String first;
	@Column(name="LAST_NM")
	private String last;
	
	public Long getId() {
		return id;
	}
	public String getFirst() {
		return first;
	}
	public String getLast() {
		return last;
	}

}
