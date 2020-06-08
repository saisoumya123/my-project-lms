package com.capgemini.librarymanagementsystemhibernate;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemhibernate.dao.AdminDAOImp;
import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;



public class AdminDAO {
	BookBean bean = new BookBean();
	AdminDAOImp dao = new AdminDAOImp();
	@Test
	public void testAddBook() {
		bean.setBid(5);
		bean.setBook_title("msword");
		bean.setAuthor("Henry");
		bean.setBook_publisher("sunms");
		bean.setCategory("swe");
		bean.setCopies(2);
		bean.setPublisher_name("sunms");
		bean.setCopyright_year(1996);
		bean.setISBN(98765432);
		bean.setStatus("new");
		boolean status = dao.addBook(bean);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testAddBook1() {
		bean.setBid(5);
		bean.setBook_title("msword");
		bean.setAuthor("Henry");
		bean.setBook_publisher("sunms");
		bean.setCategory("swe");
		bean.setCopies(2);
		bean.setPublisher_name("sunms");
		bean.setCopyright_year(1996);
		bean.setISBN(98765432);
		bean.setStatus("new");
		boolean status = dao.addBook(bean);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testAddBook2() {
		bean.setBid(6);
		bean.setBook_title("ms");
		bean.setAuthor("Heny");
		bean.setBook_publisher("sunms");
		bean.setCategory("swe");
		bean.setCopies(2);
		bean.setPublisher_name("sunms");
		bean.setCopyright_year(1996);
		bean.setISBN(98765432);
		bean.setStatus("new");
		boolean status = dao.addBook(bean);
		Assertions.assertTrue(status);
	}
	@Test
	public void testUpdateBook() {
		bean.setBid(5);
		bean.setBook_title("java");
		boolean status = dao.update(bean);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testUpdateBook1() {
		bean.setBid(55);
		bean.setBook_title("java");
		boolean status = dao.update(bean);
		Assertions.assertFalse(status);
	}
	@Test
	public void testDeleteBook() {
		boolean status = dao.delete(5);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testDeleteBook1() {
		boolean status = dao.delete(55);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testSearchBookById() {
		BookBean bean1 = dao.searchBookType(5);
		Assertions.assertNotNull(bean1);
	}
	
	@Test
	public void testSearchBookById1() {
		BookBean bean1 = dao.searchBookType(55);
		Assertions.assertNotNull(bean1);
	}
	
	@Test
	public void testSearchBookByName() {
		BookBean bean1 = dao.searchBookTitle("java");
		Assertions.assertNotNull(bean1);
	}
	
	@Test
	public void testSearchBookByName1() {
		BookBean bean1 = dao.searchBookTitle("sql");
		Assertions.assertNotNull(bean1);
	}
	
	@Test
	public void testSearchBookByAuthor() {
		BookBean bean1 = dao.searchBookAuthor("henry");
		Assertions.assertNotNull(bean1);
	}
	
	@Test
	public void testSearchBookByAuthor1() {
		BookBean bean1 = dao.searchBookTitle("heny");
		Assertions.assertNotNull(bean1);
	}
	
	@Test
	public void testIssueBook() {
		boolean status = dao.issueBook(5, 1);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testIssueBook1() {
		boolean status = dao.issueBook(56, 1);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testreturnBook() {
		boolean status = dao.returnBook(5, 1);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testReturnBook1() {
		boolean status = dao.returnBook(56, 1);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testGetId() {
		List<Integer> bean1 = dao.getBookIds();
		Assertions.assertNotNull(bean1);
	}
	
	@Test
	public void testGetId1() {
		List<Integer> bean1 = dao.getBookIds();
		Assertions.assertEquals(1, bean1.size());
	}
	
	@Test
	public void testGetInfo() {
		List<BookBean> bean1 = dao.getBooksInfo();
		Assertions.assertNotNull(bean1);
	}
	
	@Test
	public void testGetInfo1() {
		List<BookBean> bean1 = dao.getBooksInfo();
		Assertions.assertEquals(1, bean1.size());
	}
	
}
