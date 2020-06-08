package com.capgemini.librarymanagementsystemjdbc.service;

import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;

public interface UserService {
	boolean register(UserBean info);
	UserBean login(String email, String password);
}
