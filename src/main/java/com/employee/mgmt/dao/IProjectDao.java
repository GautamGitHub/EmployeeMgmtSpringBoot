package com.employee.mgmt.dao;

import java.util.List;

import com.employee.mgmt.models.Project;

public interface IProjectDao {
	public List<Project> getAllProjects();
	public Project getProjectById(int prjId);
	
	public boolean addProject(Project prj);
	public void updateProject(Project prj);
	public void deleteProject(Integer prjId);
}
