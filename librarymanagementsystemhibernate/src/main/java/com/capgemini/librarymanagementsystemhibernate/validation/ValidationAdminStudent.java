package com.capgemini.librarymanagementsystemhibernate.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.ValidationException;

public class ValidationAdminStudent {
	public boolean validatedName(String name) throws ValidationException {
		String nameRegEx = "^(?=.{3,20}$)(?![_.-])(?!.*[_.-]{2})[a-zA-Z._-]+(?<![_.-])";
		boolean result = false;
		Pattern pattern = Pattern.compile(nameRegEx);
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new ValidationException("Name should  contains only Alpabates and should be minimun 3 and maximum 20 letters");
		}
		return result;
	}

	public boolean validatedMobile(String mobile) throws ValidationException {
		String mobileRegEx = "(0/91)?[4-9][0-9]{9}" ;
		boolean result = false;
		Pattern pattern = Pattern.compile(mobileRegEx);
		Matcher matcher = pattern.matcher(mobile);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new ValidationException("Mobile Number  will start between  4 & 9 and It should contains 10 numbers ");
		}
		return result;
	}
	public boolean validatedEmail(String email) throws ValidationException {
		String emailRegEx = "^[a-z0-9](\\.?[a-z0-9]){2,}@g(oogle)?mail\\.com$";
		boolean result = false;
		Pattern pattern = Pattern.compile(emailRegEx);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new ValidationException("Enter proper email ");
		}
		return result;
	}

	public boolean validatedPassword(String password) throws ValidationException {
		String passwordRegEx = "((?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%]).{6,20})" ;
		boolean result = false;
		if (Pattern.matches(passwordRegEx, String.valueOf(password))) { 
			result = true;
		} else {
			throw new ValidationException("Password should contain atleast 6 characters ,one uppercase,one lowercase,one number,one special symbol(@#$%) "); 
		}

		return result;
	}
	
	
	public boolean validatedISBN(String ISBN) throws ValidationException {
		String ISBNRegEx = "^(97(8|9))?\\d{9}(\\d|X)$" ;
		boolean result = false;
		Pattern pattern = Pattern.compile(ISBNRegEx);
		Matcher matcher = pattern.matcher(ISBN);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new ValidationException("ISBN  will start between  978 or 979 and It should contains 10 numbers ");
		}
		return result;
	}
	
	public boolean validatedStatus(String status) throws ValidationException {
		String statusRegEx = "^(?i)(old|new)$" ;
		boolean result = false;
		if(Pattern.matches(statusRegEx, String.valueOf(status))) {
			result = true;
		} else {
			throw new ValidationException("status can be either old or new ");
		}
		return result;
	}
	
	public boolean validatedRole(String role) throws ValidationException {
		String roleRegEx = "^(?i)(admin|student)$" ;
		boolean result = false;
		if(Pattern.matches(roleRegEx, String.valueOf(role))) {
			result = true;
		} else {
			throw new ValidationException("role can be either admin or student ");
		}
		return result;
	}
	
}
