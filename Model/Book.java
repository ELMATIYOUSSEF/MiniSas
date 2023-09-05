package Model;

import Intefaces.LibraryBooks;

public abstract class Book implements LibraryBooks {
    private int ISBN ;
    private String title ;
    private  String author;
    private int Quantity ;
    private String status ;

    public Book (int ISBN , String title , String author , int Quantity ){
        this.ISBN = ISBN ;
        this.author = author ;
        this.title = title;
        this.Quantity = Quantity ;
        this.status = "available";
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void returnBook(){
        Quantity++;
        status = "available";
        System.out.println(ISBN+"Book Title :"+ title +"has ben returned");
    }
    @Override
    public void checkout(){
        if(Quantity>0){
            Quantity--;
            status ="checked Out" ;
            System.out.println(ISBN+"Book Title :"+ title +"has ben checked Out");
        }else{
            System.out.println(ISBN+"Book Title :"+ title +"is Not available for checkout .");
        }
    }
}
