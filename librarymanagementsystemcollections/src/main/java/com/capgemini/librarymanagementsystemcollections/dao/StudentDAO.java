package com.capgemini.librarymanagementsystemcollections.dao;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystemcollections.dto.BookBean;

public interface StudentDAO {
	public BookBean searchBookTitle(String name); 
	public BookBean searchBookAuthor(String Author);
	boolean req(int id, int book_id);
	public ArrayList<Integer> getBookIds();
	public List<BookBean> getBooksInfo();
	BookBean searchBookType(int bookType);
	boolean reqReturnBook(int id, int book_id);
}
