package com.capgemini.librarymanagementsystemjdbc.factory;

import com.capgemini.librarymanagementsystemjdbc.dao.AdminDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.AdminDAOImp;
import com.capgemini.librarymanagementsystemjdbc.dao.StudentDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.StudentDAOImp;
import com.capgemini.librarymanagementsystemjdbc.dao.UserDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.UserDAOImp;
import com.capgemini.librarymanagementsystemjdbc.service.AdminService;
import com.capgemini.librarymanagementsystemjdbc.service.AdminServiceImp;
import com.capgemini.librarymanagementsystemjdbc.service.StudentService;
import com.capgemini.librarymanagementsystemjdbc.service.StudentServiceImp;
import com.capgemini.librarymanagementsystemjdbc.service.UserService;
import com.capgemini.librarymanagementsystemjdbc.service.UsersServiceImp;

public class LMSFactory {
	public static UserDAO getUserDAO() {
		return new UserDAOImp();
	}
	public static UserService getUserService() {
		return new UsersServiceImp();
	}
	
	public static AdminDAO getAdminDAO() {
		return new AdminDAOImp();
	}
	public static AdminService getAdminService() {
		return new AdminServiceImp();
	}
	
	public static StudentDAO getStudentDAO() {
		return new StudentDAOImp();
	}
	
	public static StudentService getStudentService() {
		return new StudentServiceImp();
	}
}
