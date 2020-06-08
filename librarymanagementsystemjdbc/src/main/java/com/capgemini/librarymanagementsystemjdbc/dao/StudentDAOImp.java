package com.capgemini.librarymanagementsystemjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.exception.LMSException;
import com.capgemini.librarymanagementsystemjdbc.utility.JdbcUtility;

public class StudentDAOImp implements StudentDAO {
	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	ResultSet rs;
	int rs1;
	BookBean bean = new BookBean();

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
	public BookBean searchBookByAuthor(String author) {
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
	public BookBean searchBookById(int bookId) {
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
			} else {
				System.out.println("Book Id with" + bookId + "does not exist");
			}
		} catch (Exception e) {
			throw new LMSException("No book found with book-id");
		}
		return null;
	}

	@Override
	public LinkedList<BookBean> getBookIds() {

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
	public LinkedList<BookBean> getBooksInfo() {
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
			throw new LMSException("Cannot get book-ids");
		}
	}

	@Override
	public boolean requestReturnBook(int bookId, int userId) {
		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.requestBookDetails);
			preparedStatement.setInt(1, bookId);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				PreparedStatement preparedStatement1 = connection.prepareStatement(QueryMapper.requestInsert);
				preparedStatement1.setInt(1, userId);
				preparedStatement1.setInt(2, userId);
				preparedStatement1.setInt(3, bookId);
				preparedStatement1.setInt(4, bookId);
				preparedStatement1.setInt(5, userId);
				preparedStatement1.setString(6, "request");
				rs1 = preparedStatement1.executeUpdate();
				return true;
			}
		} catch (Exception e) {
			throw new LMSException("Cannot insert book");
		}
		return false;
	}

	@Override
	public boolean requestBook(int userId, int bookId) {
		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.requestBookDetails);
			preparedStatement.setInt(1, bookId);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				PreparedStatement preparedStatement1 = connection.prepareStatement(QueryMapper.returnRequest);
				preparedStatement1.setInt(1, userId);
				preparedStatement1.setInt(2, userId);
				preparedStatement1.setInt(3, bookId);
				preparedStatement1.setInt(4, bookId);
				preparedStatement1.setInt(5, userId);
				preparedStatement1.setString(6, "return");
				rs1 = preparedStatement1.executeUpdate();
				return true;

			}
		} catch (Exception e) {
			throw new LMSException("Cannot place the request for the book-id" + bookId);
		}
		return false;
	}
}
