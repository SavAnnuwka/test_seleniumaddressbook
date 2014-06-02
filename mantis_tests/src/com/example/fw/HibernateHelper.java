package com.example.fw;

import java.util.logging.Logger;

import org.hibernate.Session;


public class HibernateHelper extends HelperBase {

	
	
	public HibernateHelper(ApplicationManager manager) {
		super(manager);
	}
	private Session beginSession() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		return session;
	}

	public String getUserId(String  login) {
		Session session = beginSession();
		return  session.createQuery("select id from User where login=?").setParameter(0, login).uniqueResult().toString();
	}


}
