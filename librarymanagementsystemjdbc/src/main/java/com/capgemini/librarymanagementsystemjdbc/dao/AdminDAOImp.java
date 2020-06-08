package com.capgemini.librarymanagementsystemjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;
import com.capgemini.librarymanagementsystemjdbc.exception.LMSException;
import com.capgemini.librarymanagementsystemjdbc.utility.JdbcUtility;

public class AdminDAOImp implements AdminDAO {
	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	ResultSet rs;
	Properties properties = new Properties();

	UserBean user = new UserBean();
	int count = 0;

	@Override
	public boolean addBook(BookBean book) throws LMSException {
		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.addBook);
			preparedStatement.setInt(1, book.getBookId());
			preparedStatement.setString(2, book.getBookName());
			preparedStatement.setString(3, book.getBookAuthor());
			preparedStatement.setString(4, book.getBookCategory());
			preparedStatement.setString(5, book.getBookPublisherName());
			preparedStatement.setInt(6, book.getBookCopies());
			preparedStatement.setInt(7, book.getBookIsbn());
			preparedStatement.setInt(8, book.getBookCopyRight());
			preparedStatement.setString(9, book.getStatus());
			count = preparedStatement.executeUpdate();
			return true;

		} catch (Exception e) {
			throw new LMSException("Book not added.!! Try again");
		}
	}

	@Override
	public BookBean searchBookByTitle(String title) throws LMSException {
		connection = JdbcUtility.getConnection();
		BookBean bean = new BookBean();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.searchBookByName);
			preparedStatement.setString(1, title);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				bean.setBookId(rs.getInt("bId"));
				bean.setBookName(rs.getString("bname"));
				bean.setBookAuthor(rs.getString("author"));
				bean.setBookPublisherName(rs.getString("publishername"));
				bean.setBookCategory(rs.getString("category"));
				bean.setBookCopies(rs.getInt("copies"));
				bean.setBookIsbn(rs.getInt("isbn"));
				bean.setBookCopyRight(rs.getInt("copyright"));
				bean.setStatus(rs.getString("status"));
				return bean;
			} else {
				System.out.println("BookName with " + title.toUpperCase() + " does not exist");
			}
		} catch (Exception e) {
			throw new LMSException("NO book found with title ");
		}
		return null;
	}

	@Override
	public BookBean searchBookByAuthor(String author) throws LMSException {
		connection = JdbcUtility.getConnection();
		BookBean bean = new BookBean();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.searchBookByAuthor);
			preparedStatement.setString(1, author);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {

				bean.setBookId(rs.getInt("bId"));
				bean.setBookName(rs.getString("bname"));
				bean.setBookAuthor(rs.getString("author"));
				bean.setBookPublisherName(rs.getString("publishername"));
				bean.setBookCategory(rs.getString("category"));
				bean.setBookCopies(rs.getInt("copies"));
				bean.setBookIsbn(rs.getInt("isbn"));
				bean.setBookCopyRight(rs.getInt("copyright"));
				bean.setStatus(rs.getString("status"));
				return bean;
			} else {
				System.out.println("Book Author with " + " does not exist");
			}
		} catch (Exception e) {
			throw new LMSException("No book found");
		}
		return null;
	}

	@Override
	public BookBean searchBookById(int bookId) throws LMSException{
		connection = JdbcUtility.getConnection();
		BookBean bean = new BookBean();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.searchBookById);
			preparedStatement.setInt(1, bookId);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				bean.setBookId(rs.getInt("bId"));
				bean.setBookName(rs.getString("bname"));
				bean.setBookAuthor(rs.getString("author"));
				bean.setBookCategory(rs.getString("category"));
				bean.setBookPublisherName(rs.getString("publishername"));
				bean.setBookCopies(rs.getInt("copies"));
				bean.setBookIsbn(rs.getInt("isbn"));
				bean.setBookCopyRight(rs.getInt("copyright"));
				bean.setStatus(rs.getString("status"));
				return bean;
			}
		} catch (Exception e) {
			throw new LMSException("No book found with book-id");
		}
		return null;
	}

	public boolean updateBook(BookBean bean) throws LMSException {
		boolean isUpdated = false;
		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.bookUpdate);
			preparedStatement.setString(1, bean.getBookName());
			preparedStatement.setInt(2, bean.getBookId());
			count = preparedStatement.executeUpdate();
			if (count != 0) {
				System.out.println("Book has been updated Sucessfully");
			} else {
				System.out.println("Book NOT updated");
			}
		} catch (Exception e) {
			throw new LMSException("Book NOT found");
		}
		return isUpdated;
	}

	@Override
	public boolean removeBook(int bookId) {
		boolean isRemoved = false;
		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.deleteBook);
			preparedStatement.setInt(1, bookId);
			count = preparedStatement.executeUpdate();
			if (count != 0) {
				System.out.println("book has been removed Successfully");
			} else {
				System.out.println("Book with book-id " + bookId + " does not exists");
			}
		} catch (Exception e) {
			throw new LMSException("NO book found with book-id ");
		}
		return isRemoved;
	}

	@Override
	public List<BookBean> getBookIds() {

		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.getBookIds);
			rs = preparedStatement.executeQuery();
			LinkedList<BookBean> beans = new LinkedList<BookBean>();
			while (rs.next()) {
				BookBean bean = new BookBean();
				bean.setBookId(rs.getInt("bId"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			throw new LMSException("Cannot get book-ids");
		}
	}

	@Override
	public List<BookBean> getBooksInfo() {
		connection = JdbcUtility.getConnection();
		try {
			LinkedList<BookBean> beans = new LinkedList<BookBean>();
			try (PreparedStatement preparedStatement = connection.prepareStatement(QueryMapper.getAllBookInfo)) {
				rs = preparedStatement.executeQuery();
				while (rs.next()) {
					BookBean bean = new BookBean();
					bean.setBookId(rs.getInt("bId"));
					bean.setBookName(rs.getString("bname"));
					bean.setBookAuthor(rs.getString("author"));
					bean.setBookCopies(rs.getInt("copies"));
					bean.setBookCategory(rs.getString("category"));
					bean.setBookPublisherName(rs.getString("publishername"));
					bean.setBookCopies(rs.getInt("copies"));
					bean.setBookIsbn(rs.getInt("isbn"));
					bean.setBookCopyRight(rs.getInt("copyright"));
					bean.setStatus(rs.getString("status"));
					beans.add(bean);
				}
				return beans;
			}
		} catch (Exception e) {
			throw new LMSException("Cannot get books information");
		}
	}

	@Override
	public List<UserBean> showUsers() {
		List<UserBean> beans = new ArrayList<UserBean>();
		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.getAllUserInfo);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt("uId"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setMobile(rs.getString("mobile"));
				user.setRole(rs.getString("role"));
				beans.add(user);
			}
			return beans;
		} catch (Exception e) {
			throw new LMSException("Cannot get Users-Info");
		}
	}

	@Override
	public boolean issueBook(int bookId, int userId) {
		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.bookDetails);
			preparedStatement.setInt(1, bookId);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				PreparedStatement preparedStatement1 = connection.prepareStatement(QueryMapper.requestBook);
				preparedStatement1.setInt(1, bookId);
				preparedStatement1.setInt(2, userId);
				ResultSet rs1 = preparedStatement1.executeQuery();
				if (rs1.next()) {
					PreparedStatement preparedStatement2 = connection.prepareStatement(QueryMapper.borrowBook);
					preparedStatement2.setInt(1, userId);
					ResultSet rs2 = preparedStatement2.executeQuery();
					if (rs2.next()) {
						int noOfBooksBorrowed = rs2.getInt("uid");
						if (noOfBooksBorrowed < 3) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							LocalDate date = LocalDate.now();
							Calendar cal = Calendar.getInstance();
							cal.setTime(new java.util.Date());
							cal.add(Calendar.DATE, 15);
							String date1 = sdf.format(cal.getTime());
							PreparedStatement preparedStatement3 = connection.prepareStatement(QueryMapper.bookIssue);
							preparedStatement3.setInt(1, userId);
							preparedStatement3.setInt(2, bookId);
							preparedStatement3.setObject(3, date);
							preparedStatement3.setObject(4, date1);
							count = preparedStatement3.executeUpdate();
							if (count != 0) {
								PreparedStatement preparedStatement4 = connection
										.prepareStatement(QueryMapper.borrowBookDetails);
								preparedStatement4.setInt(1, bookId);
								preparedStatement4.setInt(2, userId);
								preparedStatement4.setInt(3, userId);
								preparedStatement4.executeUpdate();
								PreparedStatement preparedStatement5 = connection
										.prepareStatement(QueryMapper.requestDelete);
								preparedStatement5.setInt(1, userId);
								preparedStatement5.setInt(2, bookId);
								preparedStatement5.executeUpdate();
								PreparedStatement preparedStatement6 = connection
										.prepareStatement(QueryMapper.updateBookDetails1);
								preparedStatement6.setInt(1, bookId);
								preparedStatement6.executeUpdate();
							} else {
								System.out.println("You have excedded the borrowing limit");
							}

						}
					}
				}
			}
		} catch (Exception e) {
			throw new LMSException("Cannot issue book ");
		}
		return false;
	}

	@Override
	public boolean returnBook(int bookId, int userId) {

		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.issueBook);
			preparedStatement.setInt(1, bookId);
			preparedStatement.setInt(2, userId);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				PreparedStatement preparedStatement1 = connection.prepareStatement(QueryMapper.requestBook);
				preparedStatement1.setInt(1, bookId);
				preparedStatement1.setInt(2, userId);
				@SuppressWarnings("unused")
				ResultSet rs1 = preparedStatement1.executeQuery();

				if (rs.next()) {
					PreparedStatement preparedStatement2 = connection.prepareStatement(QueryMapper.updateBookDetails2);
					preparedStatement2.setInt(1, bookId);
					preparedStatement2.executeUpdate();

					PreparedStatement preparedStatement3 = connection.prepareStatement(QueryMapper.deleteBookDetails);
					preparedStatement3.setInt(1, bookId);
					preparedStatement3.setInt(2, userId);
					preparedStatement3.executeUpdate();

					PreparedStatement preparedStatement4 = connection.prepareStatement(QueryMapper.deleteBorrowBook);
					preparedStatement4.setInt(1, bookId);
					preparedStatement4.setInt(2, userId);
					preparedStatement4.executeUpdate();

					PreparedStatement preparedStatement5 = connection.prepareStatement(QueryMapper.deleteRequestBook);
					preparedStatement5.setInt(1, bookId);
					preparedStatement5.setInt(2, userId);
					preparedStatement5.executeUpdate();
					return true;
				}
			}
		} catch (Exception e) {
			throw new LMSException("Cannot return book");
		}
		return false;
	}

}
