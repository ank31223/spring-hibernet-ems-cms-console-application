package com.io.hibernet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.io.hibernet.commonutils.CommonUtils;
import com.io.hibernet.dao.EmployeeDao;
import com.io.hibernet.dto.Client;
import com.io.hibernet.dto.Employee;

@Component
public class EmployeeService implements EmployeeServiceInterface {
	private EmployeeDao employeeDao;
	private ClientServiceInterface clientServiceInterface;

	public EmployeeService() {
		super();
	}

	public EmployeeService(EmployeeDao employeeDao) {
		super();
		this.employeeDao = employeeDao;

	}

	@Autowired
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
		System.out.print("setting employeeDao");
	}

	@Autowired
	public void setClientService(ClientService clientService) {
		this.clientServiceInterface = clientService;
		System.out.print("setting clientService");
	}

	@Override
	public void addEmployee(Employee employee) {
		employee.setId(CommonUtils.getUUID());
		employeeDao.addEmployee(employee);

	}

	@Override
	public void removeEmployee(String employeeId) {
		employeeDao.removeEmployee(employeeId);

	}

	@Override
	public List<Employee> showAllEmployee() {
		return employeeDao.getAllEmployees();
	}

	@Override
	public Employee getEmployeeById(String employeeId) {
		return employeeDao.getEmployeeById(employeeId);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeDao.updateEmployee(employee);

	}

	@Override
	public void addClientToEmployee(String employeeId, String clientId) {
		Employee employee = getEmployeeById(employeeId);
		Client client = clientServiceInterface.getClientById(clientId);
		employeeDao.addClientToEmployee(employeeId, client);
		clientServiceInterface.addEmployeeToClientForEmployee(clientId, employee);
	}

	@Override
	public void addClientToEmployeeForClient(String employeeId, Client client) {
		
		employeeDao.addClientToEmployeeForClient(employeeId,client);
	}

	@Override
	public List<Client> getAllClientsUnderEmployee(String employeeId) {
		return employeeDao.getAllClientsUnderEmployee(employeeId);
	}

}
