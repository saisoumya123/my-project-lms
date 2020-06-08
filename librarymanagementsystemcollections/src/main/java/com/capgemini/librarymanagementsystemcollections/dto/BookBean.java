package com.capgemini.librarymanagementsystemcollections.dto;

public class BookBean {
	private int bid;
	private String book_title;
	private String category;
	private String author;
	private int copies;
	private String book_publisher;
	private String publisher_name;
	private int ISBN;
	private int copyright_year;
	private String status;
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBook_title() {
		return book_title;
	}
	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getCopies() {
		return copies;
	}
	public void setCopies(int copies) {
		this.copies = copies;
	}
	public String getBook_publisher() {
		return book_publisher;
	}
	public void setBook_publisher(String book_publisher) {
		this.book_publisher = book_publisher;
	}
	public String getPublisher_name() {
		return publisher_name;
	}
	public void setPublisher_name(String publisher_name) {
		this.publisher_name = publisher_name;
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int isbn) {
		this.ISBN = isbn;
	}
	public int getCopyright_year() {
		return copyright_year;
	}
	public void setCopyright_year(int copyright_year) {
		this.copyright_year = copyright_year;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public BookBean(int bid, String book_title, String category, String author, int copies, String book_publisher,
			String publisher_name, int iSBN, int copyright_year, String status) {
		super();
		this.bid = bid;
		this.book_title = book_title;
		this.category = category;
		this.author = author;
		this.copies = copies;
		this.book_publisher = book_publisher;
		this.publisher_name = publisher_name;
		ISBN = iSBN;
		this.copyright_year = copyright_year;
		this.status = status;
	}
	
	public BookBean() {
		super();
	}
	@Override
	public String toString() {
		 return String.format("%-10s %-10s %-13s %-12s %-10s %-15s %-15s %-10s %-13s %s", 
				 bid, book_title, category, author, copies, book_publisher, publisher_name, 
				 ISBN, copyright_year, status);
	}
	
}
