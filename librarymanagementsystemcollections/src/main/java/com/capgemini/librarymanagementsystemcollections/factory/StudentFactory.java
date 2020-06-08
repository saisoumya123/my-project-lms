package com.capgemini.librarymanagementsystemcollections.factory;

import com.capgemini.librarymanagementsystemcollections.dao.StudentDAO;
import com.capgemini.librarymanagementsystemcollections.dao.StudentDAOImp;
import com.capgemini.librarymanagementsystemcollections.service.StudentService;
import com.capgemini.librarymanagementsystemcollections.service.StudentServiceImp;

public class StudentFactory {
	public static StudentDAO getStudentDAO() {

		return new StudentDAOImp();
	}

	public static StudentService getStudentService() {
		return new StudentServiceImp();
	}
}
