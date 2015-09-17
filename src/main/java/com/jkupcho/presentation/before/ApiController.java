package com.jkupcho.presentation.before;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkupcho.presentation.common.Employee;

@Controller
@RequestMapping("/api")
@Transactional(readOnly=true)	
public class ApiController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@RequestMapping(value="/employee", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody ResponseEntity<Employee> create(@RequestBody Employee employee) {
		try {
			if (employee.getId() != null) {
				entityManager.persist(employee);
			} else {
				employee = entityManager.merge(employee);
			}
			return new ResponseEntity<>(employee, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/employees", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ResponseEntity<List<Employee>> findAll() {
		return new ResponseEntity<List<Employee>>(entityManager.createQuery("select emp from Employee emp", Employee.class).getResultList(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/employee/{id}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ResponseEntity<Employee> create(@PathVariable("id") Long id) {
		return new ResponseEntity<Employee>(entityManager.find(Employee.class, id), HttpStatus.OK);
	}
	
	@RequestMapping(value="/employee/findByFirst/{first}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ResponseEntity<List<Employee>> create(@PathVariable("first") String first) {
		TypedQuery<Employee> query = entityManager.createQuery("select emp from Employee emp where UPPER(emp.first) = ?1", Employee.class);
		query.setParameter(1, first.toUpperCase());
		
		return new ResponseEntity<List<Employee>>(query.getResultList(), HttpStatus.OK);
	}
}
