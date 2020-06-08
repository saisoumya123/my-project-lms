package com.capgemini.librarymanagementsystemcollections.dao;

import com.capgemini.librarymanagementsystemcollections.dto.UserBean;

public interface UserDAO {

	boolean register(UserBean bean);
	UserBean auth(String email, String password);

}
