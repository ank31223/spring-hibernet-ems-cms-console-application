package com.io.hibernet.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Client {

	@Id
	private String id;
	private String clientName;
	private String clientAddress;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Employee> employees;

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(String id, String clientName, String clientAddress) {
		super();
		this.id = id;
		this.clientName = clientName;
		this.clientAddress = clientAddress;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", clientName=" + clientName + ", clientAddress=" + clientAddress + "]";
	}

}
