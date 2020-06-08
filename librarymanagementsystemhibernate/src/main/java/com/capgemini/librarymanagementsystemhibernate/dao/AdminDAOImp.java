package com.capgemini.librarymanagementsystemhibernate.dao;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.exception.LMSException;

public class AdminDAOImp implements AdminDAO {
	EntityManagerFactory factory = null;

	@Override
	public boolean update(BookBean book) {

		EntityManager manager = null;
		EntityTransaction transaction = null;
		boolean isUpdated = false;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.merge(book);
			transaction.commit();
			isUpdated = true;
		} catch (Exception e) {
			throw new LMSException("Cannot update the book");
		}
		return isUpdated;
	}

	@Override
	public boolean delete(int bId) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		boolean isDeleted = false;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookBean record = manager.find(BookBean.class, bId);
			if (manager.contains(record)) {
				isDeleted = true;
				manager.remove(record);
				System.out.println("Record removed");
			} else {
				isDeleted = false;
				System.out.println("record not found");
			}

			transaction.commit();
		} catch (Exception e) {
			throw new LMSException("Cannot delete the book or invalid details of book");
		}
		manager.close();
		return isDeleted;
	}

	@Override
	public boolean addBook(BookBean info) {
		
		EntityManager manager = null;
		EntityTransaction transaction = null;
		boolean isBookAdded = false;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.merge(info);
			isBookAdded = true;
			transaction.commit();

		} catch (Exception e) {
			throw new LMSException("cannot add the book or book already exist");
		}
		manager.close();
		return isBookAdded;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getBookIds() {
		
		EntityManager manager = null;
		EntityTransaction transaction = null;
		List<Integer> bookBeans = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.getTransaction();
			Query q = manager.createQuery("select b.bid from BookBean b");
			bookBeans = q.getResultList();
			transaction.commit();

		} catch (Exception e) {
			throw new LMSException("No id found");
		}
		manager.close();
		return bookBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookBean> getBooksInfo() {
		
		EntityManager manager = null;
		EntityTransaction transaction = null;
		List<BookBean> bookBeans = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.getTransaction();
			Query q = manager.createQuery("from BookBean");
			bookBeans = q.getResultList();
			transaction.commit();

		} catch (Exception e) {
			throw new LMSException("No bookInfo present or no book in library");
		}
		manager.close();	
		return bookBeans;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean issueBook(int id, int book_id) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createQuery("select b from BookBean b where b.bid = :bid and b.copies>=1");
			Query books = query.setParameter("bid", book_id);
			List count = books.getResultList();
			int bookCount = count.size();
			if (bookCount >= 1) {
				Query query1 = manager
						.createQuery("select r from RequestBookBean r where r.id = :id and r.reqBookPK.bid = :bid");
				query1.setParameter("id", id);
				query1.setParameter("bid", book_id);
				List count1 = query1.getResultList();
				int reqBook = count1.size();
				
				if (reqBook >= 1) {
					Query query2 = manager
							.createQuery("select count(id) as idCount from BorrowedBookBean b where id=:id");
					query2.setParameter("id", id);
					List count2 = query2.getResultList();
					int borrowBook = count2.size();
					if (borrowBook >= 1) {
						int noOfBooksBorrowed = count2.indexOf(0);
						if (noOfBooksBorrowed < 3) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							LocalDate date = LocalDate.now();
							Calendar c = Calendar.getInstance();
							c.setTime(new java.util.Date());
							c.add(Calendar.DATE, 15);
							String date1 = sdf.format(c.getTime());
							Query userEmail = manager.createQuery("select u.email from UserBean u  where id = :id");
							userEmail.setParameter("id", id);
							List userEmailList = userEmail.getResultList();
							Query query3 = manager.createNativeQuery(
									"insert into bookissue1 (id,bid,email,issueDate,returnDate) values (? , ? , ? , ? , ?) ");
							query3.setParameter(1, id);
							query3.setParameter(2, book_id);
							query3.setParameter(3, userEmailList.get(0));
							query3.setParameter(4, date);
							query3.setParameter(5, date1);
							int count3 = query3.executeUpdate();
							if (count3 != 0) {
								Query userEmail1 = manager.createQuery("select u.email from UserBean u where id = :id");
								userEmail1.setParameter("id", id);
								List userEmail1List = userEmail1.getResultList();
								Query query4 = manager
										.createNativeQuery("insert into borrowbook1 (id,bid,email) values (?,?,?)");
								query4.setParameter(1, id);
								query4.setParameter(2, book_id);
								query4.setParameter(3, userEmail1List.get(0));
								query4.executeUpdate();

								Query query5 = manager.createQuery(
										"delete from RequestBookBean r where r.id = :id and r.reqBookPK.bid = :bid");
								query5.setParameter("id", id);
								query5.setParameter("bid", book_id);
								query5.executeUpdate();
								Query query6 = manager
										.createQuery("update BookBean b set b.copies = b.copies-1 where b.bid = :bid");
								query6.setParameter("bid", book_id);
								query6.executeUpdate();
								transaction.commit();
								return true;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw new LMSException("Cannot issue the book");
		}
		manager.close();
		return false;
	}

	@Override
	public BookBean searchBookTitle(String name) {
		
		EntityManager manager = null;
		EntityTransaction transaction = null;
		BookBean res = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select m from BookBean m where m.book_title =:mbook_title";
			TypedQuery<BookBean> query = manager.createQuery(jpql, BookBean.class);
			query.setParameter("mbook_title", name);
			res = query.getSingleResult();
			transaction.commit();
		} catch (Exception e) {
			throw new LMSException("Cannot search book with this name");
		}
		manager.close();
		return res;
	}

	@Override
	public BookBean searchBookAuthor(String Author) {
		
		EntityManager manager = null;
		EntityTransaction transaction = null;
		BookBean res = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();

			String jpql = "select m from BookBean m where m.author =:mauthor";
			TypedQuery<BookBean> query = manager.createQuery(jpql, BookBean.class);
			query.setParameter("mauthor", Author);
			res = query.getSingleResult();
			transaction.commit();
		} catch (Exception e) {
			throw new LMSException("Cannot find book with this author");
		}
		manager.close();
		
		return res;
	}

	@Override
	public BookBean searchBookType(int bookType) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		BookBean record = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			record = manager.find(BookBean.class, bookType);
			transaction.commit();
		} catch (Exception e) {
			throw new LMSException("Cannot find book with this id");
		}
		manager.close();
		return record;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean returnBook(int id, int book_id) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager
					.createQuery("select b from BookIssueDetailsBean b where b.id = :id and b.issuePK.bid = :bid");
			query.setParameter("id", id);
			query.setParameter("bid", book_id);
			List count = query.getResultList();
			int i = count.size();
			if (i >= 1) {
				Query query1 = manager.createQuery(
						"select r from RequestBookBean r where r.id = :id and r.reqBookPK.bid = :bid and type = :type");
				query1.setParameter("id", id);
				query1.setParameter("bid", book_id);
				query1.setParameter("type", "return");
				List count1 = query1.getResultList();
				int i1 = count1.size();
				if (i1 >= 1) {
					Query query2 = manager
							.createQuery("update BookBean b  set b.copies = b.copies+1 where b.bid = :bid");
					query2.setParameter("bid", book_id);
					query2.executeUpdate();
					Query q3 = manager
							.createQuery("delete from BookIssueDetailsBean b where b.issuePK.bid = :bid and id =:id");
					q3.setParameter("bid", book_id);
					q3.setParameter("id", id);
					q3.executeUpdate();
					Query q4 = manager
							.createQuery("delete from BorrowedBookBean b where b.borrowBookPK.bid = :bid and id = :id");
					q4.setParameter("bid", book_id);
					q4.setParameter("id", id);
					q4.executeUpdate();
					Query q5 = manager.createQuery(
							"delete from RequestBookBean r where r.id = :id and r.reqBookPK.bid = :bid and type = :type");
					q5.setParameter("id", id);
					q5.setParameter("bid", book_id);
					q5.setParameter("type", "return");
					q5.executeUpdate();
					transaction.commit();
					return true;
				}
			}
		} catch (Exception e) {
			throw new LMSException("Cannot return the book");
		}
		manager.close();
		return false;
	}

}
