package com.capgemini.librarymanagementsystemcollections.service;

import com.capgemini.librarymanagementsystemcollections.dao.UserDAO;
import com.capgemini.librarymanagementsystemcollections.dto.UserBean;
import com.capgemini.librarymanagementsystemcollections.factory.UserFactory;

public class UserServiceImp implements UserService {
	private UserDAO dao = UserFactory.getUser();

	public boolean register(UserBean bean) {
		return dao.register(bean);
	}

	public UserBean auth(String email, String password) {
		return dao.auth(email, password);
	}

}
