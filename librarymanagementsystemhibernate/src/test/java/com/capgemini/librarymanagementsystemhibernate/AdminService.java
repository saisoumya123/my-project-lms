package com.capgemini.librarymanagementsystemhibernate;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.service.AdminServiceImp;

public class AdminService {
	BookBean bean = new BookBean();
	AdminServiceImp service = new AdminServiceImp();
	@Test
	public void testAddBook() {
		BookBean bean = new BookBean();
		bean.setBid(56);
		bean.setBook_title("mysql");
		bean.setAuthor("codd");
		bean.setBook_publisher("sun");
		bean.setCategory("sw");
		bean.setCopies(3);
		bean.setPublisher_name("sun");
		bean.setCopyright_year(1995);
		bean.setISBN(98765432);
		bean.setStatus("old");
		boolean status = service.addBook(bean);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testAddBook1() {
		BookBean bean = new BookBean();
		bean.setBid(56);
		bean.setBook_title("mysql");
		bean.setAuthor("codd");
		bean.setBook_publisher("sun");
		bean.setCategory("sw");
		bean.setCopies(3);
		bean.setPublisher_name("sun");
		bean.setCopyright_year(1995);
		bean.setISBN(98765432);
		bean.setStatus("old");
		boolean status = service.addBook(bean);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testUpdateBook() {
		bean.setBid(55);
		bean.setBook_title("java");
		boolean status = service.update(bean);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testUpdateBook1() {
		bean.setBid(55);
		bean.setBook_title("java");
		boolean status = service.update(bean);
		Assertions.assertFalse(status);
	}
	/*
	 * @Test public void testDeleteBook() { boolean status = service.delete(55);
	 * Assertions.assertTrue(status); }
	 * 
	 * @Test public void testDeleteBook1() { boolean status = service.delete(55);
	 * Assertions.assertFalse(status); }
	 */
	
	@Test
	public void testSearchBookById() {
		BookBean bean1 = service.searchBookType(56);
		Assertions.assertNotNull(bean1);
	}
	
	@Test
	public void testSearchBookById1() {
		BookBean bean1 = service.searchBookType(55);
		Assertions.assertNotNull(bean1);
	}
	
	@Test
	public void testSearchBookByName() {
		BookBean bean1 = service.searchBookTitle("mysql");
		Assertions.assertNotNull(bean1);
	}
	
	@Test
	public void testSearchBookByName1() {
		BookBean bean1 = service.searchBookTitle("sql");
		Assertions.assertNotNull(bean1);
	}
	
	@Test
	public void testSearchBookByAuthor() {
		BookBean bean1 = service.searchBookAuthor("codd");
		Assertions.assertNotNull(bean1);
	}
	
	@Test
	public void testSearchBookByAuthor1() {
		BookBean bean1 = service.searchBookTitle("william");
		Assertions.assertNotNull(bean1);
	}
	
	@Test
	public void testIssueBook() {
		boolean status = service.issueBook(56, 1);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testIssueBook1() {
		boolean status = service.issueBook(56, 1);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testreturnBook() {
		boolean status = service.returnBook(56, 1);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testReturnBook1() {
		boolean status = service.returnBook(56, 1);
		Assertions.assertFalse(status);
	}
	
	@Test
	public void testGetId() {
		List<Integer> bean1 = service.getBookIds();
		Assertions.assertNotNull(bean1);
	}
	
	@Test
	public void testGetId1() {
		List<Integer> bean1 = service.getBookIds();
		Assertions.assertEquals(1, bean1.size());
	}
	
	@Test
	public void testGetInfo() {
		List<BookBean> bean1 = service.getBooksInfo();
		Assertions.assertNotNull(bean1);
	}
	
	@Test
	public void testGetInfo1() {
		List<BookBean> bean1 = service.getBooksInfo();
		Assertions.assertEquals(1, bean1.size());
	}
	
}
