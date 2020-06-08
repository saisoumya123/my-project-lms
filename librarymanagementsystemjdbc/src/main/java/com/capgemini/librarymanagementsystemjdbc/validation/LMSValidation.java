package com.capgemini.librarymanagementsystemjdbc.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.librarymanagementsystemjdbc.exception.LMSException;

public class LMSValidation {
	public boolean validatedId(int id) throws LMSException {
		String idRegEx = "[1-9]{1}[0-9]{3}" ;
		boolean result = false;
		if (Pattern.matches(idRegEx, String.valueOf(id))) {
			result = true;
		} else {
			throw new LMSException("Id should contains exactly 3 digits");
		}
		return result;
	}
	public boolean validatedName(String name) throws LMSException {
		String nameRegEx = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$" ;
		boolean result = false;
		Pattern pattern = Pattern.compile(nameRegEx);
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LMSException("Name should  contains only Alphabates");
		}
		return result;
	}

	public boolean validatedMobile(CharSequence regMobile) throws LMSException {
		String mobileRegEx = "(0/91)?[6-9][0-9]{9}" ;
		boolean result = false;
		Pattern pattern = Pattern.compile(mobileRegEx);
		Matcher matcher = pattern.matcher(regMobile);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LMSException("Mobile Number  will start with  6 or 9 and It should contains 10 numbers ");
		}
		return result;
	}
	public boolean validatedEmail(String email) throws LMSException {
		String emailRegEx = "^[a-z0-9](\\.?[a-z0-9]){2,}@g(oogle)?mail\\.(com|org)";
		boolean result = false;
		Pattern pattern = Pattern.compile(emailRegEx);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LMSException("Enter proper email it should contain extension of .com or .org");
		}
		return result;
	}

	public boolean validatedPassword(String password) throws LMSException {
		String passwordRegEx = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})" ;
		boolean result = false;
		if (Pattern.matches(passwordRegEx, String.valueOf(password))) { 
			result = true;
		} else {
			throw new LMSException("Password should contain atleast 8 characters ,one uppercase,one lowercase,one symbol "); 
		}

		return result;
	}
	public boolean validatedRole(String role) throws LMSException {
		String roleRegEx = "^(?i)(admin|student)$" ;
		boolean result = false;
		if(Pattern.matches(roleRegEx, String.valueOf(role))) {
			result = true;
		} else {
			throw new LMSException("role can be either admin or student ");
		}
		return result;
	}

	public  boolean validatedDate(String date) throws LMSException {
		String regex = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$"; 
		boolean result = false;
		Pattern pattern = Pattern.compile(regex); 
		Matcher matcher = pattern.matcher((CharSequence)date); 
		if(matcher.matches()) {
			result = true;
		} else {
			throw new LMSException("Enter proper Date Format");
		}
		return result;
	}
	public boolean validatedStatus(String status) throws LMSException {
		String statusRegEx = "^(?i)(old|new)$" ;
		boolean result = false;
		if(Pattern.matches(statusRegEx, String.valueOf(status))) {
			result = true;
		} else {
			throw new LMSException("status can be either old or new ");
		}
		return result;
	}
	public boolean validatedISBN(String ISBN) throws LMSException {
		String ISBNRegEx = "^(12(8|9))?\\d{9}(\\d|X)$" ;
		boolean result = false;
		Pattern pattern = Pattern.compile(ISBNRegEx);
		Matcher matcher = pattern.matcher(ISBN);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LMSException("ISBN  will start between  128 or 129 and It should contains 10 numbers ");
		}
		return result;
	}
	
}
