package com.capgemini.librarymanagementsystemcollections.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystemcollections.dto.BookBean;

public interface AdminService {

	boolean update(BookBean book);

	boolean delete(int bId);

	boolean addBook(BookBean info);

	ArrayList<Integer> getBookIds();

	List<BookBean> getBooksInfo();

	BookBean searchBookTitle(String booktitle);

	BookBean searchBookAuthor(String author);

	BookBean searchBookType(int bookid);

	boolean issueBook(int book_Id, int id);

	boolean returnBook(int book_Id, int id);
}
