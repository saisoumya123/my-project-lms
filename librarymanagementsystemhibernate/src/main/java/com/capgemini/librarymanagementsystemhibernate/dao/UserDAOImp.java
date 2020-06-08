package com.capgemini.librarymanagementsystemhibernate.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.capgemini.librarymanagementsystemhibernate.dto.UserBean;
import com.capgemini.librarymanagementsystemhibernate.exception.LMSException;

public class UserDAOImp implements UserDAO{
	EntityManagerFactory factory;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	@Override
	public boolean register(UserBean bean) {
		boolean isRegistered = false;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			transaction.begin();
			manager.persist(bean);
			transaction.commit();
			isRegistered = true;
			
		} catch (Exception e) {
			throw new LMSException("Invalid credentials");
		}
		
		return isRegistered;
	}

	@Override
	public UserBean auth(String email, String password) {
		
		try {
			factory=Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			UserBean b = manager.find(UserBean.class, email);
			b.setEmail(email);
			manager.persist(b);
			if(b.getPassword().equals(password)) {
				return b;
			}
			transaction.commit();
		
		} catch (Exception e) {
			throw new LMSException("Invalid Credentials");
		}
		manager.close();
		return null;
	}

}
