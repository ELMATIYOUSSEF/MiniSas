package Model;

import java.util.Date;

public class Borrow {
     private Date date_Borrow ;
     private Date date_return;
     private String Status ;
      private int Isbn_Borrow ;
      private int IdBib ;
      private int IdBen ;

    public Borrow(Date date_Borrow, Date date_return, String status, int isbn_Borrow, int idBib, int idBen) {
        this.date_Borrow = date_Borrow;
        this.date_return = date_return;
        Status = status;
        Isbn_Borrow = isbn_Borrow;
        IdBib = idBib;
        IdBen = idBen;
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

    public int getIsbn_Borrow() {
        return Isbn_Borrow;
    }

    public void setIsbn_Borrow(int isbn_Borrow) {
        Isbn_Borrow = isbn_Borrow;
    }

    public int getIdBib() {
        return IdBib;
    }

    public void setIdBib(int idBib) {
        IdBib = idBib;
    }

    public int getIdBen() {
        return IdBen;
    }

    public void setIdBen(int idBen) {
        IdBen = idBen;
    }
}
