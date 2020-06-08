package com.capgemini.librarymanagementsystemcollections.dao;

import com.capgemini.librarymanagementsystemcollections.database.DataBase;
import com.capgemini.librarymanagementsystemcollections.dto.UserBean;

public class UserDAOImp implements UserDAO{

	public boolean register(UserBean bean) {
		for (UserBean userBean :  DataBase.user) {
			if (userBean.getEmail().equals(bean.getEmail())) {
				return false;
			}
		}
		DataBase.user.add(bean);
		return true;
	}

	public UserBean auth(String email, String password) {
		for (UserBean bean : DataBase.user) {
			if (bean.getEmail().equals(email) && bean.getPassword().equals(password)) {
				System.out.println("Login Successful");
				bean.getRole();
				return bean;
			}
		}
		System.err.println("Invalid email and password");
		return null;
	}

	}


