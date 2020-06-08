package com.capgemini.librarymanagementsystemcollections.dao;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystemcollections.dto.BookBean;

public interface AdminDAO {

	boolean update(BookBean book);

	boolean delete(int bId);

	boolean addBook(BookBean info);

	ArrayList<Integer> getBookIds();

	List<BookBean> getBooksInfo();

	BookBean searchBookTitle(String name);

	BookBean searchBookAuthor(String Author);

	BookBean searchBookType(int bookType);

	boolean issueBook(int id, int book_id);

	boolean returnBook(int id, int book_id);

}
