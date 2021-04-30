package com.io.hibernet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.io.hibernet.commonutils.CommonUtils;
import com.io.hibernet.dao.ClientDao;
import com.io.hibernet.dto.Client;
import com.io.hibernet.dto.Employee;

@Component
public class ClientService implements ClientServiceInterface {
	private ClientDao clientDao;
	private EmployeeServiceInterface employeeServiceInterface;

	public ClientService() {
		super();
	}

	public ClientService(ClientDao clientDao, EmployeeService employeeService) {
		super();
		this.clientDao = clientDao;
		this.employeeServiceInterface = employeeService;
	}

	@Autowired
	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
		System.out.print("setting ClientDao");
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeServiceInterface = employeeService;
		System.out.print("setting employeeService");
	}

	@Override
	public void addClient(Client client) {
		client.setId(CommonUtils.getUUID());
		clientDao.addClient(client);
	}

	@Override
	public void removeClient(String clientId) {
		clientDao.removeClient(clientId);
	}

	@Override
	public List<Client> showAllClient() {
		return clientDao.getAllClients();
	}

	@Override
	public Client getClientById(String clientId) {
		return clientDao.getClientById(clientId);
	}

	@Override
	public void updateClient(Client client2) {
		clientDao.updateClient(client2);
		
	}

	@Override
	public void addEmployeeToClientForEmployee(String clientId, Employee employee) {
		clientDao.addEmployeeToClientForEmployee(clientId,employee);
		
	}

	@Override
	public void addEmployeeToClient(String clientId, String employeeId) {
		Employee employee = employeeServiceInterface.getEmployeeById(employeeId);
		Client client = getClientById(clientId);
		clientDao.addEmployeeToClient(clientId,employee);
		employeeServiceInterface.addClientToEmployeeForClient(employeeId,client);
		
	}

	@Override
	public List<Employee> getAllEmployeesUnderClient(String clientId) {
		return clientDao.getAllEmployeesUnderClient(clientId);
	}

}
