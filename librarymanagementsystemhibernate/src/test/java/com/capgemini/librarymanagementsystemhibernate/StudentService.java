package com.capgemini.librarymanagementsystemhibernate;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.service.StudentServiceImp;

public class StudentService {
	StudentServiceImp service = new StudentServiceImp();
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
	
	@Test
	public void testRequestBook() {
		boolean status = service.req(1, 56);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testRequestBook1() {
		boolean status = service.req(1, 56);
		Assertions.assertFalse(status);
	}
	@Test
	public void testReturnRequestBook() {
		boolean status = service.reqReturnBook(56, 1);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testReturnRequestBook1() {
		boolean status = service.reqReturnBook(56, 1);
		Assertions.assertFalse(status);
	}
}
