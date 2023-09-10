<H2> Console Library Management Application </H2>
Overview
This repository contains the source code and documentation for a console-based library management application developed in Java. The application aims to address the challenges faced by the municipal library of Paris in managing its books and providing an efficient user experience. It offers features for automated book management, efficient book search, loan and return management, and statistical reporting.

Project Objectives
The project aims to achieve the following educational and technical objectives:

Understand the requirements of a library management application.
Design and model the application using UML (Unified Modeling Language).
Master object-oriented programming in Java.
Utilize relational databases and interact with them in Java.
Implement database connectivity and manipulation using JDBC (Java Database Connectivity).
Manage the source code of the project with Git.
Resolve issues and debug the application code.
Apply software development skills by implementing application features.
Collaborate effectively in a team.
Project Brief
Context
The municipal library of Paris faces several challenges related to manual book management and the lack of an efficient tracking system. These challenges include:

Inefficient Management: Librarians must perform all book-related tasks manually, which can lead to errors and time wastage. It's challenging to maintain an up-to-date database and track the status of each book (available or borrowed).
Search Difficulties: Library users struggle to find the books they want to borrow. The absence of an efficient search system makes the process tedious and can discourage users.
Lack of Statistics: There is no easy way to obtain statistics on available books, borrowed books, and lost books. This limits the library's ability to analyze and optimize its collections.
In summary, there is a need to develop a console-based library management application in Java that enables automated book management, efficient search, loan and return management, and the generation of statistical reports. This application aims to address the issues of inefficient management and search difficulties faced by the library while improving the user experience.

Requirements
To address the mentioned challenges, the library requires a console-based library management application with the following features:

Automated Book Management: The application should allow librarians to manage books automatically, including adding new books, updating information for existing books, and deleting books from the database.
Efficient Search System: The application should provide users with a quick and efficient way to search for books by title or author, making it easier to discover available books in the library.
Loan and Return Management: Librarians should be able to record book loans and returns. The application should also maintain the status of each book (available or borrowed).
Statistical Reporting: The application should generate statistical reports on available books, borrowed books, and lost books. This will enable the library to analyze trends and make informed decisions to optimize its book collection.
User Stories
The following user stories outline the specific functionalities of the application:

1. Adding a New Book
As a user, I want to be able to add a new book to the library by providing its title, author, and ISBN number to keep track of available books.

Definition of Done: The user can enter book information (title, author, ISBN) via the console interface. The application validates the entered data, adds the book to the library database with an initial "available" status, and displays a confirmation message to the user.

2. Displaying a List of Available Books
As a user, I want to view a complete list of available books in the library, including their title, author, and status (available or borrowed).

Definition of Done: The user can select the option to display the list of available books in the console application. The application queries the database to retrieve all books with the "available" status and displays their information (title, author, status) on the screen.

3. Searching for a Book
As a user, I want to be able to search for a book by its title or author to quickly find books of interest.

Definition of Done: The user can enter a title or author in the console application to perform a search. The application queries the database to find books that match the search criteria and displays the results on the screen.

4. Borrowing a Book
As a user, I want to be able to borrow a book by providing its ISBN number to update its status in the library.

Definition of Done: The user can enter the ISBN of an available book to borrow it via the console application. The application checks if the book is available, updates its status to "borrowed" in the database, records borrower information (name, member number, etc.), records the loan date, and displays a confirmation message to the user.

5. Returning a Borrowed Book
As a user, I want to be able to return a borrowed book by providing its ISBN number to update its status in the library.

Definition of Done: The user can enter the ISBN of a borrowed book to return it via the console application. The application checks if the book is indeed borrowed, updates its status to "available" in the database, removes borrower information and loan date, and displays a confirmation message to the user.

6. Displaying a List of Borrowed Books
As a user, I want to view a list of borrowed books, including borrower information and loan dates, to track ongoing loans.

Definition of Done: The user can select the option to display the list of borrowed books in the console application. The application queries the database to retrieve all books with the "borrowed" status and displays their information (title, author, borrower information, loan date) on the screen.

7. Deleting a Book
As a user, I want to be able to delete a book from the library by providing its ISBN number to remove obsolete or lost books from the collection.

Definition of Done: The user can enter the ISBN of a book to delete it from the library database via the console application. The application checks if the book exists in the database, removes it from the collection, and displays a confirmation message to the user.

8. Modifying Book Information
As a user, I want to be able to update the information of an existing book (title, author, etc.) by providing its ISBN number to keep the details up to date.

Definition of Done: The user can enter the ISBN of a book to access its information via the console application. The application displays the current information of the book and allows the user to modify relevant fields (title, author, etc.). Once the changes are made, the application updates the book's information in the database and displays a confirmation message to the user.

9. Generating Statistical Reports
As a user, I want to be able to generate a report containing statistics on available books, borrowed books, and lost books to track the library's status.

Definition of Done: The user can select the option to generate a report in the console application. The application analyzes the database, generates a detailed report containing statistics on available books, borrowed books, lost books, etc., and displays the report on the screen or saves it to a file.
