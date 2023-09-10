package service;

import model.Book;
import repository.BorrowRepository;

import java.util.Calendar;
import java.util.Date;

import static repository.BorrowRepository.borrowBook;

public class BorrowService {

    private final BorrowRepository borrowRepository ;

    public BorrowService() {
        this.borrowRepository = new BorrowRepository();
    }
    public static boolean borrowBookService(Book book, int idBib, int idBen){
        if ("disponible".equals(book.getStatus())) {
            book.setStatus("borrowed");

            Date currentDate = new Date();
            String msg = "Book borrowed successfully.";
            Date returnDate = calculateReturnDate(currentDate);
            return borrowBook(book,idBib,idBen,msg ,returnDate, currentDate);
        }
     else {
            System.out.println("\u001B[31m]");
            System.out.println("Book is not available.");
            System.out.println("\u001B[0m]");
    }
return false;
    }


    public static Date calculateReturnDate(Date currentDate) {
        // for this function it's help me in Mysql => Triggers function for change status book if  currentDate + 1 year > DateTime.now() status of book in this situation = Perdu
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        // Add 1 year
        calendar.add(Calendar.YEAR, 1);
        Date returnDate = calendar.getTime();

        return returnDate;
    }
}
