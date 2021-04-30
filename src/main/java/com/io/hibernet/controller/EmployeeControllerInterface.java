package com.io.hibernet.controller;

import java.util.Scanner;

public interface EmployeeControllerInterface {
	void setSc(Scanner sc);

	void addEmployee();

	void removeEmployee();

	void showAllEmployee();

	void getAllClientsUnderEmployee();

	void deletClientFromEmployee();

	void addClientToEmployee();

	void updateEmployee();

}
