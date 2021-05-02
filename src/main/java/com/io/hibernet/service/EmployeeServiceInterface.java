package com.io.hibernet.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.io.hibernet.dto.Client;
import com.io.hibernet.dto.Employee;


public interface EmployeeServiceInterface {

	void addEmployee(Employee employee);

	void removeEmployee(String employeeId);

	List<Employee> showAllEmployee();

	Employee getEmployeeById(String employeeId);

	void updateEmployee(Employee employee);

	void addClientToEmployee(String employeeId, String clientId);

	void addClientToEmployeeForClient(String employeeId, Client client);

	List<Client> getAllClientsUnderEmployee(String employeeId);

	List<Client> getAllAssignableClients(String employeeId);

	List<Employee> getAllAssignableEmployees(List<String> employeeListIds);

	void removeClientFromEmployee(String employeeId, String clientId);

	void removeClientFromEmployeeForClient(String employeeId, Client client);
	
	 

}
