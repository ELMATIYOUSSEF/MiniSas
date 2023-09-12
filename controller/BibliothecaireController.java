package controller;


import service.BibliothecaireService;
import static console.TextColor.*;

import java.util.Scanner;

public class BibliothecaireController {
    BibliothecaireService service = new BibliothecaireService();

    public void returnBookService(){
        Scanner myObj = new Scanner(System.in);
        System.out.print(GREEN);
        System.out.println("<=============== Add Beneficiaries ===============> \n");
        System.out.println("Enter le ISBN : ");
        int ISBN = myObj.nextInt();
        System.out.println("Enter ID de Beneficiaries : ");
        int IDben = myObj.nextInt();
        service.returnBookService(ISBN,IDben);

    }

    public void checkoutBook(){
        Scanner myObj = new Scanner(System.in);
        System.out.print(GREEN);
        System.out.println("<=============== Get back Book ===============> \n");
        System.out.println("Enter le ISBN : ");
        int ISBN = myObj.nextInt();
        System.out.println("Enter ID de Beneficiaries : ");
        int IDben = myObj.nextInt();
        service.checkoutService(ISBN,IDben);
    }

}
