package com.jkupcho.presentation.before.web;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkupcho.presentation.common.entity.Employee;

@Controller
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	@RequestMapping(value="/employee", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody ResponseEntity<Employee> create(@RequestBody Employee employee) {
		try {
			entityManager.persist(employee);
			return new ResponseEntity<>(employee, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@Transactional(readOnly=true)	
	@RequestMapping(value="/employee/{id}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ResponseEntity<Employee> create(@PathVariable("id") Long id) {
		return new ResponseEntity<Employee>(entityManager.find(Employee.class, id), HttpStatus.OK);
	}
	
	@Transactional(readOnly=true)
	@RequestMapping(value="/employee/findByFirst/{first}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ResponseEntity<List<Employee>> create(@PathVariable("first") String first) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
		Root<Employee> employee = query.from(Employee.class);
		
		query.select(employee);
		
		ParameterExpression<String> firstNameEquals = builder.parameter(String.class, "first");
		query.where(builder.equal(employee.get("first"), firstNameEquals));
		
		TypedQuery<Employee> finalQuery = entityManager.createQuery(query);
		finalQuery.setParameter("first", first);
		
		return new ResponseEntity<List<Employee>>(finalQuery.getResultList(), HttpStatus.OK);
	}
}
