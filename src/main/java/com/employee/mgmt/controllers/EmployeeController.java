package com.employee.mgmt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.employee.mgmt.dao.IEmployeeDao;
import com.employee.mgmt.models.Employee;

@Controller
@RequestMapping("user")
public class EmployeeController {
	@Autowired
	private IEmployeeDao empDao;
	
	@GetMapping("employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) {
		Employee emp = empDao.getEmployeeById(id);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	
	@GetMapping("employee")
	public ResponseEntity<List<String>> getEmployees() {
		List<String> emps = empDao.getAllEmployees();
		return new ResponseEntity<List<String>>(emps,HttpStatus.OK);
	}
	
	@GetMapping("employee/extra/{n}")
	public ResponseEntity<Employee> getNthHigherSalaryEmployee(@PathVariable("n") Integer n) {
		Employee emp = empDao.getNthHigherSalaryEmployee(n);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	
	@GetMapping("employee/projects")
	public ResponseEntity<List<?>> getEmployeesByProject() {
		List<?> list = empDao.getEmployeesWithProjectDetails();
		return new ResponseEntity<List<?>>(list,HttpStatus.OK);
	}
	
	@PostMapping("employee/projects/{prjId}")
	public ResponseEntity<Void> addEmployee(@RequestBody Employee emp, @PathVariable("prjId") int prjId, UriComponentsBuilder builder) {
        boolean flag = empDao.addEmployee(emp, prjId);
        if (flag == false) {
	    return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/employee/{id}").buildAndExpand(emp.getEmpId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("employee")
	public ResponseEntity<Void> updateEmployee(@RequestBody Employee emp, UriComponentsBuilder builder) {
		empDao.updateEmployee(emp);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	
	@DeleteMapping("employee/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Integer empId) {
		empDao.deleteEmployee(empId);
		return new ResponseEntity<Void>(new HttpHeaders(),HttpStatus.OK);
	}

}
