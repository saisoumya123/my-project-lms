package com.capgemini.librarymanagementsystemcollections.dao;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystemcollections.database.DataBase;
import com.capgemini.librarymanagementsystemcollections.dto.BookBean;
import com.capgemini.librarymanagementsystemcollections.dto.RequestBookBean;
import com.capgemini.librarymanagementsystemcollections.dto.UserBean;

public class StudentDAOImp implements StudentDAO {

	public BookBean searchBookTitle(String name) {
		for (BookBean bean : DataBase.book) {
			if (bean.getBook_title().equalsIgnoreCase(name)) {
				return bean;
			}
		}
		return null;
	}

	public BookBean searchBookAuthor(String Author) {
		for (BookBean bean : DataBase.book) {
			if (bean.getAuthor().equalsIgnoreCase(Author)) {
				return bean;
			}
		}
		return null;
	}

	public boolean req(int id, int book_id) {
		String title = null;
		String name = null;
		String email = null;
		RequestBookBean reqBookBean = new RequestBookBean();
		boolean isBookPresent = false;

		for (BookBean bean2 : DataBase.book) {
			if (bean2.getBid() == book_id) {
				isBookPresent = true;
			} else {
				System.out.println("Book not found");
			}
			if (isBookPresent) {

				for (BookBean bookName : DataBase.book) {
					if (bookName.getBid() == book_id) {
						title = bookName.getBook_title();

					}
					for (UserBean userBean : DataBase.user) {
						if (userBean.getId() == id) {
							name = userBean.getName();
							email = userBean.getEmail();
						}
					}
				}
			}

			reqBookBean.setId(id);
			reqBookBean.setBid(book_id);
			reqBookBean.setBook_title(title);
			reqBookBean.setName(name);
			reqBookBean.setEmail(email);
			reqBookBean.setType("request");
			DataBase.requestBookBean.add(reqBookBean);
			return true;
		}

		return false;
	}

	public ArrayList<Integer> getBookIds() {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		for (int i = 0; i <= DataBase.book.size() - 1; i++) {
			BookBean retrievedBook = DataBase.book.get(i);
			int retrievedBookId = retrievedBook.getBid();
			idList.add(retrievedBookId);
		}
		return idList;
	}

	public List<BookBean> getBooksInfo() {
		return DataBase.book;
	}

	@SuppressWarnings("unused")
	public BookBean searchBookType(int bookType) {
		BookBean getById = new BookBean();
		for (BookBean bean : DataBase.book) {
			if (bean.getBid() == bookType) {
				return bean;
			}
		}
		return null;
	}

	public boolean reqReturnBook(int id, int book_id) {
		String title = null;
		String name = null;
		String email = null;
		RequestBookBean reqBookBean = new RequestBookBean();
		boolean isBookPresent = false;
		for (BookBean bean2 : DataBase.book) {
			if (bean2.getBid() == book_id) {
				isBookPresent = true;
			} else {
				System.out.println("Book not found");
			}
			if (isBookPresent) {

				for (BookBean bookName : DataBase.book) {
					if (bookName.getBid() == book_id) {
						title = bookName.getBook_title();

					}
					for (UserBean userBean : DataBase.user) {
						if (userBean.getId() == id) {
							name = userBean.getName();
							email = userBean.getEmail();
						}
					}
				}
			}

			reqBookBean.setId(id);
			reqBookBean.setBid(book_id);
			reqBookBean.setBook_title(title);
			reqBookBean.setName(name);
			reqBookBean.setEmail(email);
			reqBookBean.setType("return");
			DataBase.requestBookBean.add(reqBookBean);
			return true;
		}

		return false;
	}

}
