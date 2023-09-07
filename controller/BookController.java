package controller;

import model.Book;
import service.BookService;

import org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static repository.BibliothecaireRepository.getBookInfo;
import static repository.BibliothecaireRepository.login;

public class BookController {
    BookService bookService = new BookService();
    Book book ;
    public  void addBook(){

        Scanner myObj = new Scanner(System.in);
        System.out.print("\u001B[32m");
        System.out.println("<=============== Add book ===============> \n");
        System.out.println("Enter ISBN:");
        int ISBN = myObj.nextInt();
        myObj.nextLine(); // Consume the newline character
        System.out.println("Enter Title:");
        String title = myObj.nextLine();
        System.out.println("Enter Author:");
        String author = myObj.nextLine();
        System.out.println("Enter Quantity:");
        int quantity = myObj.nextInt();
        String status = "disponible";
        System.out.print("\u001B[0m");

        Book book = new Book(ISBN, title, author, quantity, status);

        // Assuming that bookService.save() returns a boolean indicating success
        if (bookService.save(book)!= null) {
            System.out.print("\u001B[34m");
            System.out.println("Book added successfully!");
            System.out.print("\u001B[0m");
        } else {
            System.out.print("\u001B[31m");
            System.out.println("Error: Please try again.");
            System.out.print("\u001B[0m");
        }
    }
    public void showAll() {
        Scanner myObj = new Scanner(System.in);
        System.out.print("\u001B[32m");
        System.out.println("<=============== Add book ===============> \n");
        System.out.println("Enter ISBN:");
        int ISBN = myObj.nextInt();
        myObj.nextLine(); // Consume the newline character
        System.out.println("Enter Title:");
        String title = myObj.nextLine();
        System.out.println("Enter Author:");
        String author = myObj.nextLine();
        System.out.println("Enter Quantity:");
        int quantity = myObj.nextInt();
        String status = "available";
        System.out.print("\u001B[0m");

        Book book = new Book(ISBN, title, author, quantity, status);

        // Assuming that bookService.save() returns a boolean indicating success
        if (bookService.save(book)!= null) {
            System.out.println("Book added successfully!");
        } else {
            System.out.println("Error: Please try again.");
        }
    }

    public  void showBook() throws SQLException {
        Scanner myObj = new Scanner(System.in);
        System.out.print("\u001B[32m");
        System.out.println("<===============  Show book  ===============> \n");
        System.out.println("Enter ISBN :");
        int ISBN = myObj.nextInt();
       Book book1 = bookService.getbook(ISBN);
        if (book1 != null) {
            displayBookInfoInTable(book1);      }
        else   System.out.println("Error: Book not found. Please try again.");
    }
    public void displayBookInfoInTable(Book book) {
        System.out.println("===================================================================");
        System.out.println(" ISBN     | Titre           | Auteur       | Quantité   | Status    ");
        System.out.println("===================================================================");

        String formattedOutput = String.format(" %-9d| %-15s| %-13s| %-9d| %-9s",
                book.getISBN(),
                StringUtils.abbreviate(book.getTitle(), 15),
                StringUtils.abbreviate(book.getAuthor(), 13),
                book.getQuantity(),
                StringUtils.abbreviate(book.getStatus(), 9)
        );

        System.out.println(formattedOutput);

        System.out.println("===========================================");
    }
}
