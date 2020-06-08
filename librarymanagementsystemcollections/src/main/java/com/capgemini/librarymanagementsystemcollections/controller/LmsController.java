package com.capgemini.librarymanagementsystemcollections.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.ValidationException;

import com.capgemini.librarymanagementsystemcollections.database.DataBase;
import com.capgemini.librarymanagementsystemcollections.dto.BookBean;
import com.capgemini.librarymanagementsystemcollections.dto.UserBean;
import com.capgemini.librarymanagementsystemcollections.factory.AdminFactory;
import com.capgemini.librarymanagementsystemcollections.factory.StudentFactory;
import com.capgemini.librarymanagementsystemcollections.factory.UserFactory;
import com.capgemini.librarymanagementsystemcollections.service.AdminService;
import com.capgemini.librarymanagementsystemcollections.service.StudentService;
import com.capgemini.librarymanagementsystemcollections.service.UserService;
import com.capgemini.librarymanagementsystemcollections.validation.ValidationAdminStudent;

@SuppressWarnings("restriction")
public class LmsController {
	
	public static void doReg() {
		DataBase.defaultDB();
		boolean flag = false;
		int regId1 = 0;
		String regName = null;
		String regMobile = null;
		String regEmail = null;
		String regPassword = null;
		String regRole = null;
		ValidationAdminStudent validation = new ValidationAdminStudent();

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		do {
			int i = 0;
			System.out.println("*********************Welcome to Library**********************");
			System.out.println("Press 1 to register");
			System.out.println("Press 2 to login");
			System.out.println("**************************************************************");
			UserService service1 = UserFactory.getUserService();
			try {
				String input = scanner.next();
				i = Integer.parseInt(input);
				if (i > 2 || i < 1) {
					flag = false;
					System.err.println("Enter valid numbers");
				} else {
					flag = true;
				}
			} catch (Exception e) {
				System.err.println("Enter only 1 and 2");
			}

			switch (i) {
			case 1:
				do {

					System.out.println("Enter ID :");
					String regId = scanner.next();
					try {
						regId1 = Integer.parseInt(regId);
						if (regId1 < 0) {
							flag = false;
							System.err.println("Enter valid numbers");
						} else {
							flag = true;
						}

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
						admin();
					} else if (auth.getRole().equalsIgnoreCase("student")) {
						student();
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

	@SuppressWarnings({ "resource", "unused" })
	public static void admin() {
		int i = 0;
		AdminService service = AdminFactory.getAdminService();
		BookBean bean = new BookBean();
		Scanner scanner = new Scanner(System.in);
		do {
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
			try {
				String input = scanner.next();
				i = Integer.parseInt(input);
				boolean flag;
				if (i > 9 || i < 1) {
					flag = false;
					System.err.println("Enter valid numbers");
				} else {
					flag = true;
				}
			} catch (Exception e) {
				System.err.println("Enter only 1 and 2");
			}
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

			switch (i) {
			case 1:
				do {
					System.out.println("Enter Book  ID :");
					String regId = scanner.next();
					try {
						bookId = Integer.parseInt(regId);
						if (bookId < 0) {
							flag = false;
							System.err.println("Enter valid numbers");
						} else {
							flag = true;
						}

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
							if (copies < 0) {
								flag = false;
								System.err.println("Enter valid numbers");
							} else {
								flag = true;
							}

						} catch (Exception e) {
							flag = false;
							System.err.println("copies should be of only numbers");
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
							if (ISBN < 0) {
								flag = false;
								System.err.println("Enter valid numbers");
							} else {
								flag = true;
							}

						} catch (Exception e) {
							flag = false;
							System.err.println("ISBN should be only numbers and should be less than 10 digits");
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
						if (year < 0) {
							flag = false;
							System.err.println("Enter valid copyright year");
						} else {
							flag = true;
						}
					} catch (Exception e) {
						flag = false;
						System.err.println("copyright year should contains only digits");
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
						if (bookId < 0) {
							flag = false;
							System.err.println("Enter valid numbers");
						} else {
							flag = true;
						}
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
					System.out.println(
							"***************************Welcome to Search Page********************************");
					System.out.println("Press 1 to Search the Book by Author");
					System.out.println("Press 2 to Search the Book by Title");
					System.out.println("Press 3 to Search the Book by Id");
					System.out.println("Press 0 for exit");
					int j = 0;
					try {
						String input = scanner.next();
						j = Integer.parseInt(input);
						if (j > 3 || j < 0) {
							flag = false;
							System.err.println("Enter valid numbers");
						} else {
							flag = true;
						}
					} catch (Exception e) {
						System.err.println("Enter only 1 and 2");
					}
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
						System.out.println(String.format("%-10s %-10s %-13s %-12s %-10s %-15s %-15s %-10s %-10s %s",
								"BookId", "BookName", "BookCategory", "BookAuthor", "Copies", "BookPublisher",
								"PublisherName", "ISBN", "CopyrightYear", "Status"));
						BookBean searchByAuthor = service.searchBookAuthor(bookAuthor);
						if (searchByAuthor != null) {
							System.out.println(searchByAuthor.toString());
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
						System.out.println(String.format("%-10s %-10s %-13s %-12s %-10s %-15s %-15s %-10s %-10s %s",
								"BookId", "BookName", "BookCategory", "BookAuthor", "Copies", "BookPublisher",
								"PublisherName", "ISBN", "CopyrightYear", "Status"));

						BookBean searchByName = service.searchBookTitle(bookName);
						if (searchByName != null) {
							System.out.println(searchByName.toString());
						} else {
							System.out.println("Book Not Found");
						}
						break;

					case 3:
						do {

							System.out.println("Enter Book ID :");
							String bookId1 = scanner.next();
							try {
								bookId = Integer.parseInt(bookId1);
								if (bookId < 0) {
									flag = false;
									System.err.println("Enter valid numbers");
								} else {
									flag = true;
								}
							} catch (Exception e) {
								flag = false;
								System.err.println("Id should contains only digits");
							}

						} while (!flag);

						BookBean bean5 = new BookBean();
						bean5.setBid(bookId);
						System.out.println(String.format("%-10s %-10s %-13s %-12s %-10s %-15s %-15s %-10s %-10s %s",
								"BookId", "BookName", "BookCategory", "BookAuthor", "Copies", "BookPublisher",
								"PublisherName", "ISBN", "CopyrightYear", "Status"));

						BookBean searchById = service.searchBookType(bookId);
						if (searchById != null) {
							System.out.println(searchById.toString());
						} else {
							System.out.println("Book Not Found");
						}
						break;
					case 0:
						admin();
						break;
					}
				} while (true);
			case 4:
				do {

					System.out.println("Enter Book ID :");
					String bookId1 = scanner.next();
					try {
						bookId = Integer.parseInt(bookId1);
						if (bookId < 0) {
							flag = false;
							System.err.println("Enter valid numbers");
						} else {
							flag = true;
						}
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
				ArrayList<Integer> ids = service.getBookIds();
				try {
					for (Integer books : ids) {
						if (books != null) {
							System.out.println(books);
						} else {
							System.out.println("No Books Ids are available");
						}
					}
				} catch (Exception e) {
					System.err.println("No Books present");
				}

				break;
			case 6:

				List<BookBean> info = service.getBooksInfo();
				System.out.println(String.format("%-10s %-10s %-13s %-12s %-10s %-15s %-15s %-10s %-10s %s", "BookId",
						"BookName", "BookCategory", "BookAuthor", "Copies", "BookPublisher", "PublisherName", "ISBN",
						"CopyrightYear", "Status"));

				for (BookBean bookBean : info) {

					if (bookBean != null) {
						System.out.println(bookBean);
					} else {
						System.out.println("Books info is not present");
					}
				}
				break;
			case 7:
				int userID = 0;
				int book_id = 0;
				do {

					System.out.println("Enter User ID :");
					String regId = scanner.next();
					try {
						userID = Integer.parseInt(regId);
						if (userID < 0) {
							flag = false;
							System.err.println("Enter valid numbers");
						} else {
							flag = true;
						}
					} catch (Exception e) {
						flag = false;
						System.err.println("Id should contains only digits");
					}

				} while (!flag);

				do {

					System.out.println("Enter Book ID :");
					String regId = scanner.next();
					try {
						book_id = Integer.parseInt(regId);
						if (book_id < 0) {
							flag = false;
							System.err.println("Enter valid numbers");
						} else {
							flag = true;
						}
					} catch (Exception e) {
						flag = false;
						System.err.println("Id should contains only digits");
					}

				} while (!flag);

				boolean check4 = service.issueBook(userID, book_id);
				if (check4) {
					System.out.println("-----------------------------------------------");
					System.out.println("Book Issued");
				} else {
					System.out.println("-----------------------------------------------");
					System.out.println("Book not issued");
				}
				break;
			case 8:
				int userID1 = 0;
				int book_id1 = 0;
				do {

					System.out.println("Enter User ID :");
					String regId = scanner.next();
					try {
						userID1 = Integer.parseInt(regId);
						if (userID1 < 0) {
							flag = false;
							System.err.println("Enter valid numbers");
						} else {
							flag = true;
						}
					} catch (Exception e) {
						flag = false;
						System.err.println("Id should contains only digits");
					}

				} while (!flag);

				do {

					System.out.println("Enter Book ID :");
					String regId = scanner.next();
					try {
						book_id1 = Integer.parseInt(regId);
						if (book_id1 < 0) {
							flag = false;
							System.err.println("Enter valid numbers");
						} else {
							flag = true;
						}
					} catch (Exception e) {
						flag = false;
						System.err.println("Id should contains only digits");
					}

				} while (!flag);

				boolean check5 = service.returnBook(userID1, book_id1);
				if (check5 == true) {
					System.out.println("Book Returned");
				} else {
					System.out.println("Book cannot be returned");
				}
			case 9:
				doReg();
			}
		} while (true);
	}

	@SuppressWarnings({ "resource"})
	public static void student() {

		Scanner scanner = new Scanner(System.in);
		StudentService service2 = StudentFactory.getStudentService();
		do {
			try {
				do {
					System.out.println("**********************Student Page**********************");
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
					int choice2 = 0;
					try {
						String input = scanner.next();
						choice2 = Integer.parseInt(input);
						if (choice2 > 6 || choice2 < 1) {
							flag = false;
							System.err.println("Enter valid numbers");
						} else {
							flag = true;
						}
					} catch (Exception e) {
						System.err.println("Enter only 1 and 2");
					}

					switch (choice2) {
					case 1:
						System.out.println("**************************Search Page*********************");
						System.out.println("Press 1 to Search the Book by Author");
						System.out.println("Press 2 to Search the Book by Title");
						System.out.println("Press 3 to Search the Book by Id");
						System.out.println("Press 0 to exit");
						int i = 0;
						try {
							String input = scanner.next();
							i = Integer.parseInt(input);
							if (i > 3 || i < 0) {
								flag = false;
								System.err.println("Enter valid numbers");
							} else {
								flag = true;
							}
						} catch (Exception e) {
							System.err.println("Enter only 1 and 2");
						}

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
							System.out.println(String.format("%-10s %-10s %-13s %-12s %-10s %-15s %-15s %-10s %-10s %s",
									"BookId", "BookName", "BookCategory", "BookAuthor", "Copies", "BookPublisher",
									"PublisherName", "ISBN", "CopyrightYear", "Status"));

							BookBean searchByAuthor = service2.searchBookAuthor(bookAuthor);
							if (searchByAuthor != null) {
								System.out.println(searchByAuthor.toString());
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
							System.out.println(String.format("%-10s %-10s %-13s %-12s %-10s %-15s %-15s %-10s %-10s %s",
									"BookId", "BookName", "BookCategory", "BookAuthor", "Copies", "BookPublisher",
									"PublisherName", "ISBN", "CopyrightYear", "Status"));

							BookBean searchByName = service2.searchBookTitle(bookName);
							if (searchByName != null) {
								System.out.println(searchByName.toString());
							} else {
								System.out.println("Book Not Found");
							}
							break;

						case 3:
							do {

								System.out.println("Enter Book ID :");
								String bookId1 = scanner.next();
								try {
									bookId = Integer.parseInt(bookId1);
									if (bookId < 0) {
										flag = false;
										System.err.println("Enter valid numbers");
									} else {
										flag = true;
									}
								} catch (Exception e) {
									flag = false;
									System.err.println("Id should contains only digits");
								}

							} while (!flag);

							BookBean bean5 = new BookBean();
							bean5.setBid(bookId);
							System.out.println(String.format("%-10s %-10s %-13s %-12s %-10s %-15s %-15s %-10s %-10s %s",
									"BookId", "BookName", "BookCategory", "BookAuthor", "Copies", "BookPublisher",
									"PublisherName", "ISBN", "CopyrightYear", "Status"));

							BookBean searchById = service2.searchBookType(bookId);
							if (searchById != null) {
								System.out.println(searchById.toString());
							} else {
								System.out.println("Book Not Found");
							}
							break;
						case 0:
							student();
							break;
						}
					case 2:
						ArrayList<Integer> ids = service2.getBookIds();
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
						System.out.println(String.format("%-10s %-10s %-13s %-12s %-10s %-15s %-15s %-10s %-10s %s",
								"BookId", "BookName", "BookCategory", "BookAuthor", "Copies", "BookPublisher",
								"PublisherName", "ISBN", "CopyrightYear", "Status"));

						for (BookBean bookBean : info) {

							if (bookBean != null) {
								System.out.println(bookBean);
							} else {
								System.out.println("Books info is not presernt");
							}
						}
						break;
					case 4:
						int userID = 0;
						int book_id = 0;
						do {

							System.out.println("Enter User ID :");
							String regId = scanner.next();
							try {
								userID = Integer.parseInt(regId);
								if (userID < 0) {
									flag = false;
									System.err.println("Enter valid numbers");
								} else {
									flag = true;
								}
							} catch (Exception e) {
								flag = false;
								System.err.println("Id should contains only digits");
							}

						} while (!flag);

						do {

							System.out.println("Enter Book ID :");
							String regId = scanner.next();
							try {
								book_id = Integer.parseInt(regId);
								if (book_id < 0) {
									flag = false;
									System.err.println("Enter valid numbers");
								} else {
									flag = true;
								}
							} catch (Exception e) {
								flag = false;
								System.err.println("Id should contains only digits");
							}

						} while (!flag);

						boolean returnBook = service2.reqReturnBook(userID, book_id);
						if (returnBook) {
							System.out.println("Book returned");
						} else {
							System.out.println("book not returned");
						}
						break;
					case 5:
						int userID1 = 0;
						int book_id1 = 0;
						do {

							System.out.println("Enter User ID :");
							String regId = scanner.next();
							try {
								userID1 = Integer.parseInt(regId);
								if (userID1 < 0) {
									flag = false;
									System.err.println("Enter valid numbers");
								} else {
									flag = true;
								}
							} catch (Exception e) {
								flag = false;
								System.err.println("Id should contains only digits");
							}

						} while (!flag);

						do {

							System.out.println("Enter Book ID :");
							String regId = scanner.next();
							try {
								book_id1 = Integer.parseInt(regId);
								if (book_id1 < 0) {
									flag = false;
									System.err.println("Enter valid numbers");
								} else {
									flag = true;
								}
							} catch (Exception e) {
								flag = false;
								System.err.println("Id should contains only digits");
							}

						} while (!flag);

						boolean book = service2.req(userID1, book_id1);
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
				} while (true);
			} catch (Exception e) {
				System.out.println("Invalid Credentials");
			}
			break;

		} while (true);

	}

}