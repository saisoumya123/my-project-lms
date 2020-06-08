package com.capgemini.librarymanagementsystemhibernate.service;

import com.capgemini.librarymanagementsystemhibernate.dao.UserDAO;
import com.capgemini.librarymanagementsystemhibernate.dto.UserBean;
import com.capgemini.librarymanagementsystemhibernate.factory.UserFactory;

public class UserServiceImp implements UserService{
private UserDAO dao = UserFactory.getUser();
	@Override
	public boolean register(UserBean bean) {
		return dao.register(bean);
	}

	@Override
	public UserBean auth(String email, String password) {
		return dao.auth(email, password);
	}

}
