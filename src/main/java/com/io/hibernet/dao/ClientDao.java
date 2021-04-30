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
public class ClientDao {

	@Value("#{ T(com.io.hibernet.factory.SessionCreate).getSessionFactory() }")
	private SessionFactory sessionFactory;

	public ClientDao() {
		super();
		System.out.println("in ClientDao");
	}

	public void addClient(Client client) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();

		tx.begin();
		session.save(client);
		tx.commit();

		session.close();
	}

	public void removeClient(String clientId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();

		tx.begin();
		Client client = session.get(Client.class, clientId);
		session.delete(client);
		tx.commit();

		session.close();

	}

	@SuppressWarnings("unchecked")
	public List<Client> getAllClients() {

		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		List<Client> clientList = (List<Client>) session.createQuery("from Client").list();
		tx.commit();
		session.close();

		return clientList;
	}

	public Client getClientById(String clientId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();

		tx.begin();
		Client client = session.get(Client.class,clientId);
		tx.commit();
		session.close();
        
		return client;
	}

	public void updateClient(Client client) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		session.update(client);
		tx.commit();
		session.close();
	}

	public void addEmployeeToClientForEmployee(String clientId, Employee employee) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		Client client=session.get(Client.class,clientId);
		client.getEmployees().add(employee);
		session.update(client);
		tx.commit();
		session.close();
	}

	public void addEmployeeToClient(String clientId, Employee employee) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		Client client=session.get(Client.class,clientId);
		client.getEmployees().add(employee);
		session.update(client);
		tx.commit();
		session.close();
		
	}

	public List<Employee> getAllEmployeesUnderClient(String clientId) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		Client client=session.get(Client.class, clientId);
		List<Employee> employees=client.getEmployees();
		tx.commit();
		
		session.close();
		
		return employees;
		
	}

}