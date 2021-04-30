package com.io.hibernet.service;

import java.util.List;

import com.io.hibernet.dto.Client;
import com.io.hibernet.dto.Employee;

public interface ClientServiceInterface {

	void addClient(Client client);

	void removeClient(String clientId);

	List<Client> showAllClient();

	Client getClientById(String clientId);

	void updateClient(Client client2);

	void addEmployeeToClientForEmployee(String clientId, Employee employee);

	void addEmployeeToClient(String clientId, String employeeId);

	List<Employee> getAllEmployeesUnderClient(String clientId);

}
