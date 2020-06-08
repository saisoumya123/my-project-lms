package com.capgemini.librarymanagementsystemcollections.database;

import java.util.ArrayList;

import com.capgemini.librarymanagementsystemcollections.dto.BookBean;
import com.capgemini.librarymanagementsystemcollections.dto.BookIssueDetailsBean;
import com.capgemini.librarymanagementsystemcollections.dto.BorrowedBookBean;
import com.capgemini.librarymanagementsystemcollections.dto.RequestBookBean;
import com.capgemini.librarymanagementsystemcollections.dto.UserBean;

public class DataBase {
	public static final ArrayList<BookBean> book = new ArrayList<BookBean>();
	public static final ArrayList<BorrowedBookBean> borrowBook = new ArrayList<BorrowedBookBean>();
	public static final ArrayList<RequestBookBean> requestBookBean = new ArrayList<RequestBookBean>();
	public static final ArrayList<UserBean> user = new ArrayList<UserBean>();
	public static final ArrayList<BookIssueDetailsBean> issueBook = new ArrayList<BookIssueDetailsBean>();

public static void defaultDB() {
	user.add(new UserBean("sai",1,"9640156716","sai@gmail.com","Sai123@","student"));
	user.add(new UserBean("soumya",2,"7671907630","soumya@gmail.com","Soumya123@","admin"));
	
	user.add(new UserBean("rex",3,"8640156716","rex@gmail.com","Rex123@","student"));
	user.add(new UserBean("kim",4,"7623907630","kim@gmail.com","Kim123@","admin"));
	
	book.add(new BookBean(1,"c","Programming","Dennis Ritchie",3,"sun","sun",97832134,1996,"old"));
	book.add(new BookBean(2,"c++","Programming","Stroutstroop",3,"sun","sun",97842634,1996,"new"));
}

}
