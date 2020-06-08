package com.capgemini.librarymanagementsystemhibernate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemhibernate.dto.UserBean;
import com.capgemini.librarymanagementsystemhibernate.service.UserServiceImp;

public class UserService {
	UserServiceImp service = new UserServiceImp();
	UserBean bean = new UserBean();
	@Test
	public void testRegister() {
		bean.setEmail("flick@gmail.com");
		bean.setId(65);
		bean.setMobile("9087654321");
		bean.setName("flick");
		bean.setPassword("Flick123@");
		bean.setRole("admin");
		boolean status = service.register(bean);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testRegister1() {
		bean.setEmail("jeff@gmail.com");
		bean.setId(66);
		bean.setMobile("8987654321");
		bean.setName("jeff");
		bean.setPassword("Jeff123@");
		bean.setRole("student");
		boolean status = service.register(bean);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testLogin() {
		UserBean bean1 = service.auth("flick@gmail.com", "Flick123@");
		Assertions.assertNotNull(bean1);
	}
	
	@Test
	public void testLogin1() {
		UserBean bean1 = service.auth("jeff@gmail.com", "Jeff123@");
		Assertions.assertNotNull(bean1);
	}
	
}
