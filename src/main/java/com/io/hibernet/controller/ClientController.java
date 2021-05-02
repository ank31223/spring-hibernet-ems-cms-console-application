package com.io.hibernet.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.io.hibernet.dto.Client;
import com.io.hibernet.dto.Employee;
import com.io.hibernet.service.ClientService;
import com.io.hibernet.service.ClientServiceInterface;

@Component("client")
public class ClientController implements ClientControllerInterface {

	private ClientServiceInterface clientServiceInterface;
	private Scanner scanner;

//	public ClientController() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

	@Autowired
	public ClientController(ClientService clientService) {
		super();
		this.clientServiceInterface = clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientServiceInterface = clientService;
	}

	public void setSc(Scanner sc) {
		this.scanner = sc;
	}

	@Override
	public void addClient() {

		System.out.println("Enter the Clients Detaills to be added:- ");
		scanner.nextLine();

		System.out.println("Enter the name of Client");
		String clientName = scanner.nextLine();

		System.out.println("Enter the Address of Client");
		String clientAddress = scanner.nextLine();

		Client client = new Client();
		client.setClientName(clientName);
		client.setClientAddress(clientAddress);

		clientServiceInterface.addClient(client);

	}

	@Override
	public void removeClient() {
		System.out.println("enter the id of client you want to delete\n");
		scanner.nextLine();
		String clientId = scanner.nextLine();
		clientServiceInterface.removeClient(clientId);

	}

	@Override
	public void updateClient() {

		System.out.println("Enter id of client Whose details you want to update\n");
		scanner.nextLine();
		String clientId = scanner.nextLine();
		Client client = clientServiceInterface.getClientById(clientId);
		System.out.println(client);

		System.out.println("Enter the deatils to be updated\n Enter the name of client");
		String clientName = scanner.nextLine();

		System.out.println("Enter the Address of client");
		String clientAddress = scanner.nextLine();

		Client client2 = new Client();
		client2.setId(clientId);
		client2.setClientName(clientName);
		client2.setClientAddress(clientAddress);

		clientServiceInterface.updateClient(client2);

	}

	@Override
	public void showAllClient() {
		for (Client client : clientServiceInterface.showAllClient()) {
			System.out.println(client);
		}

	}

	@Override
	public void addEmployeeToClient() {
		scanner.nextLine();
		System.out.println("Enter the id of client under which you want to add employee");
		String clientId = scanner.nextLine();
		
		for (Employee employee : clientServiceInterface.getAllEmployeesUnderClient(clientId)) {
			System.out.println(employee);
		}
		
		
		System.out.println("......................................................................................................................................");

		for (Employee employee :clientServiceInterface.getAllAssignableEmployees(clientId)) {
			System.out.println(employee);
		}
		
		System.out.println("Enter the id of employee that is to be added under this client");
		String employeeId = scanner.nextLine();

		clientServiceInterface.addEmployeeToClient(clientId, employeeId);

	}

	@Override
	public void getEmployeesUnderClient() {

		scanner.nextLine();
		System.out.println("Enter id of client whose employees details you want to know");
		String clientId = scanner.nextLine();

		for (Employee employee : clientServiceInterface.getAllEmployeesUnderClient(clientId)) {
			System.out.println(employee);
		}

	}

	@Override
	public void deleteEmpoyeeFromClient() {
		
		scanner.nextLine();
		System.out.println("Enter the id of Client whose employees you want to remove from this client");
		String clientId = scanner.nextLine();
		
		for (Employee employee: clientServiceInterface.getAllEmployeesUnderClient(clientId)) {
			System.out.println(employee);
		}
		
		System.out.println("..............................................................................................................");
		
		System.out.println("Enter the id of employee whom you want to remove from this client");
		String employeeId = scanner.nextLine();
		
		clientServiceInterface.removeEmployeeFromClient(employeeId,clientId);

	}

}
