package console;

import service.BibliothecaireService;

import java.util.Scanner;


import static repository.BibliothecaireRepository.login;

public class authentification {


    public static boolean checkLogin(){
        BibliothecaireService service = new BibliothecaireService();
        Scanner myObj = new Scanner(System.in);
        System.out.print("\u001B[32m");
        System.out.println("<===============  Welcome To YouCode library  ===============> \n");
        System.out.println("Enter Email :");
        String Email = myObj.nextLine();
        System.out.println("Enter PassWord :");
        String password = myObj.nextLine();
        System.out.print("\u001B[0m");

        return service.loginService(Email,password);
    }
}
