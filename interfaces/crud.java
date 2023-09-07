package interfaces;

import model.Book;

import java.util.List;

public interface crud {
    Book save(Book book);
    List<Book> getAll();
    Book update(Book book);
    void delete(int ISBN);
}
