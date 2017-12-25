package com.employee.mgmt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.employee.mgmt.models.Employee;
import com.employee.mgmt.models.Project;

@Component
@Transactional
public class EmployeeDao implements IEmployeeDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllEmployees() {
		String hql = "FROM Employee as emp order by emp.empId";
//		return (List<Employee>)entityManager.createQuery(hql).getResultList();
		CriteriaQuery<String> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(String.class);
		
		Root<Employee> empRoot = criteriaQuery.from(Employee.class);
		
		criteriaQuery.select(empRoot.get("firstName").as(String.class));
//		criteriaQuery.where(builder.equal(studentRoot.get("age"),"22"));
		
		return entityManager.createQuery(criteriaQuery).getResultList();
		
	}
	@Override
	public Employee getEmployeeById(int id) {
		return (Employee) entityManager.find(Employee.class, id);
	}
	@Override
	public Employee getNthHigherSalaryEmployee(int n) {
		String hql = "FROM Employee as e order by e.salary desc";
		return (Employee) entityManager
				.createQuery(hql)
				.setFirstResult(n)
				.setMaxResults(1)
				.getSingleResult();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeesOnBench() {
		String hql = "FROM Employee as emp WHERE emp.prjId = ? order by emp.empId";
		return (List<Employee>)entityManager
				.createQuery(hql)
				.setParameter(1, 2)
				.getResultList();
	}
	
	public List<?> getEmployeesWithProjectDetails() {
		String hql = "FROM Project as proj inner join proj.employees as emp";
		return ( List<?>) entityManager.createQuery(hql).getResultList();
	}
	
	@Override
	public boolean addEmployee(Employee emp, int prjId) {
		Project project = entityManager.getReference(Project.class, prjId);
		emp.setProject(project);
		entityManager.persist(emp);
		return true;
		
	}
	@Override
	public void updateEmployee(Employee emp) {
		Employee oldEmp = getEmployeeById(emp.getEmpId());
		oldEmp.setSalary(emp.getSalary());
		entityManager.flush();
		
	}
	@Override
	public void deleteEmployee(int empId) {
		entityManager.remove(getEmployeeById(empId));
		
	}

}
