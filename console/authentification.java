package console;

import service.BibliothecaireService;
import static console.TextColor.*;
import java.util.Scanner;


import static repository.BibliothecaireRepository.login;

public class authentification {

        public static boolean checkLogin() {
            BibliothecaireService service = new BibliothecaireService();
            Scanner myObj = new Scanner(System.in);



            System.out.print(GREEN);
            System.out.println("+---------------------------------+");
            System.out.println("|  Welcome to YouCode Library    |");
            System.out.println("+---------------------------------+");

            System.out.println("Enter Email:");
            String email = myObj.nextLine().trim();
            System.out.println("Enter Password:");
            String password = myObj.nextLine();

            System.out.print(RESET);

            // Perform basic input validation before calling the service
            if (email.isEmpty() || password.isEmpty()) {
                System.out.println("Email and password are required!");
                return false;
            }

            // Call the login service
            return service.loginService(email, password);
        }



}
