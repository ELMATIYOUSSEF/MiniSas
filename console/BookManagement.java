package console;

import controller.BookController;

import java.sql.SQLException;
import java.util.Scanner;
import static console.TextColor.*;

import static console.Menu.*;

public class BookManagement {
    public static void menuBook() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        BookController bookController = new BookController();
        int choice = 0;

        do {
            System.out.print(GREEN);
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
            while (!scanner.hasNextInt()) {
                // Input is not an integer, clear the invalid input and prompt again
                scanner.nextLine(); // Clear the input buffer
                System.out.print(RED);
                System.out.println("\n Choix invalide. Veuillez réessayer.");
                System.out.print(GREEN);
                System.out.print("\nVotre choix : ");
            }

                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        bookController.addBook();
                        break;
                    case 2:
                        bookController.showAll();
                        break;
                    case 3:
                        bookController.showBook();
                        break;
                    case 4:
                        bookController.searchBooks();
                        break;
                    case 5:
                        bookController.deleteBook();
                        break;
                    case 6:
                        bookController.update();
                        break;
                    case 7:
                        try {
                            Thread.sleep(2000);
                            menu();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        System.out.print(RED);
                        System.out.println("\n Choix invalide. Veuillez réessayer.");
                        System.out.print(RESET);
                }

        } while (choice != 7);
    }

}
