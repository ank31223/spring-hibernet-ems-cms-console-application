package com.io.hibernet.controller;

import java.util.Scanner;

public interface ClientControllerInterface {
	
	public void setSc(Scanner sc);

	public void removeClient();

	public void addClient();

	public void updateClient();

	public void showAllClient();

	public void addEmployeeToClient();

	public void getEmployeesUnderClient();

	public void deleteEmpoyeeFromClient();

}
