package repository;

import model.Book;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import static database.database.connect;
import static service.BorrowService.calculateReturnDate;

public class BorrowRepository {
    public BorrowRepository() {
    }

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


}
