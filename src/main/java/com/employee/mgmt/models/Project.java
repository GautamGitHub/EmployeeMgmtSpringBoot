package com.employee.mgmt.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "PROJECT")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "projectId")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "projectId")
	private int projectId;
	
	@Column(name = "project_name")
	private String projectName;
	
	@OneToMany(
			mappedBy = "project",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	private List<Employee> employees = new ArrayList<Employee>();
	


	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Override
	public boolean equals(Object project) {
		if(project == this) return true;
		
		if(!(project instanceof Project)) return false;
		
		Project other = (Project) project;
		return Objects.equals(projectId, other.projectId);
	}
	
	@Override 
	public int hashCode() {
		return Objects.hash(projectId, projectName);
	}

	/*@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + "]";
	}*/
	
	

}
