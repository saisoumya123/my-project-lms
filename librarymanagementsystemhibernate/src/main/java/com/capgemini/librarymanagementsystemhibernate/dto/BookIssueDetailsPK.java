package com.capgemini.librarymanagementsystemhibernate.dto;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Embeddable
public class BookIssueDetailsPK implements Serializable{
	//@Column(insertable = false,updatable = false)
private int bid;
	//@Column(insertable = false,updatable = false)
private String email;
}
