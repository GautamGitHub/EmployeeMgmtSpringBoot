package com.employee.mgmt.dao;

import java.util.List;

import com.employee.mgmt.models.Employee;

public interface IEmployeeDao {

	public List<String> getAllEmployees();
	public Employee getEmployeeById(int id);
	public Employee getNthHigherSalaryEmployee(int n);
	public List<Employee> getEmployeesOnBench();
	public List<?> getEmployeesWithProjectDetails();
	
	public boolean addEmployee(Employee emp, int prjId);
	public void updateEmployee(Employee emp);
	public void deleteEmployee(int empId);
	
}
