package repository;

import model.Book;
import database.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static model.Book.IDbib;
import static model.Book.NameBib;
import static database.database.connect;

public class BibliothecaireRepository {
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
            String sql = "SELECT * FROM emprunter WHERE ISBN_emp = ? and status = borrowed and id_Ben = ?";
            Book book = getBookInfo(ISBN, sql ,IdBen);
            int availableQuantity = book.getQuantity()+1;
            book.setQuantity(availableQuantity);
            //changeQuantity
            changeBookQuantity(book);
            changeBookStatusToReturned(book) ;
            System.out.println(ISBN + "Book Title :" + book.getTitle() + "has ben returned");
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
                    String borrowStatusQuery = "SELECT * FROM emprunter WHERE ISBN_emp = ? AND status = 'borrowed'";
                    if (!isBookBorrowed(ISBN, borrowStatusQuery)) {

                        changeBookQuantity(book);

                        // Change status and record the borrowing
                        String msg = "Book borrowed successfully.";
                        changeBookStatusAndBorrow(book, IdBen, msg);

                        System.out.println(ISBN + " Book Title: " + book.getTitle() + " has been checked out");
                    } else {
                        System.out.println(ISBN + " Book Title: " + book.getTitle() + " is already borrowed.");
                    }
                } else {
                    System.out.println(ISBN + " Book Title: " + book.getTitle() + " is not available for checkout.");
                }
            } else {
                System.out.println(ISBN + " Book not found.");
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

            if (rowsUpdated > 0) {
                System.out.println("Quantity Changed successfully.");
            } else {
                System.out.println(" failed.. !!");
            }

            preparedStatement.close();
            database.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
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


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String title = resultSet.getString("titre");
                    String auteur = resultSet.getString("auteur");
                    String status = resultSet.getString("status");
                    int quantity = resultSet.getInt("quantite");

                    book = new Book(ISBN, title,auteur, quantity, status);
                }
            }
        }

        return book;
    }

    private boolean isBookBorrowed(int ISBN, String query) throws SQLException {
        boolean isBorrowed = false;

        try (PreparedStatement preparedStatement = connect().prepareStatement(query)) {
            preparedStatement.setInt(1, ISBN);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    isBorrowed = true;
                }
            }
        }

        return isBorrowed;
    }


    private void changeBookStatusAndBorrow(Book book, int IdBen, String msg) throws SQLException {
        String insertQuery = "INSERT INTO emprunter (ISBN_emp, id_Ben, status, borrow_date) VALUES (?, ?, 'borrowed', ?)";

        try (PreparedStatement preparedStatement = connect().prepareStatement(insertQuery) ) {
            preparedStatement.setInt(1, book.getISBN());
            preparedStatement.setInt(2, IdBen);
            preparedStatement.setDate(3, new Date(System.currentTimeMillis())); // Assuming you record the borrowing date

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println(msg);
            } else {
                System.out.println("Failed to record borrowing.");
            }
        }
    }

    // I didn't use because I have created Trigger which performs the same function
    public void changeBookStatusToReturned(Book book) {
        try {
            String updateQuery = "UPDATE emprunter SET status = 'returned' WHERE ISBN_emp = ?";

            try (PreparedStatement preparedStatement = connect().prepareStatement(updateQuery)) {
                preparedStatement.setInt(1, book.getISBN());

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Book status changed to 'returned' successfully.");
                } else {
                    System.out.println("No book with the specified ISBN_emp found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
