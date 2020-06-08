package com.capgemini.librarymanagementsystemjdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemjdbc.dao.UserDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.UserDAOImp;
import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;

public class UserDaoTest {
	private UserDAO dao = new UserDAOImp();

	@Test
	public void testAddUser() {
		UserBean user = new UserBean();
		user.setId(1);
		user.setName("Shalini");
		user.setMobile("7330083533");
		user.setEmail("shalini@gmail.com");
		user.setPassword("Shalini@123");
		user.setRole("student");
		boolean status = dao.register(user);
		Assertions.assertTrue(status);
	}

	@Test
	public void testAddUser1() {
		UserBean user = new UserBean();
		user.setId(1);
		user.setName("Shalini");
		user.setMobile("7330083533");
		user.setEmail("shalini@gmail.com");
		user.setPassword("Shalini@123");
		user.setRole("student");
		boolean status = dao.register(user);
		Assertions.assertTrue(status);
	}

	@Test
	public void testLogin() {
		UserBean userBean = dao.login("shalini@gmail.com", "Shalini@123");
		Assertions.assertNotNull(userBean);
	}

	@Test
	public void testLogin1() {
		UserBean userBean = dao.login("swetha@gmail.com", "Swetha1998");
		Assertions.assertNotNull(userBean);
	}
}
