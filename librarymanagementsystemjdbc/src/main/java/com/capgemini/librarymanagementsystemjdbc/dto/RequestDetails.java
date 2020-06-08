package com.capgemini.librarymanagementsystemjdbc.dto;

import lombok.Data;

@Data
public class RequestDetails {
	private int userId;
	private String userName;
	private int bookId;
	private String bookName;
	private String email;

}
