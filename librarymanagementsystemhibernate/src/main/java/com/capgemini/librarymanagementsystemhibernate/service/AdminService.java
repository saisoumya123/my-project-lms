package com.capgemini.librarymanagementsystemhibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;

public interface AdminService {
	
	boolean update(BookBean book);
	boolean delete(int bId);
	boolean addBook(BookBean info);
	List<Integer> getBookIds();
	List<BookBean> getBooksInfo();
	boolean issueBook(int id , int book_id);
	BookBean searchBookTitle(String name);
	BookBean searchBookAuthor(String Author);
	BookBean searchBookType(int bookType);
	boolean returnBook(int id , int book_id);
}
