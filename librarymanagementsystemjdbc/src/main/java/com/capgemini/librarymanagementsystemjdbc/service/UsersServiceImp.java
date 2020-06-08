package com.capgemini.librarymanagementsystemjdbc.service;

import com.capgemini.librarymanagementsystemjdbc.dao.UserDAO;
import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;
import com.capgemini.librarymanagementsystemjdbc.factory.LMSFactory;

public class UsersServiceImp  implements UserService{
	UserDAO dao = LMSFactory.getUserDAO();
	@Override
	public boolean register(UserBean info) {
		
		return dao.register(info);
	}
	@Override
	public UserBean login(String email, String password) {
		
		return dao.login(email, password);
	}
}
