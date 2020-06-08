package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.LinkedList;

import com.capgemini.librarymanagementsystemjdbc.dao.StudentDAO;
import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.factory.LMSFactory;

public class StudentServiceImp implements StudentService {

	StudentDAO dao = LMSFactory.getStudentDAO();

	@Override
	public BookBean searchBookByTitle(String title) {

		return dao.searchBookByTitle(title);
	}

	@Override
	public BookBean searchBookByAuthor(String author) {

		return dao.searchBookByAuthor(author);
	}

	@Override
	public BookBean searchBookById(int bookId) {

		return dao.searchBookById(bookId);
	}

	@Override
	public LinkedList<BookBean> getBookIds() {

		return dao.getBookIds();
	}

	@Override
	public LinkedList<BookBean> getBooksInfo() {

		return dao.getBooksInfo();
	}

	@Override
	public boolean requestReturnBook(int bookId, int userId) {

		return dao.requestReturnBook(bookId, userId);
	}

	@Override
	public boolean requestBook(int userId, int bookId) {

		return dao.requestBook(userId, bookId);
	}

}
