package model;

import java.util.Date;

public class Borrow {
     private Date date_Borrow ;
     private Date date_return;
     private String Status ;
      private Book  book ;
      private Bibliothecaire bibliothecaire ;
      private Beneficiaries beneficiaries ;


    public Borrow(Date date_Borrow, Date date_return, String status, Book book, Bibliothecaire bibliothecaire, Beneficiaries beneficiaries) {
        this.date_Borrow = date_Borrow;
        this.date_return = date_return;
       this.Status = status;
        this.book = book ;
        this.bibliothecaire = bibliothecaire ;
       this.beneficiaries = beneficiaries ;
    }

    public Date getDate_Borrow() {
        return date_Borrow;
    }

    public void setDate_Borrow(Date date_Borrow) {
        this.date_Borrow = date_Borrow;
    }

    public Date getDate_return() {
        return date_return;
    }

    public void setDate_return(Date date_return) {
        this.date_return = date_return;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Bibliothecaire getBibliothecaire() {
        return bibliothecaire;
    }

    public void setBibliothecaire(Bibliothecaire bibliothecaire) {
        this.bibliothecaire = bibliothecaire;
    }

    public Beneficiaries getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(Beneficiaries beneficiaries) {
        this.beneficiaries = beneficiaries;
    }
}
