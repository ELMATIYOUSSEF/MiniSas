package service;

import model.Bibliothecaire;
import model.Book;
import repository.BibliothecaireRepository;

import java.sql.SQLException;

import static repository.BibliothecaireRepository.changeBookQuantity;
import static repository.BibliothecaireRepository.login;

public class BibliothecaireService {

    private final BibliothecaireRepository BibRepository ;

    public BibliothecaireService() {
        this.BibRepository = new BibliothecaireRepository();
    }

    public boolean loginService(String email, String password){
        return login(email,password);
    }
    public void returnBookService(int ISBN , int IdBen){
        BibRepository.returnBook(ISBN,IdBen);
    }
    public void checkoutService(int ISBN, int IdBen){
        BibRepository.checkout(ISBN,IdBen);
    }
    public void changeBookQuantityService(Book book ) throws SQLException {
        changeBookQuantity( book ) ;
    }
}
