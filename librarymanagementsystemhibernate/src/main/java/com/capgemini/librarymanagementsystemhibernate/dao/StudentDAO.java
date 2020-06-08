package com.capgemini.librarymanagementsystemhibernate.dao;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;

public interface StudentDAO {
	public BookBean searchBookTitle(String name); 
	public BookBean searchBookAuthor(String Author);
	boolean req(int id, int book_id);
	public List<Integer> getBookIds();
	public List<BookBean> getBooksInfo();
	BookBean searchBookType(int bookType);
	boolean reqReturnBook(int id, int book_id);
}
