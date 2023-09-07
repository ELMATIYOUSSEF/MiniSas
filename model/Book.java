package model;

import java.util.ArrayList;
import java.util.List;

public  class Book {
    private int ISBN ;
    private String title ;
    private  String author;
    private int Quantity ;
    private String status ;

    public static int IDbib ;
    public static String NameBib ;

   public static List<Book> listBook = new ArrayList<>();

    public Book (int ISBN , String title , String author , int Quantity ,String status){
        this.ISBN = ISBN ;
        this.author = author ;
        this.title = title;
        this.Quantity = Quantity ;
        this.status = status;
    }
    public void ShowBook(){
        System.out.println("isbn is  = "+ ISBN  + " \n author is  = " +author + " \ntitle = "+title + "\nQuantity of this book is = "+Quantity);
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





}
