Feature: Admin add-books

Background: Admin is on login page
When Admin gives "alex@gmail.com" , "alex@123"
Then Admin should be logged-in

Scenario: Admin should be able to add books

When Admin enters <bookId>, <bookName>, <bookAuthor>, <bookCategory>, <