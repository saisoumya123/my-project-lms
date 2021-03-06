package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.LinkedList;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;

public interface StudentService {
	BookBean searchBookByTitle(String title); 
	BookBean searchBookByAuthor(String author);
	BookBean searchBookById(int bookId);
	LinkedList<BookBean> getBookIds();
	LinkedList<BookBean> getBooksInfo();
	boolean requestReturnBook(int bookId, int userId);	
	boolean requestBook(int userId, int bookId);
}
