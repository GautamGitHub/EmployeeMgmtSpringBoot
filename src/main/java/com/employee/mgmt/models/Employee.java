package com.employee.mgmt.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "EMPLOYEE")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "empId")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "empId", updatable = false, nullable = false)
	private int empId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "manager")
	private int manager;
	
	@Column(name = "salary")
	private double salary;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "projId")
	private Project project;
	
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
		
	public int getManager() {
		return manager;
	}
	public void setManager(int manager) {
		this.manager = manager;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	@Override
	public boolean equals(Object emp) {
		if(emp == this) return true;
		
		if(!(emp instanceof Employee)) return false;
		
		Employee other = (Employee) emp;
		
		return Objects.equals(empId, other.empId) && 
				Objects.equals(lastName, other.lastName);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(empId, lastName);
	}
	
	/*@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", manager="
				+ manager + "]";
	}*/
	
	
	
	

}
