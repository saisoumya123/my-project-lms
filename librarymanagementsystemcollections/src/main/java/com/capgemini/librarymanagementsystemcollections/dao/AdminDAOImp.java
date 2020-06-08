package com.capgemini.librarymanagementsystemcollections.dao;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.capgemini.librarymanagementsystemcollections.database.DataBase;
import com.capgemini.librarymanagementsystemcollections.dto.BookBean;
import com.capgemini.librarymanagementsystemcollections.dto.BookIssueDetailsBean;
import com.capgemini.librarymanagementsystemcollections.dto.BorrowedBookBean;
import com.capgemini.librarymanagementsystemcollections.dto.RequestBookBean;
import com.capgemini.librarymanagementsystemcollections.dto.UserBean;

public class AdminDAOImp implements AdminDAO {

	public boolean update(BookBean book) {
		for (int i = 0; i <= DataBase.book.size() - 1; i++) {
			BookBean retrievedBook = DataBase.book.get(i);
			if (retrievedBook.getBid() == book.getBid()) {
				retrievedBook.setBook_title(book.getBook_title());
				return true;

			}

			else {
				System.err.println("Invalid Book");
			}
		}
		System.err.println("Book cannot be updated");
		return false;
	}

	public boolean delete(int bId) {
		boolean status = false;
		for (int i = 0; i <= DataBase.book.size() - 1; i++) {
			BookBean retrievedBook = DataBase.book.get(i);
			int retrievedId = retrievedBook.getBid();
			if (bId == retrievedId) {
				status = true;
				DataBase.book.remove(i);
				break;
			}
		}
		return status;
	}

	public boolean addBook(BookBean info) {
		for (BookBean bean : DataBase.book) {
			if (bean.getBid() == info.getBid()) {
				return false;
			}
		}
		DataBase.book.add(info);
		return true;

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

	public BookBean searchBookType(int bookType) {
		for (BookBean bean : DataBase.book) {
			if (bean.getBid() == bookType) {
				return bean;
			}
		}

		return null;
	}

	public boolean issueBook(int id, int book_id) {
		BookIssueDetailsBean issBook = new BookIssueDetailsBean();
		boolean isBookAvailable = false;
		@SuppressWarnings("unused")
		boolean isRequested = false;
		boolean isBookIssued = false;
		int noOfBooksBorrowed = 0;
		for (BookBean bean : DataBase.book) {
			if (bean.getBid() == book_id && bean.getCopies() >= 1) {
				isBookAvailable = true;
			} else {
				System.out.println("Book not found");
			}
			if (isBookAvailable) {
				for (RequestBookBean reqBean : DataBase.requestBookBean) {
					if (reqBean.getBid() == book_id && reqBean.getId() == id) {
						isRequested = true;

					} else {
						System.out.println("Book not requested");
					}

					for (BorrowedBookBean borrowBean : DataBase.borrowBook) {
						if (borrowBean.getId() == id) {
							DataBase.borrowBook.size();
							noOfBooksBorrowed++;
						}
					}
					if (noOfBooksBorrowed < 3) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						LocalDate date = LocalDate.now();
						Calendar c = Calendar.getInstance();
						c.setTime(new java.util.Date());
						c.add(Calendar.DATE, 15);
						String date1 = sdf.format(c.getTime());
						@SuppressWarnings("unused")
						BookIssueDetailsBean issue = new BookIssueDetailsBean();

						issBook.setBid(book_id);
						issBook.setId(id);
						issBook.setIssueDate(date);
						issBook.setReturnDate(date1);
						DataBase.issueBook.add(issBook);
						isBookIssued = true;

					}
					String email = null;
					boolean borrowed = false;
					if (isBookIssued) {
						BorrowedBookBean borrowBook = new BorrowedBookBean();
						for (UserBean bean1 : DataBase.user) {
							if (bean1.getId() == id) {
								email = bean1.getEmail();
							}
						}
						borrowBook.setBid(book_id);
						borrowBook.setId(id);
						borrowBook.setEmail(email);
						DataBase.borrowBook.add(borrowBook);
						borrowed = true;

						if (borrowed) {
							RequestBookBean reqBook = new RequestBookBean();
							if (reqBook.getBid() == book_id && reqBook.getId() == id) {
								DataBase.requestBookBean.remove(reqBook);
							}

							for (BookBean bean2 : DataBase.book) {
								if (bean2.getBid() == book_id) {
									int copy = bean2.getCopies();
									int bookCopies = --copy;
									bean2.setCopies(bookCopies);
									return true;
								}
							}
						}
					}
				}

			}
		}

		return false;
	}

	@SuppressWarnings("unused")
	public boolean returnBook(int id, int book_id) {
		boolean book = false;
		for (BookIssueDetailsBean issueBook : DataBase.issueBook) {
			if (issueBook.getBid() == book_id && issueBook.getId() == id) {
				book = true;
			}
		}
		if (book) {
			for (RequestBookBean reqBook : DataBase.requestBookBean) {
				if (reqBook.getBid() == book_id && reqBook.getId() == id) {
					for (BookBean bean : DataBase.book) {
						if (bean.getBid() == book_id) {
							int copies = bean.getCopies();
							int copy = ++copies;
							bean.setCopies(copy);
						}
						for (int i = 0; i <= DataBase.issueBook.size() - 1; i++) {
							BookIssueDetailsBean issBook = new BookIssueDetailsBean();
							if (DataBase.issueBook.get(i).getBid() == book_id
									&& DataBase.issueBook.get(i).getId() == id) {
								DataBase.issueBook.remove(i);
							}
						}
						for (int i = 0; i <= DataBase.borrowBook.size(); i++) {
							if (DataBase.borrowBook.get(i).getBid() == book_id
									&& DataBase.borrowBook.get(i).getId() == id) {
								DataBase.borrowBook.remove(i);
							}
						}
						for (int i = 0; i <= DataBase.requestBookBean.size(); i++) {
							if (DataBase.requestBookBean.get(i).getBid() == book_id
									&& DataBase.requestBookBean.get(i).getId() == id) {
								DataBase.requestBookBean.remove(i);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

}
