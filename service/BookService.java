package service;

import interfaces.crud;
import model.Book;
import repository.BookRepository;

import java.sql.SQLException;
import java.util.List;

import static repository.BibliothecaireRepository.getBookInfo;


public class BookService implements crud {
    private final BookRepository bookRepository ;

    public BookService() {
        this.bookRepository = new BookRepository() ;
    }
    public Book save(Book book){
        return bookRepository.save(book);
    }
    public List<Book> getAll(){
        return bookRepository.getAll() ;
    }
    public Book update(Book book){
        return bookRepository.update(book);
    }

    public void delete(int ISBN){
        bookRepository.delete(ISBN);
    }
    public boolean checkISBN(int ISBN){
        return bookRepository.checkISBN(ISBN);
    }
    public List<Book> searchBooks(String searchCriteria){
        return bookRepository.searchBooks(searchCriteria);
    }
    public Book getbook(int ISBN) throws SQLException {
        String query = "SELECT * FROM livre WHERE ISBN = ?";
       return getBookInfo(ISBN, query , 0) ;
    }

}
