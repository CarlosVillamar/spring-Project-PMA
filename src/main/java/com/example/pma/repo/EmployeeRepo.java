package com.example.pma.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.pma.dto.EmployeeProject;
import com.example.pma.entities.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {

	@Override
	List<Employee> findAll();
	
	@Query(nativeQuery=true,value="SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount "+
								  "FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id "
								  + "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC;")
	public List<EmployeeProject> employeeProjects();

}
