package com.capgemini.librarymanagementsystemhibernate.factory;

import com.capgemini.librarymanagementsystemhibernate.dao.AdminDAO;
import com.capgemini.librarymanagementsystemhibernate.dao.AdminDAOImp;
import com.capgemini.librarymanagementsystemhibernate.service.AdminService;
import com.capgemini.librarymanagementsystemhibernate.service.AdminServiceImp;

public class AdminFactory {
	public static AdminDAO getAdminDAO() {
		return new AdminDAOImp();
	}
	public static AdminService getAdminService() {
		return new AdminServiceImp();
	}
}
