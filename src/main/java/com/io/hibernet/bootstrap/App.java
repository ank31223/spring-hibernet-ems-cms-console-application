package com.io.hibernet.bootstrap;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.io.hibernet.controller.ClientController;
import com.io.hibernet.controller.ClientControllerInterface;
import com.io.hibernet.controller.EmployeeController;
import com.io.hibernet.controller.EmployeeControllerInterface;
import com.io.hibernet.javaconfiguration.JavaConfig;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);

		ClientControllerInterface clientControllerInterface = context.getBean("client", ClientController.class);
		EmployeeControllerInterface employeeControllerInterface = context.getBean("employeeController",
				EmployeeController.class);

		Scanner scanner = new Scanner(System.in);

		clientControllerInterface.setSc(scanner);
		employeeControllerInterface.setSc(scanner);

		while (true) {
			System.out.print("1.Client Management System \n");
			System.out.print("2.Employee Management System\n");
			int choice = scanner.nextInt();
			if (choice == 1) {
				while (true) {
					System.out.print(
							" Enter the below choices to fetch/update Client \n 1.addClient\n 2.deleteClientByCompanyName\n 3.updateClientByCompanyName\n 4.showAllClientsDetails \n 5.add Employee to the client \n 6.get details of working employees under clients \n 7.remove empoyee from the client \n to the client for exit=-1 \n");
					choice = scanner.nextInt();
					if (choice == -1) {
						break;
					}

					switch (choice) {
					case 1:
						clientControllerInterface.addClient();
						break;
					case 2:
						clientControllerInterface.removeClient();
						break;
					case 3:
						clientControllerInterface.updateClient();
						break;
					case 4:
						clientControllerInterface.showAllClient();
						break;
					case 5:
						clientControllerInterface.addEmployeeToClient();
						break;
					case 6:
						clientControllerInterface.getEmployeesUnderClient();
						break;
					case 7:
						clientControllerInterface.deleteEmpoyeeFromClient();
						break;
					}
				}

			}
			if (choice == 2) {
				while (true) {
					System.out.print(
							"\n Enter the below choices to fetch/update Employee\n 1.addEmployee\n 2.deleteEmployee\n 3.updateEmployee\n 4.showAllEmployees \n 5.add clients to the employee \n 6.show all clients under this employeess \n7.delete Client from Employee \n for exit=-1 \n");
					choice = scanner.nextInt();
					if (choice == -1) {
						break;
					}

					switch (choice) {
					case 1:
						try {
							employeeControllerInterface.addEmployee();
						} catch (Exception e) {
							System.out.print("the error is " + e);
						}
						break;
					case 2:
						employeeControllerInterface.removeEmployee();
						break;
					case 3:
						employeeControllerInterface.updateEmployee();
						break;
					case 4:
						employeeControllerInterface.showAllEmployee();
						break;
					case 5:
						employeeControllerInterface.addClientToEmployee();
						break;
					case 6:
						employeeControllerInterface.getAllClientsUnderEmployee();
						break;
					case 7:
						employeeControllerInterface.deletClientFromEmployee();
						break;
					}
				}

			}

			context.close();

		}

	}

}
