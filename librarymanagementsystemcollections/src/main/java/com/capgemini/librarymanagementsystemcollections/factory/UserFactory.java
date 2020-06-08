package com.capgemini.librarymanagementsystemcollections.factory;

import com.capgemini.librarymanagementsystemcollections.dao.UserDAO;
import com.capgemini.librarymanagementsystemcollections.dao.UserDAOImp;
import com.capgemini.librarymanagementsystemcollections.service.UserService;
import com.capgemini.librarymanagementsystemcollections.service.UserServiceImp;

public class UserFactory {
	public static UserDAO getUser() {
		return new UserDAOImp();

	}

	public static UserService getUserService() {

		return new UserServiceImp();
	}
}
