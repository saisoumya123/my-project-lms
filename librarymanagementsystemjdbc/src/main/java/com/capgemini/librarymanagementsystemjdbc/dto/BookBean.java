package com.capgemini.librarymanagementsystemjdbc.dto;

import lombok.Data;
@Data
public class BookBean {
	private static final Object bookPublishername = null;
	private int bookId;
	private String bookName;
	private String bookAuthor;
	private String bookCategory;
	private String bookPublisherName;
	private int bookCopies;
	private int bookIsbn;
	private int bookCopyRight;
	private String status;
	
//	@Override
//	public String toString() {
//		return "BookBean [bid=" + bookId + ", book_title=" + bookName + ", category=" + bookCategory + ", author=" + bookAuthor
//				+ ", copies=" + bookCopies + ", publisher_name=" + bookPublisherName
//				+ ", ISBN=" + bookIsbn + ", copyright_year=" + bookCopyRight + ", status= " + status + "]";
//	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	public String getBookPublisherName() {
		return bookPublisherName;
	}

	public void setBookPublisherName(String bookPublisherName) {
		this.bookPublisherName = bookPublisherName;
	}

	public int getBookCopies() {
		return bookCopies;
	}

	public void setBookCopies(int bookCopies) {
		this.bookCopies = bookCopies;
	}

	public int getBookIsbn() {
		return bookIsbn;
	}

	public void setBookIsbn(int bookIsbn) {
		this.bookIsbn = bookIsbn;
	}

	public int getBookCopyRight() {
		return bookCopyRight;
	}

	public void setBookCopyRight(int bookCopyRight) {
		this.bookCopyRight = bookCopyRight;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		 return String.format("%-10s %-10s %-13s %-12s %-10s %-15s %-10s %-13s %s", 
				 bookId, bookName, bookCategory, bookAuthor, bookCopies, bookPublishername, 
				 bookIsbn, bookCopyRight, status);
		}
	
}
