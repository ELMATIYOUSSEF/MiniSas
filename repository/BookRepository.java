package repository;

import interfaces.crud;
import model.Book;
import database.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static model.Book.listBook;
import static repository.BibliothecaireRepository.changeBookQuantity;
import static repository.BibliothecaireRepository.getBookInfo;

public class BookRepository implements crud {
    public Book save(Book book) {
        try  {
            if(checkISBN(book.getISBN())){
                String availabilityQuery = "SELECT * FROM livre WHERE isbn = ?";
                Book book1 = getBookInfo(book.getISBN(), availabilityQuery ,0);
                int QntTotal = book.getQuantity() + book1.getQuantity() ;
                book1.setQuantity(QntTotal);
                changeBookQuantity( book1 ) ;
                return book;
            }
            else {
                String sql = "INSERT INTO livre (ISBN, titre , auteur , quantite , status) VALUES (?, ?,?,? ,?)";
                PreparedStatement preparedStatement = database.connect().prepareStatement(sql);
                preparedStatement.setInt(1, book.getISBN());
                preparedStatement.setString(2, book.getTitle());
                preparedStatement.setString(3, book.getAuthor());
                preparedStatement.setInt(4, book.getQuantity());
                preparedStatement.setString(5, book.getStatus());

                preparedStatement.executeUpdate();
                return book;
            }

        } catch (Exception e) {
            e.getMessage();
        }
        return  null;

    }

    public List<Book> getAll() {

        try {
            String sql = "SELECT * FROM livre";
            Statement statement = database.connect().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int ISBN = resultSet.getInt("ISBN");
                String title = resultSet.getString("titre");
                String author = resultSet.getString("auteur");
                int quantity = resultSet.getInt("quantite");
                String status = resultSet.getString("status");

                Book book = new Book(ISBN, title, author ,quantity,status);
                listBook.add(book);
            }

            resultSet.close();
            statement.close();
            database.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listBook;
    }

    public Book update(Book book) {
        try {
            String sql = "UPDATE livre SET titre = ?, auteur = ? , quantite = ? ,status = ? WHERE ISBN = ?";
            PreparedStatement preparedStatement = database.connect().prepareStatement(sql);

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getQuantity());
            preparedStatement.setString(4, book.getStatus());
            preparedStatement.setInt(5, book.getISBN());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Book updated successfully.");
                return book;
            } else {
                System.out.println("No rows updated. Book not found.");
            }

            preparedStatement.close();
            database.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(int ISBN) {
        try {
            String sql = "DELETE FROM livre WHERE ISBN = ?";
            PreparedStatement preparedStatement = database.connect().prepareStatement(sql);

            preparedStatement.setInt(1, ISBN);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("No rows deleted. Book not found.");
            }

            preparedStatement.close();
            database.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean checkISBN(int ISBN){
        try {
            String sql = "SELECT * FROM livre WHERE ISBN = ?";
            PreparedStatement preparedStatement = database.connect().prepareStatement(sql);
            preparedStatement.setInt(1, ISBN);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false ;
    }

    public List<Book> searchBooks(String searchCriteria) {
        List<Book> searchResults = new ArrayList<>();
        try {
            String sql = "SELECT * FROM livre WHERE titre LIKE ? OR auteur LIKE ?";
            PreparedStatement preparedStatement = database.connect().prepareStatement(sql);

            preparedStatement.setString(1, "%" + searchCriteria + "%");
            preparedStatement.setString(2, "%" + searchCriteria + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int ISBN = resultSet.getInt("ISBN");
                String title = resultSet.getString("titre");
                String author = resultSet.getString("auteur");
                int quantity = resultSet.getInt("quantite");
                String status = resultSet.getString("status");


                Book book = new Book(ISBN, title, author,quantity,status);
                searchResults.add(book);
            }

            resultSet.close();
            preparedStatement.close();
            database.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return searchResults;
    }

}
