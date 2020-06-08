package com.capgemini.librarymanagementsystemhibernate.factory;

import com.capgemini.librarymanagementsystemhibernate.dao.StudentDAO;
import com.capgemini.librarymanagementsystemhibernate.dao.StudentDAOImp;
import com.capgemini.librarymanagementsystemhibernate.service.StudentService;
import com.capgemini.librarymanagementsystemhibernate.service.StudentServiceImp;

public class StudentFactory {
	public static StudentDAO getStudentDAO() {
		
		return new StudentDAOImp();
	}
	public static StudentService getStudentService() {
		return new StudentServiceImp();
	}
}
