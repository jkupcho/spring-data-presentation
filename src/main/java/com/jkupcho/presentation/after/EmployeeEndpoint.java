package com.jkupcho.presentation.after;

import java.util.Collection;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.jkupcho.presentation.common.entity.Employee;

@RestResource(path="employee")
public interface EmployeeEndpoint extends PagingAndSortingRepository<Employee, Long> {
	
	Collection<Employee> findByFirstIgnoreCase(@Param("first") String first);

}