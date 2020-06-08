package com.capgemini.librarymanagementsystemcollections.factory;

import com.capgemini.librarymanagementsystemcollections.dao.AdminDAO;
import com.capgemini.librarymanagementsystemcollections.dao.AdminDAOImp;
import com.capgemini.librarymanagementsystemcollections.service.AdminService;
import com.capgemini.librarymanagementsystemcollections.service.AdminServiceImp;

public class AdminFactory {
	public static AdminDAO getAdminDAO() {
		return new AdminDAOImp();
	}
	public static AdminService getAdminService() {
		return new AdminServiceImp();
	}
}
