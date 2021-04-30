package com.io.hibernet.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.io.hibernet.dto.Client;
import com.io.hibernet.dto.Employee;

@Component
public interface EmployeeServiceInterface {

	void addEmployee(Employee employee);

	void removeEmployee(String employeeId);

	List<Employee> showAllEmployee();

	Employee getEmployeeById(String employeeId);

	void updateEmployee(Employee employee);

	void addClientToEmployee(String employeeId, String clientId);

	void addClientToEmployeeForClient(String employeeId, Client client);

	List<Client> getAllClientsUnderEmployee(String employeeId);
	
	 

}
