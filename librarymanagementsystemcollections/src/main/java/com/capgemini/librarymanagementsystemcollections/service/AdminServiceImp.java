package com.capgemini.librarymanagementsystemcollections.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystemcollections.dao.AdminDAO;
import com.capgemini.librarymanagementsystemcollections.dto.BookBean;
import com.capgemini.librarymanagementsystemcollections.factory.AdminFactory;

public class AdminServiceImp implements AdminService {
	private AdminDAO dao = AdminFactory.getAdminDAO();

	public boolean update(BookBean book) {
		return dao.update(book);
	}

	public boolean delete(int bId) {
		return dao.delete(bId);
	}

	public boolean addBook(BookBean info) {
		return dao.addBook(info);
	}

	public ArrayList<Integer> getBookIds() {
		return dao.getBookIds();
	}

	public List<BookBean> getBooksInfo() {
		return dao.getBooksInfo();
	}

	public BookBean searchBookTitle(String booktitle) {
		return dao.searchBookTitle(booktitle);
	}

	public BookBean searchBookAuthor(String author) {
		return dao.searchBookAuthor(author);
	}

	public BookBean searchBookType(int bookid) {
		return dao.searchBookType(bookid);
	}

	public boolean issueBook(int book_Id, int id) {
		return dao.issueBook(book_Id, id);
	}

	public boolean returnBook(int book_Id, int id) {
		return dao.returnBook(book_Id, id);
	}

}
