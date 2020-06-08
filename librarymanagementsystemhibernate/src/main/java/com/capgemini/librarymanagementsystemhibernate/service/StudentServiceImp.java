package com.capgemini.librarymanagementsystemhibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dao.StudentDAO;
import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.factory.StudentFactory;

public class StudentServiceImp implements StudentService{
	private StudentDAO dao = StudentFactory.getStudentDAO();
	@Override
	public BookBean searchBookTitle(String name) {
		return dao.searchBookTitle(name);
	}

	@Override
	public BookBean searchBookAuthor(String Author) {
		return dao.searchBookAuthor(Author);
	}

	@Override
	public List<Integer> getBookIds() {
		return dao.getBookIds();
	}

	@Override
	public List<BookBean> getBooksInfo() {
		return dao.getBooksInfo();
	}

	@Override
	public BookBean searchBookType(int bookType) {
		return dao.searchBookType(bookType);
	}

	@Override
	public boolean req(int id, int book_id) {
		return dao.req(id, book_id);
	}

	@Override
	public boolean reqReturnBook(int id, int book_id) {
		return dao.reqReturnBook(id, book_id);
	}

}
