package com.capgemini.librarymanagementsystemhibernate.factory;

import com.capgemini.librarymanagementsystemhibernate.dao.UserDAO;
import com.capgemini.librarymanagementsystemhibernate.dao.UserDAOImp;
import com.capgemini.librarymanagementsystemhibernate.service.UserService;
import com.capgemini.librarymanagementsystemhibernate.service.UserServiceImp;

public class UserFactory {
	public static UserDAO getUser() {
		return new UserDAOImp();
		
	}
	public static UserService getUserService() {
		return new UserServiceImp();
	}
}
