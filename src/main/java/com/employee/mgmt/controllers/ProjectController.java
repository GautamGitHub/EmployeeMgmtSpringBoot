package com.employee.mgmt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.employee.mgmt.dao.IProjectDao;
import com.employee.mgmt.models.Project;

@Component
@RequestMapping("projects")
public class ProjectController {
	@Autowired
	private IProjectDao prjDao;
	
	@GetMapping()
	public ResponseEntity<List<Project>> getAllProjects() {
		List<Project> projects = prjDao.getAllProjects();
		return new ResponseEntity<List<Project>>(projects,HttpStatus.OK);
	}
	
	
	@PostMapping()
	public ResponseEntity<Void> addProject(@RequestBody Project prj, UriComponentsBuilder builder) {
        boolean flag = prjDao.addProject(prj);
        if (flag == false) {
	    return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/projects").buildAndExpand(prj.getProjectId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping()
	public ResponseEntity<Void> updateProject(@RequestBody Project prj, UriComponentsBuilder builder) {
		prjDao.updateProject(prj);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable("id") Integer prjId) {
		prjDao.deleteProject(prjId);
		return new ResponseEntity<Void>(new HttpHeaders(),HttpStatus.OK);
	}

}
