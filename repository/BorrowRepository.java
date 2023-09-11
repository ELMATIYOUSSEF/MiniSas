package repository;

import database.database;
import model.Beneficiaries;
import model.Bibliothecaire;
import model.Book;
import model.Borrow;
import service.BeneficiariesService;
import service.BibliothecaireService;
import service.BookService;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import static database.database.connect;
import static model.Book.*;
import static service.BorrowService.calculateReturnDate;

public class BorrowRepository {
    public BorrowRepository() {
        this.bibliothecaireService = new BibliothecaireService();
    }
    BeneficiariesService beneficiariesService = new BeneficiariesService();
    BookService bookService = new BookService();
    BibliothecaireService bibliothecaireService ;

    public static boolean borrowBook(Book book, int idBib, int idBen , String msg , Date returnDate ,Date currentDate ) {
        try {

                String insertSql = "INSERT INTO emprunter (date_retour, date_demprunt, status, ISBN_emp, id_Bib, id_Ben) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connect().prepareStatement(insertSql);
                preparedStatement.setDate(2, new java.sql.Date(currentDate.getTime()));
                preparedStatement.setDate(1, new java.sql.Date(returnDate.getTime()));
                preparedStatement.setString(3, book.getStatus());
                preparedStatement.setInt(4, book.getISBN());
                preparedStatement.setInt(5, idBib);
                preparedStatement.setInt(6, idBen);

                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("\u001B[34m]");
                    System.out.println(msg);
                    System.out.println("\u001B[0m]");
                    return true;
                } else {
                    System.out.println("\u001B[31]");
                    System.out.println("failed !!");
                    System.out.println("\u001B[0m]");
                }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false ;
    }
    public  List<Borrow> getAllBorrowedBooks() {
        List<Borrow> borrowedBooks = new ArrayList<>();

        try {
            // Your SQL query to retrieve all borrowed books
            String query = "SELECT * FROM emprunter";

            // Create a prepared statement
            PreparedStatement preparedStatement = connect().prepareStatement(query);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Iterate through the result set
            while (resultSet.next()) {
                // Retrieve data from the result set
                int id = resultSet.getInt("id");
                Date borrowDate = resultSet.getDate("date_demprunt");
                Date returnDate = resultSet.getDate("date_retour");
                String status = resultSet.getString("status");
                int isbn = resultSet.getInt("ISBN_emp");
                int bibId = resultSet.getInt("id_Bib");
                int benId = resultSet.getInt("id_Ben");
               Beneficiaries ben = beneficiariesService.getBenRepository(benId);
               Book book = bookService.getbook(isbn);
                Bibliothecaire bib = bibliothecaireService.getBib(bibId);
                // Create a BorrowedBook object and add it to the list
                Borrow borrowedBook = new Borrow( borrowDate, returnDate, status,book, bib, ben);
                borrowedBooks.add(borrowedBook);
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
            database.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return borrowedBooks;
    }
    public  List<Book> getAllDisponibleBooks() {
        List<Book> DesBooks = new ArrayList<>();

        try {
            // Your SQL query to retrieve all borrowed books
            String query = "SELECT * FROM livre where status = 'disponible'";

            // Create a prepared statement
            PreparedStatement preparedStatement = connect().prepareStatement(query);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Iterate through the result set
            while (resultSet.next()) {
                // Retrieve data from the result set
                int Isbn = resultSet.getInt("ISBN");
                String Titre = resultSet.getString("titre");
                String auteur = resultSet.getString("auteur");
                String status = resultSet.getString("status");
                int Qnt = resultSet.getInt("quantite");

                // Create a BorrowedBook object and add it to the list
                Book desBook = new Book( Isbn, Titre,auteur,Qnt, status);
                DesBooks.add(desBook);
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
            database.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return DesBooks;
    }


}
