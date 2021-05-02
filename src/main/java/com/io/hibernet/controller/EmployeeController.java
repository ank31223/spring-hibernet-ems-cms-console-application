package com.io.hibernet.controller;

import java.util.Scanner;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.io.hibernet.dto.Client;
import com.io.hibernet.dto.Employee;
import com.io.hibernet.service.EmployeeService;
import com.io.hibernet.service.EmployeeServiceInterface;

@Component("employeeController")
public class EmployeeController implements EmployeeControllerInterface {
	private EmployeeServiceInterface employeeServiceInterface;
	private Scanner scanner;

//	public EmployeeController() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeServiceInterface = employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeServiceInterface = employeeService;
	}

	public Scanner getSc() {
		return scanner;
	}

	public void setSc(Scanner sc) {
		this.scanner = sc;
	}

	@Override
	public void addEmployee() {
		Employee employee = new Employee();
		System.out.println("Enter the Employee Detaills to be added:- ");
		System.out.println("Enter the Name of Employee");
		scanner.nextLine();
		String name = scanner.nextLine();

		employee.setName(name);
		System.out.println("Enter the gender of Employee");
		employee.setGender(scanner.nextLine());
		System.out.println("Enter the Age of Employee");
		employee.setAge(scanner.nextInt());

		System.out.println("Enter the emailId of Employee");
		String email = scanner.next();
		String regex = "^[a-zA-Z0-9]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		int i = 0;

		if (pattern.matcher(email).matches()) {
			employee.setEmail(email);
		} else {

			while (i != 3) {
				System.out.println("Enter the valid emailId of Employee");
				email = scanner.next();
				if (pattern.matcher(email).matches()) {
					employee.setEmail(email);
					break;
				}
				i++;
			}
		}
		if (i != 3) {
			employeeServiceInterface.addEmployee(employee);
			System.out.print("User added Successfully");
		} else {
			System.out.print("User not able to add employee");
		}

	}

	@Override
	public void removeEmployee() {
		System.out.println("enter the id of employee you want to delete\n");
		scanner.nextLine();
		String employeeId = scanner.nextLine();
		employeeServiceInterface.removeEmployee(employeeId);

	}

	@Override
	public void showAllEmployee() {
		for (Employee emp : employeeServiceInterface.showAllEmployee()) {
			System.out.println(emp);
		}

	}

	@Override
	public void updateEmployee() {
		System.out.println("Enter id of employee Whose details you want to update\n");
		scanner.nextLine();
		String employeeId = scanner.nextLine();
		Employee emp = employeeServiceInterface.getEmployeeById(employeeId);
		System.out.println(emp);

		Employee employee = new Employee();

		System.out.println("Enter the deatils to be updated\n Enter the name of employee");
		String employeeName = scanner.nextLine();

		System.out.println("Enter the age of employee");
		int employeeAge = scanner.nextInt();

		scanner.nextLine();

		System.out.println("Enter the gender of employee\n");
		String employeeGender = scanner.nextLine();

		System.out.println("Enter the email of employee\n");
		String employeeEmail = scanner.nextLine();

		employee.setId(employeeId);
		employee.setName(employeeName);
		employee.setAge(employeeAge);
		employee.setGender(employeeGender);
		employee.setEmail(employeeEmail);

		employeeServiceInterface.updateEmployee(employee);

	}

	@Override
	public void getAllClientsUnderEmployee() {

		scanner.nextLine();
		System.out.println("Enter id of employee whose clients you want to know");
		String employeeId=scanner.nextLine();
		
		for (Client client : employeeServiceInterface.getAllClientsUnderEmployee(employeeId)) {
			System.out.println(client);
		}
		
	}

	@Override
	public void deletClientFromEmployee() {
		
		scanner.nextLine();
		System.out.println("Enter the id of Employee whose clients you want to delete");
		String employeeId = scanner.nextLine();
		
		for (Client client : employeeServiceInterface.getAllClientsUnderEmployee(employeeId)) {
			System.out.println(client);
		}
		
		System.out.println("..............................................................................................................");
		
		System.out.println("Enter the id of client whom you want to remove from this employee");
		String clientId = scanner.nextLine();
		
		employeeServiceInterface.removeClientFromEmployee(employeeId,clientId);
		

		
		
	}

	@Override
	public void addClientToEmployee() {
		scanner.nextLine();
		System.out.println("Enter the id of employee under which you want to add client");
		String employeeId = scanner.nextLine();

		
		
		for (Client client : employeeServiceInterface.getAllClientsUnderEmployee(employeeId)) {
			System.out.println(client);
		}
		
		System.out.println("......................................................................................................");
		
		
		for (Client client: employeeServiceInterface.getAllAssignableClients(employeeId)) {
			System.out.println(client);
		}
//
		System.out.println("Enter the id of client that is to be added");
		String clientId = scanner.nextLine();
		
		employeeServiceInterface.addClientToEmployee(employeeId, clientId);

	}

}
