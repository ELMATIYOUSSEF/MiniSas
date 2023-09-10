package controller;

import model.Book;
import service.BookService;

import org.apache.commons.lang3.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static console.BookManagement.menuBook;
import static repository.BibliothecaireRepository.getBookInfo;
import static repository.BibliothecaireRepository.login;

public class BookController {
    BookService bookService = new BookService();
    List<Book> books;
    Book book ;
    public  void addBook(){

        Scanner myObj = new Scanner(System.in);
        System.out.print("\u001B[32m");
        System.out.println("<=============== Add book ===============> \n");
        System.out.println("Enter ISBN:");
        int ISBN = myObj.nextInt();
        myObj.nextLine();
        System.out.println("Enter Title:");
        String title = myObj.nextLine();
        System.out.println("Enter Author:");
        String author = myObj.nextLine();
        System.out.println("Enter Quantity:");
        int quantity = myObj.nextInt();
        String status = "disponible";
        System.out.print("\u001B[0m");

        Book book = new Book(ISBN, title, author, quantity, status);

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
        System.out.print("\u001B[32m");
        System.out.println("<=============== ALL book ===============> \n");

        System.out.print("\u001B[0m");
        if(books!= null)books.clear();
        books =bookService.getAll() ;
        HeaderTable();
        for (Book book1 : books) {
            displayBookInfoInTable(book1);
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
            HeaderTable();
            displayBookInfoInTable(book1);      }
        else {
            System.out.print("\u001B[31m");
            System.out.println("Error: Book not found. Please try again.");
            System.out.print("\u001B[0m");
        }
    }

    public void searchBooks(){
        if(books!= null)books.clear();

        Scanner myObj = new Scanner(System.in);
        System.out.print("\u001B[32m");
        System.out.println("<===============  Search Book use Title of book or Name of author ===============> \n");
        System.out.println("Enter le titre au bien nom  de le auteur : ");
        String searchCriteria = myObj.nextLine();
       books= bookService.searchBooks(searchCriteria);
        HeaderTable();
        for (Book book1 : books) {
            displayBookInfoInTable(book1);
        }
    }
    public void deleteBook() throws SQLException {
        Scanner myObj = new Scanner(System.in);
        System.out.print("\u001B[32m");
        System.out.println("<=============== Deleting book ===============> \n");
        System.out.println("Enter ISBN:");
        int ISBN = myObj.nextInt();
        myObj.nextLine();
        System.out.println("es-tu sûr de vouloir supprimer ce livre :");
        System.out.println("1 : Oui ");
        System.out.println("2 : No ");
        int reponse = myObj.nextInt();
        if (reponse == 1){
            bookService.delete(ISBN);
        }else menuBook();
    }

    public void update() throws SQLException {
        Scanner myObj = new Scanner(System.in);
        System.out.print("\u001B[32m");
        System.out.println("<=============== Updating book ===============> \n");
        System.out.println("Enter ISBN:");
        int ISBN = myObj.nextInt();
        Book book1 = bookService.getbook(ISBN);
        if (book1 != null) {
            HeaderTable();
            displayBookInfoInTable(book1);
        }
        else  {
            System.out.print("\u001B[31m");
            System.out.println("Error: Book not found. Please try again.");
            System.out.print("\u001B[0m");
        }
        myObj.nextLine();
        System.out.println("Titre :");
        String Titre = myObj.nextLine();
        if (!Titre.isEmpty()) {
            book1.setTitle(Titre);
        }
        System.out.println("Auteur : ");
        String Auteur = myObj.nextLine();
        if (!Auteur.isEmpty()) {
            book1.setAuthor(Auteur);
        }
        System.out.println("Quantite :");
        int Quantite = myObj.nextInt();
        if (Quantite!=0) {
            book1.setQuantity(Quantite);
        }
        myObj.next() ;
        System.out.println("Status :");
        System.out.println("1 : disponible ");
        System.out.println("2 : emprunte ");
        int reponse = myObj.nextInt();
        myObj.next();
        if (reponse != 0 && reponse < 3 ) {
            if (reponse == 1){
                book1.setStatus("disponible");
            }else book1.setStatus("emprunte");
        }
        bookService.update(book1);
    }
     Boolean checkISNullString(String str){
        if(str == null){
            return true ;
        }
        return false ;
     }
    Boolean checkISNullInt(int Int){
        if(Int == 0){
            return  true ;
        }
        return  false ;
    }

    public void displayBookInfoInTable(Book book) {


        String formattedOutput = String.format(" %-9d| %-15s| %-13s| %-9d| %-9s",
                book.getISBN(),
                StringUtils.abbreviate(book.getTitle(), 15),
                StringUtils.abbreviate(book.getAuthor(), 13),
                book.getQuantity(),
                StringUtils.abbreviate(book.getStatus(), 9)
        );

        System.out.println(formattedOutput);

        System.out.println("===================================================================");
    }
    public void HeaderTable(){
        System.out.println("===================================================================");
        System.out.println(" ISBN     | Titre          | Auteur      | Quantité | Status    ");
        System.out.println("===================================================================");
    }
}
