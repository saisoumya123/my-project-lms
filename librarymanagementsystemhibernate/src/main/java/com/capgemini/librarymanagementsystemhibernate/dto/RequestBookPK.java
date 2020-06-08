package com.capgemini.librarymanagementsystemhibernate.dto;

import java.io.Serializable;

import javax.persistence.Embeddable;



@SuppressWarnings("serial")
@Embeddable
public class RequestBookPK implements Serializable{
	//@Column(insertable = false,updatable = false)
private int bid;
	//@Column(insertable = false,updatable = false)
private String email;
public int getBid() {
	return bid;
}
public void setBid(int bid) {
	this.bid = bid;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + bid;
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	RequestBookPK other = (RequestBookPK) obj;
	if (bid != other.bid)
		return false;
	if (email == null) {
		if (other.email != null)
			return false;
	} else if (!email.equals(other.email))
		return false;
	return true;
}
@Override
public String toString() {
	return "RequestBookPK [bid=" + bid + ", email=" + email + "]";
}


}
