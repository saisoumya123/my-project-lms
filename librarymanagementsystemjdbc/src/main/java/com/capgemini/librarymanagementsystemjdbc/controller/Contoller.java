package com.capgemini.librarymanagementsystemjdbc.controller;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.UserBean;
import com.capgemini.librarymanagementsystemjdbc.exception.LMSException;
import com.capgemini.librarymanagementsystemjdbc.factory.LMSFactory;
import com.capgemini.librarymanagementsystemjdbc.service.AdminService;
import com.capgemini.librarymanagementsystemjdbc.service.StudentService;
import com.capgemini.librarymanagementsystemjdbc.service.UserService;
import com.capgemini.librarymanagementsystemjdbc.validation.LMSValidation;

public class Contoller {

	public static void doReg() {
		boolean flag = false;
		int regId1 = 0;
		String regName = null;
		String regMobile = null;
		String regEmail = null;
		String regPassword = null;
		String regRole = null;
		LMSValidation validation = new LMSValidation();

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		do {
			try {
				System.out.println("*********************Welcome to Library**********************");
				System.out.println("Press 1 to register");
				System.out.println("Press 2 to login");
				System.out.println("Press 3 to EXIT");
				System.out.println("**************************************************************");
				UserService service = LMSFactory.getUserService();
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
						} catch (LMSException e) {
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
							System.err.println("Mobile Number should contains only numbers");
						} catch (LMSException e) {
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
							System.err.println("Email should be proper with proper extension .com or .org");
						} catch (LMSException e) {
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
						} catch (LMSException e) {
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
						} catch (LMSException e) {
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
						boolean check = service.register(bean);
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
						} catch (LMSException e) {
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
						} catch (LMSException e) {
							flag = false;
							System.err.println(e.getMessage());
						}
					} while (!flag);
					try {
						UserBean loginInfo = service.login(regEmail, regPassword);
						String role = loginInfo.getRole();
						if (role.equalsIgnoreCase("admin")) {

							admin();

						} else if (role.equalsIgnoreCase("student")) {

							student();

						} else {
							System.out.println("Invalid user and password");
						}
					} catch (Exception e) {
						System.out.println("Invalid user credentials");
					}
					break;
				case 3:
					System.exit(0);
				default:
					System.out.println("enter valid choice");
					break;
				} // end of switch-case
			} catch (InputMismatchException e) {
				System.out.println("Enter digits");
				doReg();
			}
		} while (true); // end of do-while
	} // end of doReg()

	public static void admin() {
		AdminService service = LMSFactory.getAdminService();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		do {
			try {
				System.out.println("1: Add Books");
				System.out.println("2: Update the Book");
				System.out.println("3: Search the Book");
				System.out.println("4: Remove the Book");
				System.out.println("5: Get the Book Id's");
				System.out.println("6: Get the Book Information");
				System.out.println("7: Issue the Book");
				System.out.println("8: Return book");
				System.out.println("9: Main");
				System.out.println("0: Exit");
				boolean flag = false;
				LMSValidation validation = new LMSValidation();
				int bookId = 0;
				String bookAuthor = null;
				String bookName = null;
				String bookCategory = null;
				String bookPublisher = null;
				int ISBN = 0;
				String status = null;
				int copies = 0;
				int year = 0;
				int choice1 = scanner.nextInt();
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
						} catch (LMSException e) {
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
						} catch (LMSException e) {
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
						} catch (LMSException e) {
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
						} catch (LMSException e) {
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
						} catch (LMSException e) {
							flag = false;
							System.err.println(e.getMessage());
						}
					} while (!flag);

					do {

						System.out.println("Enter Copyright Year :");
						String copyrightYear = scanner.next();
						try {
							year = Integer.parseInt(copyrightYear);
						} catch (Exception e) {
							flag = false;
							System.err.println("Id should contains only digits");
						}

					} while (!flag);
					BookBean bean2 = new BookBean();
					bean2.setBookId(bookId);
					bean2.setBookName(bookName);
					bean2.setBookCategory(bookCategory);
					bean2.setBookAuthor(bookAuthor);
					bean2.setBookCopies(copies);
					bean2.setBookPublisherName(bookPublisher);
					bean2.setBookIsbn(ISBN);
					bean2.setBookCopyRight(year);
					bean2.setStatus(status);

					boolean bookAdded = service.addBook(bean2);
					if (bookAdded == false) {
						System.out.println("Book already exists");
					} else {
						System.out.println("Book added");
					}
					break;
				case 2:
					do {
						System.out.println("Enter BookID :");
						String bookId1 = scanner.next();
						try {
							bookId = Integer.parseInt(bookId1);
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
						} catch (LMSException e) {
							flag = false;
							System.err.println(e.getMessage());
						}
					} while (!flag);
					BookBean book = new BookBean();
					book.setBookId(bookId);
					book.setBookName(bookName);
					boolean updated = service.updateBook(book);
					if (updated == false) {
						System.out.println("book updated");
					} else {
						System.out.println("book is not updated");
					}
					break;
				case 3:
					BookBean bean = new BookBean();
					System.out.println(
							"***************************Welcome to Search Page********************************");
					do {
						try {
							System.out.println("1: Search by Book Title");
							System.out.println("2: Search by Book Author");
							System.out.println("3: Search by Book Id");
							System.out.println("0: exit");
							int choice2 = scanner.nextInt();
							switch (choice2) {
							case 1:
								do {
									try {
										System.out.println("Search the book by the Book Name:");
										bookName = scanner.next();
										validation.validatedName(bookName);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Name should contains only Alphabates");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								bean.setBookName(bookName);
								BookBean bookBean = service.searchBookByTitle((bookName));
								System.out.println(bookBean);
								break;

							case 2:
								do {
									try {
										System.out.println("Search the book by the Author Name:");
										bookAuthor = scanner.next();
										validation.validatedName(bookAuthor);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Name should contains only Alphabates");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								bean.setBookAuthor(bookAuthor);

								BookBean bookBean2 = service.searchBookByAuthor(bookAuthor);
								System.out.println(bookBean2);
								break;

							case 3:
								do {
									try {
										System.out.println("Search the book by the Book-Id :");
										bookId = scanner.nextInt();
										validation.validatedId(bookId);
										flag = true;
									} catch (LMSException e) {
										flag = false;
										System.err.println("Book-Id should contains only digits");
									}
								} while (!flag);

								bean.setBookId(bookId);
								BookBean bookBean3 = service.searchBookById(bookId);
								System.out.println(bookBean3);
								break;
							case 0:
								admin();
								break;
							default:
								System.out.println("Enter Valid Choice");
								break;
							} // end of switch case
						} catch (InputMismatchException e) {
							System.out.println("Enter Digits");
						}
					} while (true);
					// end of search
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
						bean6.setBookId(bookId);
						boolean remove = service.removeBook(bookId);
						if (remove == false) {
							System.out.println("The Book is not removed");
						} else {
							System.out.println("The Book is removed");
						}
					}

					break;
				case 5:
					List<BookBean> ids = service.getBookIds();
					for (BookBean integer : ids) {
						if (integer != null) {

							System.out.println(integer.getBookId());
						} else {
							System.out.println("No Books Ids are available");
						}
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
					boolean bookIssue = service.issueBook(issueId, userId);
					if (bookIssue) {
						System.out.println("-----------------------------------------------");
						System.out.println("Book Issued");
					} else {
						System.out.println("-----------------------------------------------");
						System.out.println("Book not issued");
					}
					break;
				case 8:
					System.out.println("Enter Book id");
					int returnBook = scanner.nextInt();
					System.out.println("Enter user id");
					int userId1 = scanner.nextInt();
					boolean bookReturn = service.returnBook(returnBook, userId1);
					if (bookReturn) {
						System.out.println("Book is returned");
					} else {
						System.out.println("Book not returned");
					}
					break;
				case 9:
					doReg();
					break;
				case 0:
					System.exit(0);
				} // end of switch-case
			} catch (InputMismatchException e) {
				System.out.println("Enter digits");
				admin();
			}
		} while (true); // end of do-while
	} // end of admin()

	public static void student() {
		boolean flag = false;
		int bookId = 0;
		int userId = 0;
		String bookAuthor = null;
		String bookName = null;

		BookBean bean = new BookBean();
		UserBean usersBean = new UserBean();
		LMSValidation validation = new LMSValidation();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		StudentService service = LMSFactory.getStudentService();

		do {
			try {
				System.out.println("1: Search the Book");
				System.out.println("2: to Get the Book Id's");
				System.out.println("3: Get the Book Information");
				System.out.println("4: Request Return book");
				System.out.println("5: Request book");
				System.out.println("6: Main");
				System.out.println("0: Exit");
				// try {
				int choice = scanner.nextInt();
				switch (choice) {
				case 1:
					do {
						try {
							System.out.println("1: Search by Book Title");
							System.out.println("2: Search by Book Author");
							System.out.println("3: Search by Book Id");
							System.out.println("0: exit");
							int choice2 = scanner.nextInt();
							switch (choice2) {
							case 1:
								do {
									try {
										System.out.println("Search the book by the Book Title:");
										bookName = scanner.next();
										validation.validatedName(bookName);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Name should contains only Alphabates");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}

								} while (!flag);

								bean.setBookName(bookName);
								BookBean bookBean = service.searchBookByTitle((bookName));
								System.out.println(bookBean);
								break;

							case 2:
								do {
									try {
										System.out.println("Search the book by the Author Name:");
										bookAuthor = scanner.next();
										validation.validatedName(bookAuthor);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Name should contains only Alphabates");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								bean.setBookAuthor(bookAuthor);

								BookBean bookBean2 = service.searchBookByAuthor(bookAuthor);
								System.out.println(bookBean2);
								break;

							case 3:
								do {
									try {
										System.out.println("Search the book by the Book-Id :");
										bookId = scanner.nextInt();
										validation.validatedId(bookId);
										flag = true;
									} catch (Exception e) {
										flag = false;
										System.err.println("Book-Id should contains only digits");
									}
								} while (!flag);

								bean.setBookId(bookId);
								BookBean bookBean3 = service.searchBookById(bookId);
								System.out.println(bookBean3);
								break;
							case 0:
								admin();
								break;
							default:
								System.out.println("Enter Valid Choice");
								break;
							} // end of switch case
						} catch (InputMismatchException e) {
							System.out.println("Enter Digits");
						}
					} while (true);
				case 2:
					LinkedList<BookBean> ids = service.getBookIds();
					for (BookBean integer : ids) {
						if (integer != null) {
							System.out.println(integer.getBookId());
						} else {
							System.out.println("No Books Ids are available");
						}
					}
					break;
				case 3:
					List<BookBean> info = service.getBooksInfo();
					for (BookBean bookBean : info) {
						if (bookBean != null) {
							System.out.println(bookBean);
						} else {
							System.out.println("Books info is not present");
						}
					}
					break;
				case 4:
//					do {
//						try {
//							System.out.println("enter Book Id");
//							bookId = scanner.nextInt();
//							validation.validatedId(bookId);
//							flag = true;
//						} catch (Exception e) {
//							flag = false;
//							System.err.println("Id should contains only digits");
//						}
//					} while (!flag);
//
//					do {
//						try {
//							System.out.println("enter User Id");
//							userId = scanner.nextInt();
//							validation.validatedId(userId);
//							flag = true;
//						} catch (Exception e) {
//							flag = false;
//							System.err.println("Id should contains only digits");
//						}
//					} while (!flag);
					
					do {

						System.out.println("Enter book ID :");
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
					
					do {

						System.out.println("Enter user ID :");
						String userId1 = scanner.next();
						try {
							userId = Integer.parseInt(userId1);
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
					bean.setBookId(bookId);
					usersBean.setId(userId);
					try {
						boolean isreturned = service.requestReturnBook(bookId, userId);
						if (isreturned) {
							System.out.println("Book Returned");
						} else {
							System.out.println("Book cannot be Returned");
						}

					} catch (Exception e) {
						System.out.println("Invalid data request book cannot be returned");
					}
					break;
				case 5:
//					do {
//						try {
//							System.out.println("enter Book Id");
//							bookId = scanner.nextInt();
//							validation.validatedId(bookId);
//							flag = true;
//						} catch (Exception e) {
//							flag = false;
//							System.err.println("Id should contains only digits");
//						}
//					} while (!flag);
					
					do {

						System.out.println("Enter book ID :");
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

//					do {
//						try {
//							System.out.println("enter User Id");
//							userId = scanner.nextInt();
//							validation.validatedId(userId);
//							flag = true;
//						} catch (Exception e) {
//							flag = false;
//							System.err.println("Id should contains only digits");
//						}
//					} while (!flag);
					
					do {

						System.out.println("Enter user ID :");
						String userId1 = scanner.next();
						try {
							userId = Integer.parseInt(userId1);
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

					bean.setBookId(bookId);
					usersBean.setId(userId);
					try {
						boolean request = service.requestReturnBook(bookId, userId);
						if (request) {
							System.out.println("Book Requested Successfully");
						} else {
							System.out.println("Book NOT requested");
						}

					} catch (Exception e) {
						System.out.println("Invalid data request book cannot be requested");
					}
					break;
				case 6:
					doReg();
					break;
				case 0:
					System.exit(0);
				default:
					System.out.println("Enter digits");
					break;
				} // end of switch case
			} catch (InputMismatchException e) {
				System.out.println("Enter digits");
				student();
			}
		} while (true); // end of while

	} // end of student()
} // end of class
