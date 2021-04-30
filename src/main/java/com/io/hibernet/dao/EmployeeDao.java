package com.io.hibernet.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.io.hibernet.dto.Client;
import com.io.hibernet.dto.Employee;

@Component
public class EmployeeDao {

	@Value("#{ T(com.io.hibernet.factory.SessionCreate).getSessionFactory() }")
	private SessionFactory sessionFactory;

	public EmployeeDao() {
		super();
		System.out.println("in employeeDao");
	}

	public void addEmployee(Employee employee) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		session.save(employee);
		tx.commit();
		session.close();

	}

	public void removeEmployee(String employeeId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		Employee employee = session.get(Employee.class, employeeId);
		session.delete(employee);
		tx.commit();
		session.close();

	}

	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		List<Employee> employeeList = (List<Employee>) session.createQuery("from Employee").list();
		tx.commit();
		session.close();

		return employeeList;
	}

	public Employee getEmployeeById(String employeeId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		Employee emp = session.get(Employee.class, employeeId);
		tx.commit();
		session.close();

		return emp;
	}

	public void updateEmployee(Employee employee) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		session.update(employee);
		tx.commit();
		session.close();

	}

	public void addClientToEmployee(String employeeId, Client client) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		Employee employee=session.get(Employee.class,employeeId);
		employee.getClients().add(client);
		session.update(employee);
		tx.commit();
		session.close();
	}

	public void addClientToEmployeeForClient(String employeeId, Client client) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		Employee employee=session.get(Employee.class,employeeId);
		employee.getClients().add(client);
		session.update(employee);
		tx.commit();
		session.close();
		
	}

	public List<Client> getAllClientsUnderEmployee(String employeeId) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		Employee employee=session.get(Employee.class, employeeId);
		List<Client> clients=employee.getClients();
		tx.commit();
		
		session.close();
		
		return clients;
	}

}
