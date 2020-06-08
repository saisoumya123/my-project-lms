package com.capgemini.librarymanagementsystemcollections.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystemcollections.dao.StudentDAO;
import com.capgemini.librarymanagementsystemcollections.dto.BookBean;
import com.capgemini.librarymanagementsystemcollections.factory.StudentFactory;

public class StudentServiceImp implements StudentService {
	private StudentDAO dao = StudentFactory.getStudentDAO();

	public BookBean searchBookTitle(String bookTitle) {
		return dao.searchBookTitle(bookTitle);
	}

	public BookBean searchBookAuthor(String Author) {
		return dao.searchBookAuthor(Author);
	}

	public ArrayList<Integer> getBookIds() {
		return dao.getBookIds();
	}

	public List<BookBean> getBooksInfo() {
		return dao.getBooksInfo();
	}

	public boolean reqReturnBook(int book_Id, int id) {
		return dao.reqReturnBook(book_Id, id);
	}

	public BookBean searchBookType(int bookId) {
		return dao.searchBookType(bookId);
	}

	public boolean req(int id, int book_id) {
		return dao.req(id, book_id);
	}

}
