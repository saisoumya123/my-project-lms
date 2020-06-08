package com.capgemini.librarymanagementsystemcollections.dto;

import java.time.LocalDate;

public class BookIssueDetailsBean {
	private int id;
	private int bid;
	private LocalDate issueDate;
	private String returnDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public LocalDate getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(LocalDate date) {
		this.issueDate = date;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String date1) {
		this.returnDate = date1;
	}
		
}
