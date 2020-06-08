package com.capgemini.librarymanagementsystemhibernate.dao;

import com.capgemini.librarymanagementsystemhibernate.dto.UserBean;

public interface UserDAO {

	boolean register(UserBean bean);

	UserBean auth(String email, String password);

}
