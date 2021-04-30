package com.io.hibernet.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionCreate {

	private static SessionFactory sessionFactory;

	private SessionCreate() {

	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			Configuration con = new Configuration().configure();
			return con.buildSessionFactory();
		}
		return sessionFactory;
	}

}
