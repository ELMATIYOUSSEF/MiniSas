package repository;

import static console.TextColor.*;
import model.Bibliothecaire;
import model.Book;
import database.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static model.Book.IDbib;
import static model.Book.NameBib;
import static model.Book.Email;
import static database.database.connect;
import static service.BorrowService.borrowBookService;

public class BibliothecaireRepository {
    Bibliothecaire bib ;
    public static boolean login(String email, String password) {
        try {
            String sql = "SELECT id, name FROM Bibliothecaires WHERE email = ? AND PASS_WORD = ?";
            PreparedStatement preparedStatement = connect().prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                IDbib = resultSet.getInt("id");
                NameBib = resultSet.getString("name");
                Email = email ;
                return true; // User with matching email and password found
            } else {
                return false; // User not found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public void returnBook(int ISBN , int IdBen){
        try {
            String sql = "SELECT * FROM livre WHERE ISBN = ? ";
            Book book = getBookInfo(ISBN, sql ,0);
            if (book != null){
                int availableQuantity = book.getQuantity();
                book.setQuantity(availableQuantity+1);
                //changeQuantity
                changeBookQuantity(book);
                changeBookStatusToReturned(book,IdBen) ;
            }else {
                System.out.println(RED);
                System.out.println(" is not available for returned.");
                System.out.println(RESET);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void checkout(int ISBN, int IdBen) {
        try {
            String availabilityQuery = "SELECT * FROM livre WHERE isbn = ?";
            Book book = getBookInfo(ISBN, availabilityQuery ,0);
            if (book != null) {
                int availableQuantity = book.getQuantity()-1;
                book.setQuantity(availableQuantity);
                if (availableQuantity > 0) {

                } else {
                    System.out.println(ISBN + " Book Title: " + book.getTitle() + " is not available for checkout.");
                }

                if (availableQuantity > 0) {

                        String borrowStatusQuery = "SELECT * FROM emprunter WHERE  status = 'borrowed' AND id_Ben = ?";
                        if (!isBookBorrowed(borrowStatusQuery ,IdBen)) {
                            //Change Book Quantity
                            changeBookQuantity(book);

                            // Change status and record the borrowing
                            borrowBookService(book, IDbib, IdBen);

                    }else {
                        System.out.println(RED);
                        System.out.println(" This Beneficiary is already borrowed Book.");
                        System.out.println(RESET);
                    }

                } else {
                    System.out.println(RED);
                    System.out.println(ISBN + " Book Title: " + book.getTitle() + " is not available for checkout.");
                    System.out.println(RESET);
                }
            } else {
                System.out.println(RED);
                System.out.println(ISBN + " Book not found.");
                System.out.println(RESET);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeBookQuantity(Book book ) throws SQLException {
        try {
            String Sql = "UPDATE livre SET quantite = ? WHERE ISBN = ?";
            PreparedStatement preparedStatement = connect().prepareStatement(Sql);
            preparedStatement.setInt(2, book.getISBN());
            preparedStatement.setInt(1, book.getQuantity());

            int rowsUpdated = preparedStatement.executeUpdate();

            if ( rowsUpdated == 0) {
                System.out.println(RED);
                System.out.println(" failed.. !!");
                System.out.println(RESET);
            }

            preparedStatement.close();
            database.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private boolean isBookBorrowed( String query , int Idbin) throws SQLException {
        boolean isBorrowed = false;

        try (PreparedStatement preparedStatement = connect().prepareStatement(query)) {
            preparedStatement.setInt(1, Idbin);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    isBorrowed = true;
                }
            }
        }

        return isBorrowed;
    }

    public static Book getBookInfo(int ISBN, String query , int Idben) throws SQLException {
        Book book = null;

        try (PreparedStatement preparedStatement = connect().prepareStatement(query)) {
            if(Idben == 0){
                preparedStatement.setInt(1, ISBN);
            }
            else {
                preparedStatement.setInt(1, ISBN);
                preparedStatement.setInt(2, Idben);
            }


            try{
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String title = resultSet.getString("titre");
                    String auteur = resultSet.getString("auteur");
                    String status = resultSet.getString("status");
                    int quantity = resultSet.getInt("quantite");

                    book = new Book(ISBN, title,auteur, quantity, status);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return book;
    }


    public void changeBookStatusToReturned(Book book , int Idben) {
        try {
            String updateQuery = "UPDATE emprunter SET status = 'returned' WHERE ISBN_emp = ? AND id_Ben = ?";

            try (PreparedStatement preparedStatement = connect().prepareStatement(updateQuery)) {
                preparedStatement.setInt(1, book.getISBN());
                preparedStatement.setInt(2, Idben);

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println(BLEU);
                    System.out.println("Book status changed to 'returned' successfully.");
                    System.out.println();
                } else {
                    System.out.println(RED);
                    System.out.println("No book with the specified ISBN_emp found.");
                    System.out.println(RESET);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Bibliothecaire getBib(int id){
        try {
            String sql = "SELECT * FROM bibliothecaires where id = ?";
            PreparedStatement preparedStatement = connect().prepareStatement(sql);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBib  = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                bib = new Bibliothecaire(idBib,name,email);
                return bib ;
            }
            resultSet.close();
            preparedStatement.close();
            database.disconnect();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return bib ;
    }


}
