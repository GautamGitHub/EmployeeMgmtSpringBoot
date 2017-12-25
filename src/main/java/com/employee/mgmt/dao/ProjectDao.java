package com.employee.mgmt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.employee.mgmt.models.Project;

@Component
@Transactional
public class ProjectDao implements IProjectDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getAllProjects() {
		String hql = "FROM PROJECT prj order by prj.pid";
		return (List<Project>)entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Project getProjectById(int prjId) {
		return entityManager.find(Project.class, prjId);
	}
	
	@Override
	public boolean addProject(Project prj) {
		entityManager.persist(prj);
		return true;
		
	}
	@Override
	public void updateProject(Project prj) {
		Project oldPrj = getProjectById(prj.getProjectId());
		oldPrj.setProjectName(prj.getProjectName());
		entityManager.flush();
		
	}
	@Override
	public void deleteProject(Integer prj) {
		entityManager.remove(getProjectById(prj));
		
	}
	
}
