package com.capgemini.librarymanagementsystemjdbc.dto;

import lombok.Data;
@Data
public class BorrowedBooks{
	private int borrowedBookId;
	private int borrowedUserId;
	private String email;
}
