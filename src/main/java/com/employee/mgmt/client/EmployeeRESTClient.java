package com.employee.mgmt.client;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.employee.mgmt.models.Employee;
import com.employee.mgmt.models.Project;

public class EmployeeRESTClient {
	public void addEmployeeDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/springbootapp/user/employee/projects/1";
		Employee objEmployee = new Employee();
		objEmployee.setFirstName("Jugal");
		objEmployee.setLastName("P");
		objEmployee.setManager(8);
		objEmployee.setSalary(60000);
		
        HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(objEmployee, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());    	
    }
	
	public void updateEmployee() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/springbootapp/user/employee";
		Employee objEmployee = new Employee();
		objEmployee.setEmpId(9);
		objEmployee.setFirstName("Jugal");
		objEmployee.setLastName("P");
		objEmployee.setManager(8);
		objEmployee.setSalary(16);
		
        HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(objEmployee, headers);
        restTemplate.put(url, requestEntity); 	
    }
	
	public void getEmployeeById() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/springbootapp/user/employee/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Employee> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Employee.class, 4);
        Employee Employee = responseEntity.getBody();
        System.out.println("Id:"+Employee.getEmpId()+", First Name:"+Employee.getFirstName() + " Project "+ Employee.getProject().getProjectId());      
    }
	
    public void getNthHigherSalaryEmployee() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/springbootapp/user/employee/extra/{n}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Employee> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Employee.class, 2);
        Employee Employee = responseEntity.getBody();
        System.out.println("Id:"+Employee.getEmpId()+", First Name:"+Employee.getFirstName() + "Project "+ Employee.getProject().getProjectId()); 
    }
    
    public void getAllEmployees() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
            RestTemplate restTemplate = new RestTemplate();
    	String url = "http://localhost:8080/springbootapp/user/employee";
            HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
            ResponseEntity<String[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String[].class);
            String[] emp = responseEntity.getBody();
            for(String e : emp) {
                  System.out.println("Name:"+e);
            }
        }
    
    public void getEmployeesByProject() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
            RestTemplate restTemplate = new RestTemplate();
    	String url = "http://localhost:8080/springbootapp/user/employee/projects";
            HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
            @SuppressWarnings("unchecked")
            //TODO : Response is correct from controller but confused on how to provide the class name for List<?>
			ResponseEntity<List<?>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, (Class<List<?>>)(Class<?>)List.class);
            List<?> list = responseEntity.getBody();
            for(int i=0; i<list.size(); i++) {
            	Object[] row = (Object[]) list.get(i);
                 Project project = (Project) row[0];
                 Employee emp = (Employee) row[1];
                 System.out.println("EmpName " + emp.getFirstName() + " Project Name "+ project.getProjectName());
            }
	}
    
    public static void main(String args[]) {
    	EmployeeRESTClient util = new EmployeeRESTClient();
    	//EMPLOYEE
//    	util.getEmployeeById();
//    	util.getNthHigherSalaryEmployee();
    	util.getAllEmployees();
//    	util.addEmployeeDemo();
//    	util.updateEmployee();
    	util.getEmployeesByProject();
    	
    	//PROJECT
//    	util.addProject();
//    	util.deleteProject();
    }   
    
    
    /*public void getEmployeesByProject() {
    	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/springbootapp/user/employee/extra/{n}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        @SuppressWarnings("unchecked")
        ResponseEntity<List<Object[]>> responseEntity = (ResponseEntity<List<Object[]>>) restTemplate.exchange(url, HttpMethod.GET, requestEntity, List.class, 2);
        Employee Employee = responseEntity.getBody();
        
    	for(int i=0; i<list.size(); i++) {
			Object[] row = (Object[]) list.get(i);
			Project project = (Project)row[0];
			Employee employee = (Employee)row[1];
			System.out.println("CompId:"+project.getProjectId()+", CompName:"+ project.getProjectName()+
					   ", EmpId:"+ employee.getEmpId()+", EmpName:"+ employee.getFirstName());
		}
    }*/
    public void addProject() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        
		String url = "http://localhost:8080/springbootapp/projects";
		Project project = new Project();
		project.setProjectName("MEDHOST");
		
		
        HttpEntity<Project> requestEntity = new HttpEntity<Project>(project, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());    	
    }
    
    public void deleteProject() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        
		String url = "http://localhost:8080/springbootapp/projects/{id}";
		
		
        HttpEntity<Project> requestEntity = new HttpEntity<Project>(headers);	
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 2);    	
    }

}
