package console;

import controller.BibliothecaireController;
import rapport_statistique.statistique;

import java.sql.SQLException;
import java.util.Scanner;

import static console.BeneficiariesManagement.menuBeneficiaries;
import static console.TextColor.*;
import static console.BookManagement.menuBook;


public class Menu {
    public static void menu() throws SQLException {
        BibliothecaireController bibliothecaireController = new BibliothecaireController();
    Scanner scanner = new Scanner(System.in);
    statistique st = new statistique();
    int choice ;


    do {
        System.out.print("\u001B[32m");
        clearScreen();
        printHeader();
        System.out.println("+-----------------------------------------------------------------------------------------+");
        System.out.println("+------------------------------------------------------------+");
        System.out.println("\t\t|  +===============  Bonjour !! =================>\t |");
        System.out.println("\t S'il vous plaît, choisissez ce que vous voulez : \n");
        System.out.println("+------------------------------------------------------------+");
        System.out.println("\t01 : Gestion des Livres.");
        System.out.println("\t02 : Gestion des Bénéficaires.");
        System.out.println("\t03 : Donne Le livre.");
        System.out.println("\t04 : Récupérer le livre .");
        System.out.println("\t05 : Changé le mode Passe .");
        System.out.println("\t06 : Les statistiques.");
        System.out.println("\t07 : Quitter l'application.");
        System.out.println("+-----------------------------------------------------------------------------------------+");
        System.out.print("\n\t Votre choix : ");
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print(RED);
            System.out.println("\n Choix invalide. Veuillez réessayer.");
            System.out.print(GREEN);
            System.out.print("\nVotre choix : ");
        }


            choice = scanner.nextInt();


            switch (choice) {
                case 1:
                    menuBook();
                    break;
                case 2:
                    menuBeneficiaries();
                    break;
                case 3:
                    bibliothecaireController.checkoutBook();
                    break;
                case 4:
                    bibliothecaireController.returnBookService();
                    break;
                case 5:

                    break;
                case 6:
                    st.MenuStatistique();
                    break;
                case 7:

                    try {
                        //function GOODBY
                        Thread.sleep(2000); // Sleep for 2 seconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                    break;
                default:
                    System.out.print(GREEN);
                    System.out.println("\n Choix invalide. Veuillez réessayer.");
                    System.out.print(RESET);
            }

    } while(true);

}

    // Clear the screen (works for most consoles)
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    // Print header
    public static void printHeader() {
        clearScreen();
        System.out.println("\t \t===========================================================");
        System.out.println("\t \t   &&&#  Console Library Management Application  #&&& ");
        System.out.println("\t \t===========================================================");
        System.out.println("\n");
    }

}
