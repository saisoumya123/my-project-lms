package com.capgemini.librarymanagementsystemcollections.service;

import com.capgemini.librarymanagementsystemcollections.dto.UserBean;

public interface UserService {
	boolean register(UserBean bean);

	UserBean auth(String email, String password);
}
