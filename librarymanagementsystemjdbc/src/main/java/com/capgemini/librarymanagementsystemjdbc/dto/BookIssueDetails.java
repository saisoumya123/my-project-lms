package com.capgemini.librarymanagementsystemjdbc.dto;

import java.util.Date;

import lombok.Data;
@Data
public class BookIssueDetails {
	private int bookId;
	private int userId;
	private Date issueDate;
	private Date returnDate;	
}
