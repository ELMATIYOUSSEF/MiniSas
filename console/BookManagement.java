package console;

import controller.BookController;

import java.sql.SQLException;
import java.util.Scanner;

import static console.Menu.clearScreen;
import static console.Menu.printHeader;

public class BookManagement {
    public static void menuBook() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        BookController bookController = new BookController();
        int choice;


        do {
            System.out.print("\u001B[32m");
            clearScreen();
            printHeader();
            System.out.println("\tBook management : ");
            System.out.println("\t01 : Ajoute des Livres.");
            System.out.println("\t02 : affiché tout les livres");
            System.out.println("\t03 : affiché un livre.");
            System.out.println("\t04 : Rechecher sur un livre ");
            System.out.println("\t05 : suppreme un livre .");
            System.out.println("\t06 : Modifier un livre.");
            System.out.println("\t07 : Retournez à la page d'accueil .");
            System.out.print("\nVotre choix : ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bookController.addBook();
                    break;
                case 2:
                    System.out.print("\u000C");
                    break;
                case 3:
                    bookController.showBook();
                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

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
                    System.out.print("\u001B[31m");
                    System.out.println("\n Choix invalide. Veuillez réessayer.");
                    System.out.print("\u001B[0m");
            }

        } while( choice != 7);

    }
}
