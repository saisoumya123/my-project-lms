package com.capgemini.librarymanagementsystemhibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;

public interface StudentService {
	public BookBean searchBookTitle(String name); 
	public BookBean searchBookAuthor(String Author);
	
	public List<Integer> getBookIds();
	public List<BookBean> getBooksInfo();
	boolean req(int id, int book_id);
	boolean reqReturnBook(int id , int book_id);
	BookBean searchBookType(int bookType);
}
