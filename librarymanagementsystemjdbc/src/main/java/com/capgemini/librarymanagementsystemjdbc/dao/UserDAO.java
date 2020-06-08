package com.capgemini.librarymanagementsystemjdbc.dao;

import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;

public interface UserDAO {
	boolean register(UserBean info);
	UserBean login(String email, String password);
}
