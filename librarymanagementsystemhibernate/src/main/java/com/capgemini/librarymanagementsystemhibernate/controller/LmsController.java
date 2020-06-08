package com.capgemini.librarymanagementsystemhibernate.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.ValidationException;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.dto.UserBean;
import com.capgemini.librarymanagementsystemhibernate.factory.AdminFactory;
import com.capgemini.librarymanagementsystemhibernate.factory.StudentFactory;
import com.capgemini.librarymanagementsystemhibernate.factory.UserFactory;
import com.capgemini.librarymanagementsystemhibernate.service.AdminService;
import com.capgemini.librarymanagementsystemhibernate.service.StudentService;
import com.capgemini.librarymanagementsystemhibernate.service.UserService;
import com.capgemini.librarymanagementsystemhibernate.validation.ValidationAdminStudent;

public class LmsController {
	@SuppressWarnings("resource")
	public static void doReg() {
		boolean flag = false;
		int regId1 = 0;
		String regName = null;
		String regMobile = null;
		String regEmail = null;
		String regPassword = null;
		String regRole = null;
		ValidationAdminStudent validation = new ValidationAdminStudent();

		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("*********************Welcome to Library**********************");
			System.out.println("Press 1 to register");
			System.out.println("Press 2 to login");
			System.out.println("**************************************************************");
			UserService service1 = UserFactory.getUserService();

			int i = scanner.nextInt();
			switch (i) {
			case 1:
				do {

					System.out.println("Enter ID :");
					String regId = scanner.next();
					try {
						regId1 = Integer.parseInt(regId);
						flag = true;
					} catch (Exception e) {
						flag = false;
						System.err.println("Id should contains only digits");
					}

				} while (!flag);

				do {
					try {
						System.out.println("Enter Name :");
						regName = scanner.next();
						validation.validatedName(regName);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Name should contains only Alphabates");
					} catch (javax.xml.bind.ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter Mobile :");
						regMobile = scanner.next();
						validation.validatedMobile(regMobile);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Mobile Number  should contains only numbers");
					} catch (javax.xml.bind.ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter Email :");
						regEmail = scanner.next();
						validation.validatedEmail(regEmail);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Email should be proper ");
					} catch (javax.xml.bind.ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter Password :");
						regPassword = scanner.next();
						validation.validatedPassword(regPassword);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Enter correct Password ");
					} catch (javax.xml.bind.ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter Role :");
						regRole = scanner.next();
						validation.validatedRole(regRole);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Enter correct Role ");
					} catch (javax.xml.bind.ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				UserBean bean = new UserBean();
				bean.setId(regId1);
				bean.setName(regName);
				bean.setMobile(regMobile);
				bean.setEmail(regEmail);
				bean.setPassword(regPassword);
				bean.setRole(regRole);
				try {
					boolean check = service1.register(bean);
					if (check == false) {
						System.out.println("Email already exist");
					} else {
						System.out.println("Registered");
					}
				} catch (Exception e) {
					System.out.println("invalid");
				}
				break;
			case 2:

				System.out.println("******************************************************");
				do {
					try {
						System.out.println("Enter Email :");
						regEmail = scanner.next();
						validation.validatedEmail(regEmail);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Email should be proper with proper extension .com or .org");
					} catch (javax.xml.bind.ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter Password :");
						regPassword = scanner.next();
						validation.validatedPassword(regPassword);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Enter correct Password ");
					} catch (javax.xml.bind.ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				try {
					UserBean auth = service1.auth(regEmail, regPassword);
					if (auth.getRole().equalsIgnoreCase("admin")) {
						do {
							admin();
						} while (true);
					} else if (auth.getRole().equalsIgnoreCase("student")) {
						do {
							student();
						} while (true);
					} else {
						System.out.println("invalid email and password");
					}
				} catch (Exception e) {
					System.out.println("invalid credentials");
				}
				break;
			}
		} while (true);
	}

	@SuppressWarnings("resource")
	public static void admin() {
		AdminService service = AdminFactory.getAdminService();
		BookBean bean = new BookBean();
		Scanner scanner = new Scanner(System.in);
		System.out.println("*****************************Welcome to ADMIN PAGE****************************");
		System.out.println("Press 1 to Add Books");
		System.out.println("Press 2 to update");
		System.out.println("Press 3 for Search page");
		System.out.println("Press 4 to remove the Books");
		System.out.println("Press 5 to Get the Book Id's");
		System.out.println("Press 6 to Get the Book Information");
		System.out.println("Press 7 to issue book");
		System.out.println("Press 8 for return book");
		System.out.println("Press 9 to main");

		int choice1 = scanner.nextInt();
		ValidationAdminStudent validation = new ValidationAdminStudent();
		boolean flag = false;
		int bookId = 0;
		String bookAuthor = null;
		String bookName = null;
		String bookCategory = null;
		String bookPublisherName = null;
		String bookPublisher = null;
		int ISBN = 0;
		String status = null;
		int copies = 0;
		int year = 0;

		switch (choice1) {
		case 1:
			do {
				System.out.println("Enter ID :");
				String regId = scanner.next();
				try {
					bookId = Integer.parseInt(regId);
					flag = true;
				} catch (Exception e) {
					flag = false;
					System.err.println("Id should contains only digits");
				}

			} while (!flag);

			do {
				try {
					System.out.println("Enter Book Name :");
					bookName = scanner.next();
					validation.validatedName(bookName);
					flag = true;
				} catch (InputMismatchException e) {
					flag = false;
					System.err.println("Book-Name should contains only Alphabates");
				} catch (ValidationException e) {
					flag = false;
					System.err.println(e.getMessage());
				}
			} while (!flag);
			do {
				try {
					System.out.println("Enter Author :");
					bookAuthor = scanner.next();
					validation.validatedName(bookAuthor);
					flag = true;
				} catch (InputMismatchException e) {
					flag = false;
					System.err.println("Author Name should contains only Alphabates");
				} catch (ValidationException e) {
					flag = false;
					System.err.println(e.getMessage());
				}
			} while (!flag);

			do {
				try {
					System.out.println("Enter Category :");
					bookCategory = scanner.next();
					validation.validatedName(bookCategory);
					flag = true;
				} catch (InputMismatchException e) {
					flag = false;
					System.err.println("Book-Category should contains only Alphabates");
				} catch (ValidationException e) {
					flag = false;
					System.err.println(e.getMessage());
				}
			} while (!flag);

			do {
				try {
					System.out.println("Enter PublisherName :");
					bookPublisherName = scanner.next();
					validation.validatedName(bookPublisherName);
					flag = true;
				} catch (InputMismatchException e) {
					flag = false;
					System.err.println("Book-PublisherName should contains only Alphabates");
				} catch (ValidationException e) {
					flag = false;
					System.err.println(e.getMessage());
				}
			} while (!flag);

			do {
				try {
					System.out.println("Enter BookPublisher :");
					bookPublisher = scanner.next();
					validation.validatedName(bookPublisher);
					flag = true;
				} catch (InputMismatchException e) {
					flag = false;
					System.err.println("Book-Publisher should contains only Alphabates");
				} catch (ValidationException e) {
					flag = false;
					System.err.println(e.getMessage());
				}
			} while (!flag);

			do {
				try {
					System.out.println("Enter Number Of Copies :");
					String copies1 = scanner.next();
					try {
						copies = Integer.parseInt(copies1);

					} catch (Exception e) {
						e.printStackTrace();
					}

				} catch (InputMismatchException e) {
					flag = false;
					System.err.println("Copies should contains only digits");
				}
			} while (!flag);

			do {
				try {
					System.out.println("Enter ISBN :");
					String isbn = scanner.next();
					try {
						ISBN = Integer.parseInt(isbn);

					} catch (Exception e) {
						e.printStackTrace();
					}

				} catch (InputMismatchException e) {
					flag = false;
					System.err.println("ISBN should contains only digits");
				}
			} while (!flag);

			do {
				try {
					System.out.println("Enter status :");
					status = scanner.next();
					validation.validatedStatus(status);
					flag = true;
				} catch (InputMismatchException e) {
					flag = false;
					System.err.println("Status can be only new or old");
				} catch (javax.xml.bind.ValidationException e) {
					flag = false;
					System.err.println(e.getMessage());
				}
			} while (!flag);

			do {

				System.out.println("Enter Copyright Year :");
				String copyrightYear = scanner.next();
				try {
					year = Integer.parseInt(copyrightYear);
					flag = true;
				} catch (Exception e) {
					flag = false;
					System.err.println("Id should contains only digits");
				}

			} while (!flag);

			bean.setBid(bookId);
			bean.setBook_title(bookName);
			bean.setCategory(bookCategory);
			bean.setAuthor(bookAuthor);
			bean.setBook_publisher(bookPublisher);
			bean.setPublisher_name(bookPublisherName);
			bean.setCopies(copies);
			bean.setISBN(ISBN);
			bean.setCopyright_year(year);
			bean.setStatus(status);

			boolean check = service.addBook(bean);
			if (check == false) {
				System.out.println("Book already exists");
			} else {
				System.out.println("Book added");
			}

			break;
		case 2:
			do {
				System.out.println("Enter BookID :");
				String regId = scanner.next();
				try {
					bookId = Integer.parseInt(regId);
					flag = true;
				} catch (Exception e) {
					flag = false;
					System.err.println("Id should contains only digits");
				}

			} while (!flag);
			do {
				try {
					System.out.println("Enter Book Name :");
					bookName = scanner.next();
					validation.validatedName(bookName);
					flag = true;
				} catch (InputMismatchException e) {
					flag = false;
					System.err.println("Book-Name should contains only Alphabates");
				} catch (ValidationException e) {
					flag = false;
					System.err.println(e.getMessage());
				}
			} while (!flag);

			bean.setBid(bookId);
			bean.setBook_title(bookName);
			boolean updated = service.update(bean);
			if (updated == false) {
				System.out.println("book is not updated");
			} else {
				System.out.println("book  updated");
			}

			break;
		case 3:
			do {
				System.out.println("***************************Welcome to Search Page********************************");
				System.out.println("Press 1 to Search the Book by Author");
				System.out.println("Press 2 to Search the Book by Title");
				System.out.println("Press 3 to Search the Book by Id");
				System.out.println("Press 0 for exit");
				int j = scanner.nextInt();
				switch (j) {
				case 1:
					do {
						try {
							System.out.println("Enter Author :");
							bookAuthor = scanner.next();
							validation.validatedName(bookAuthor);
							flag = true;
						} catch (InputMismatchException e) {
							flag = false;
							System.err.println("Author Name should contains only Alphabates");
						} catch (ValidationException e) {
							flag = false;
							System.err.println(e.getMessage());
						}
					} while (!flag);

					BookBean bean4 = new BookBean();
					bean4.setAuthor(bookAuthor);
					BookBean searchByAuthor = service.searchBookAuthor(bookAuthor);
					if (searchByAuthor != null) {
						System.out.println(searchByAuthor.getBid());
						System.out.println(searchByAuthor.getBook_title());
						System.out.println(searchByAuthor.getCategory());
						System.out.println(searchByAuthor.getAuthor());
						System.out.println(searchByAuthor.getCopies());
						System.out.println(searchByAuthor.getBook_publisher());
						System.out.println(searchByAuthor.getPublisher_name());
						System.out.println(searchByAuthor.getISBN());
						System.out.println(searchByAuthor.getCopyright_year());
						System.out.println(searchByAuthor.getStatus());
						System.out.println(searchByAuthor);
					} else {
						System.out.println("Book Not Found");
					}
					break;

				case 2:
					do {
						try {
							System.out.println("Enter Book Name :");
							bookName = scanner.next();
							validation.validatedName(bookName);
							flag = true;
						} catch (InputMismatchException e) {
							flag = false;
							System.err.println("Book-Name should contains only Alphabates");
						} catch (ValidationException e) {
							flag = false;
							System.err.println(e.getMessage());
						}
					} while (!flag);
					BookBean bean3 = new BookBean();
					bean3.setBook_title(bookName);
					BookBean searchByName = service.searchBookTitle(bookName);
					if (searchByName != null) {
						System.out.println();
						System.out.println(searchByName.getBid());
						System.out.println(searchByName.getBook_title());
						System.out.println(searchByName.getCategory());
						System.out.println(searchByName.getAuthor());
						System.out.println(searchByName.getCopies());
						System.out.println(searchByName.getBook_publisher());
						System.out.println(searchByName.getPublisher_name());
						System.out.println(searchByName.getISBN());
						System.out.println(searchByName.getCopyright_year());
						System.out.println(searchByName.getStatus());

						System.out.println(searchByName);
					} else {
						System.out.println("Book Not Found");
					}
					break;

				case 3:
					do {

						System.out.println("Enter ID :");
						String bookId1 = scanner.next();
						try {
							bookId = Integer.parseInt(bookId1);
							flag = true;
						} catch (Exception e) {
							flag = false;
							System.err.println("Id should contains only digits");
						}

					} while (!flag);

					BookBean bean5 = new BookBean();
					bean5.setBid(bookId);
					BookBean searchById = service.searchBookType(bookId);
					if (searchById != null) {
						System.out.println(searchById.getBid());
						System.out.println(searchById.getBook_title());
						System.out.println(searchById.getCategory());
						System.out.println(searchById.getAuthor());
						System.out.println(searchById.getCopies());
						System.out.println(searchById.getBook_publisher());
						System.out.println(searchById.getPublisher_name());
						System.out.println(searchById.getISBN());
						System.out.println(searchById.getCopyright_year());
						System.out.println(searchById.getStatus());
						System.out.println(searchById);
					} else {
						System.out.println("Book Not Found");
					}
					break;
				case 0:
					admin();
				}
			} while (true);
		case 4:
			do {

				System.out.println("Enter ID :");
				String bookId1 = scanner.next();
				try {
					bookId = Integer.parseInt(bookId1);
					flag = true;
				} catch (Exception e) {
					flag = false;
					System.err.println("Id should contains only digits");
				}

			} while (!flag);
			if (bookId == 0) {
				System.out.println("Enter the Valid Book_Id");
			} else {
				BookBean bean6 = new BookBean();

				bean6.setBid(bookId);
				boolean remove = service.delete(bookId);
				if (remove == false) {
					System.out.println("The Book is not removed");
				} else {
					System.out.println("The Book is removed");
				}
			}

			break;
		case 5:
			List<Integer> ids = service.getBookIds();
			try {
				for (Integer books : ids) {
					if (books != null) {
						System.out.println(books);
					} else {
						System.out.println("No Books Ids are available");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 6:

			List<BookBean> info = service.getBooksInfo();
			for (BookBean bookBean : info) {

				if (bookBean != null) {
					System.out.println(bookBean);
				} else {
					System.out.println("Books info is not present");
				}
			}
			break;
		case 7:
			System.out.println("Enter Book Id");
			int issueId = scanner.nextInt();
			System.out.println("Enter User Id");
			int userId = scanner.nextInt();
			boolean check4 = service.issueBook(userId, issueId);
			if (check4) {
				System.out.println("-----------------------------------------------");
				System.out.println("Book Issued");
			} else {
				System.out.println("-----------------------------------------------");
				System.out.println("Book not issued");
			}
			break;
		case 8:
			System.out.println("Enter book id");
			int bookId1 = scanner.nextInt();
			System.out.println("Enter user id");
			int userId1 = scanner.nextInt();
			boolean check5 = service.returnBook(userId1, bookId1);
			if (check5 == true) {
				System.out.println("Book Returned");
			} else {
				System.out.println("Book cannot be returned");
			}
		case 9:
			doReg();
		}

	}

	@SuppressWarnings("resource")
	public static void student() {
		Scanner scanner = new Scanner(System.in);
		StudentService service2 = StudentFactory.getStudentService();
		do {
			try {
				System.out.println("Press 1 for Search page");
				System.out.println("Press 2 to Get the Book Id's");
				System.out.println("Press 3 to Get the Book Information");
				System.out.println("Press 4 to place returnReq book");
				System.out.println("Press 5 to request book");
				System.out.println("Press 6 to main");

				boolean flag = false;
				ValidationAdminStudent validation = new ValidationAdminStudent();
				int bookId = 0;
				String bookAuthor = null;
				String bookName = null;

				int choice2 = scanner.nextInt();
				switch (choice2) {
				case 1:
					do {
						System.out.println("Press 1 to Search the Book by Author");
						System.out.println("Press 2 to Search the Book by Title");
						System.out.println("Press 3 to Search the Book by Id");
						System.out.println("Press 0 to exit");
						int i = scanner.nextInt();
						switch (i) {
						case 1:
							do {
								try {
									System.out.println("Enter Author :");
									bookAuthor = scanner.next();
									validation.validatedName(bookAuthor);
									flag = true;
								} catch (InputMismatchException e) {
									flag = false;
									System.err.println("Author Name should contains only Alphabates");
								} catch (ValidationException e) {
									flag = false;
									System.err.println(e.getMessage());
								}
							} while (!flag);

							BookBean bean4 = new BookBean();
							bean4.setAuthor(bookAuthor);
							BookBean searchByAuthor = service2.searchBookAuthor(bookAuthor);
							if (searchByAuthor != null) {
								System.out.println(searchByAuthor.getBid());
								System.out.println(searchByAuthor.getBook_title());
								System.out.println(searchByAuthor.getCategory());
								System.out.println(searchByAuthor.getAuthor());
								System.out.println(searchByAuthor.getCopies());
								System.out.println(searchByAuthor.getBook_publisher());
								System.out.println(searchByAuthor.getPublisher_name());
								System.out.println(searchByAuthor.getISBN());
								System.out.println(searchByAuthor.getCopyright_year());
								System.out.println(searchByAuthor.getStatus());
								System.out.println(searchByAuthor);
							} else {
								System.out.println("Book Not Found");
							}
							break;

						case 2:
							do {
								try {
									System.out.println("Enter Book Name :");
									bookName = scanner.next();
									validation.validatedName(bookName);
									flag = true;
								} catch (InputMismatchException e) {
									flag = false;
									System.err.println("Book-Name should contains only Alphabates");
								} catch (ValidationException e) {
									flag = false;
									System.err.println(e.getMessage());
								}
							} while (!flag);
							BookBean bean3 = new BookBean();
							bean3.setBook_title(bookName);
							BookBean searchByName = service2.searchBookTitle(bookName);
							if (searchByName != null) {
								System.out.println();
								System.out.println(searchByName.getBid());
								System.out.println(searchByName.getBook_title());
								System.out.println(searchByName.getCategory());
								System.out.println(searchByName.getAuthor());
								System.out.println(searchByName.getCopies());
								System.out.println(searchByName.getBook_publisher());
								System.out.println(searchByName.getPublisher_name());
								System.out.println(searchByName.getISBN());
								System.out.println(searchByName.getCopyright_year());
								System.out.println(searchByName.getStatus());

								System.out.println(searchByName);
							} else {
								System.out.println("Book Not Found");
							}
							break;

						case 3:
							do {

								System.out.println("Enter ID :");
								String bookId1 = scanner.next();
								try {
									bookId = Integer.parseInt(bookId1);
									flag = true;
								} catch (Exception e) {
									flag = false;
									System.err.println("Id should contains only digits");
								}

							} while (!flag);

							BookBean bean5 = new BookBean();
							bean5.setBid(bookId);
							BookBean searchById = service2.searchBookType(bookId);
							if (searchById != null) {
								System.out.println(searchById.getBid());
								System.out.println(searchById.getBook_title());
								System.out.println(searchById.getCategory());
								System.out.println(searchById.getAuthor());
								System.out.println(searchById.getCopies());
								System.out.println(searchById.getBook_publisher());
								System.out.println(searchById.getPublisher_name());
								System.out.println(searchById.getISBN());
								System.out.println(searchById.getCopyright_year());
								System.out.println(searchById.getStatus());
								System.out.println(searchById);
							} else {
								System.out.println("Book Not Found");
							}
							break;
						case 0:
							student();
						}
					} while (true);
				case 2:
					List<Integer> ids = service2.getBookIds();
					for (Integer integer : ids) {

						if (integer != null) {
							System.out.println(integer);
						} else {
							System.out.println("No Books Ids are available");
						}
					}
					break;
				case 3:
					List<BookBean> info = service2.getBooksInfo();
					for (BookBean bookBean : info) {

						if (bookBean != null) {
							System.out.println(bookBean);
						} else {
							System.out.println("Books info is not presernt");
						}
					}
					break;
				case 4:
					System.out.println("enter id");
					int returned = scanner.nextInt();
					System.out.println("enter book id");
					int book_id = scanner.nextInt();
					boolean returnBook = service2.reqReturnBook(returned, book_id);
					if (returnBook) {
						System.out.println("Book returned");
					} else {
						System.out.println("book not returned");
					}
					break;
				case 5:
					System.out.println("enter book id");
					int reqBook = scanner.nextInt();
					System.out.println("enter id");
					int reqId = scanner.nextInt();
					boolean book = service2.req(reqId, reqBook);
					if (book == true) {
						System.out.println("requested successfully");
					} else {
						System.out.println("book not found");
					}
					break;
				case 6:
					doReg();
				default:
					break;
				}
			} catch (Exception e) {
				System.out.println("Invalid Credentials");
			}
			break;

		} while (true);

	}
}