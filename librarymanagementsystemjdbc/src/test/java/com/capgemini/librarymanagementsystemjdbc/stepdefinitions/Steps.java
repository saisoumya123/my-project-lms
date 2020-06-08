package com.capgemini.librarymanagementsystemjdbc.stepdefinitions;

import org.junit.jupiter.api.Assertions;

import com.capgemini.librarymanagementsystemjdbc.dao.UserDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.UserDAOImp;
import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps {
	private UserDAO dao = new UserDAOImp();
	UserBean user;
	@Given("^User is on registration page$")
	public void user_is_on_registration_page() throws Throwable {
		user = new UserBean();
	}

	@When("^User enters (\\d+), \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void user_enters(int arg1, String arg2, String arg3, String arg4, String arg5, String arg6) throws Throwable {
		user.setId(1);
		user.setName("Shalini");
		user.setMobile("7330083533");
		user.setEmail("shalini@gmail.com");
		user.setPassword("Shalini@123");
		user.setRole("student");
	}

	@Then("^User should be \"([^\"]*)\"$")
	public void user_should_be(String arg1) throws Throwable {
		boolean status = dao.register(user);
		Assertions.assertTrue(status);
	}
	
	
//	@Given("^Admin is on login page$")
//	public void admin_is_on_login_page() throws Throwable {
//		user = new UserBean();
//	}
//
//	@When("^Adimn gives \"([^\"]*)\", \"([^\"]*)\"$")
//	public void adimn_gives(String arg1, String arg2) throws Throwable {
//		user.setEmail(arg1);
//		user.setPassword(arg2); 
//	}
//
//	@Then("^Admin should be logged-in$")
//	public void admin_should_be_logged_in() throws Throwable {
//		UserBean userBean = dao.login(user.getEmail(),user.getPassword());
//		Assertions.assertNull(userBean);
//	}
}
