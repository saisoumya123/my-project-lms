package com.capgemini.librarymanagementsystemhibernate.service;

import com.capgemini.librarymanagementsystemhibernate.dto.UserBean;

public interface UserService {
	boolean register(UserBean bean);
	UserBean auth(String email, String password);
}
