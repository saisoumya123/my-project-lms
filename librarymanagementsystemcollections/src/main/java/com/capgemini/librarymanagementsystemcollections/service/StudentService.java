package com.capgemini.librarymanagementsystemcollections.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystemcollections.dto.BookBean;

public interface StudentService {
	public BookBean searchBookTitle(String bookTitle);

	public BookBean searchBookAuthor(String Author);

	public ArrayList<Integer> getBookIds();

	public List<BookBean> getBooksInfo();

	boolean reqReturnBook(int book_Id, int id);

	BookBean searchBookType(int bookId);

	boolean req(int id, int book_id);
}
