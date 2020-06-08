package com.capgemini.librarymanagementsystemjdbc;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemjdbc.dao.StudentDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.StudentDAOImp;
import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;

public class StudentDaoTest {
	private StudentDAO dao = new StudentDAOImp();

	@Test
	public void testSearchBookById() {
		BookBean bean = dao.searchBookById(100001);
		Assertions.assertNotNull(bean);
	}

	@Test
	public void testSearchBookById1() {
		BookBean bean = dao.searchBookById(102);
		Assertions.assertNotNull(bean);
	}

	@Test
	public void testSearchByName() {
		BookBean bean = dao.searchBookByTitle("Java");
		Assertions.assertNotNull(bean);
	}

	@Test
	public void testSearchByName1() {
		BookBean bean = dao.searchBookByTitle("Java Programming");
		Assertions.assertNotNull(bean);
	}

	@Test
	public void testSearchByAuthor() {
		BookBean bean = dao.searchBookByAuthor("james");
		Assertions.assertNotNull(bean);
	}

	@Test
	public void testSearchByAuthor1() {
		BookBean bean = dao.searchBookByAuthor("james gosling");
		Assertions.assertNotNull(bean);
	}

	@Test
	public void testGetIds() {
		List<BookBean> beans = dao.getBookIds();
		Assertions.assertNotNull(beans);
	}

	@Test
	public void testGetIds1() {
		List<BookBean> beans = dao.getBookIds();
		Assertions.assertNull(beans);
	}

	@Test
	public void testGetAllBooks() {
		List<BookBean> beans = dao.getBooksInfo();
		Assertions.assertNotNull(beans);
	}

	@Test
	public void testGetAllBooks1() {
		List<BookBean> beans = dao.getBooksInfo();
		Assertions.assertNull(beans);
	}
	
	@Test
	public void testRequestBook() {
		boolean status = dao.requestBook(1, 100001);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testRequestBook1() {
		boolean status = dao.requestBook(1, 100001);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testRequestReturnBook() {
		boolean status = dao.requestReturnBook(100001, 1);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testRequestReturnBook1() {
		boolean status = dao.requestReturnBook(100001, 1);
		Assertions.assertTrue(status);
	}
}
