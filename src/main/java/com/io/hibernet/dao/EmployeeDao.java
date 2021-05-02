package com.io.hibernet.dao;

import java.util.List;

import javax.persistence.Query;

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
		Employee employee = (Employee)session.get(Employee.class, employeeId);
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
		Employee emp = (Employee)session.get(Employee.class, employeeId);
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
		Employee employee=(Employee)session.get(Employee.class,employeeId);
		employee.getClients().add(client);
		tx.commit();
		session.clear();
	}

	public void addClientToEmployeeForClient(String employeeId, Client client) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		Employee employee=(Employee)session.get(Employee.class,employeeId);
		System.out.println(employee);

		employee.getClients().add(client);
		tx.commit();
		session.close();
		
	}

	public List<Client> getAllClientsUnderEmployee(String employeeId) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		Employee employee=(Employee)session.get(Employee.class, employeeId);
		List<Client> clients=employee.getClients();
		tx.commit();
		
		session.close();
		
		return clients;
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getAllAssignableEmployees(List<String> employeeListIds) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();

		List<Employee> list;
		if (employeeListIds.size() != 0) {
			Query q = session.createQuery("from Employee where id not in (:employeeListIds) ");
			q.setParameter("employeeListIds", employeeListIds);
			list = q.getResultList();
		} else {
			System.out.print("Ankit.............");
			list = (List<Employee>)session.createQuery("from Employee").list();
		}

		tx.commit();
		session.close();

		return list;
	}

	public void removeClientFromEmployee(String employeeId, Client client) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		Employee employee=(Employee)session.get(Employee.class,employeeId);
		System.out.println(employee);
		System.out.println(client);
		int i=0;
		for (Client client2 : employee.getClients()) {
			if(client2.getId().equalsIgnoreCase(client.getId())) {
				employee.getClients().remove(i);
				break;
			}
			i++;
		}
		System.out.println(employee);
		session.update(employee);
		tx.commit();
		session.close();
		
	}

	public void removeClientFromEmployeeForClient(String employeeId, Client client) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		Employee employee=(Employee)session.get(Employee.class,employeeId);
		int i=0;
		for (Client client2 : employee.getClients()) {
			if(client2.getId().equalsIgnoreCase(client.getId())) {
				employee.getClients().remove(i);
				break;
			}
			i++;
		}
		session.update(employee);
		System.out.println(employee);
		tx.commit();
		session.close();
		
	}

}
