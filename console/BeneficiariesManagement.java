package console;

import controller.BeneficiariesController;

import java.sql.SQLException;
import java.util.Scanner;

import static console.Menu.*;

public class BeneficiariesManagement {
    public static void menuBeneficiaries() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        BeneficiariesController beneficiariesController = new BeneficiariesController();
        int choice = 0;


        do {
            System.out.print("\u001B[32m");
            clearScreen();
            printHeader();
            System.out.println("\t <=========== Beneficiaries management ===========> ");
            System.out.println("\t01 : Ajoute un Beneficiaries ");
            System.out.println("\t02 : affiché tout les Beneficiaries");
            System.out.println("\t03 : Retournez à la page d'accueil .");
            System.out.print("\nVotre choix : ");
            while (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.print("\u001B[31m");
                System.out.println("\n Choix invalide. Veuillez réessayer.");
                System.out.print("\u001B[32m");
                System.out.print("\nVotre choix : ");
            }

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        beneficiariesController.addBeneficiaries();
                        break;
                    case 2:
                        beneficiariesController.showAll();
                        break;
                    case 3:

                        try {
                            Thread.sleep(2000);
                            menu();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        System.out.print("\u001B[31m");
                        System.out.println("\n Choix invalide. Veuillez réessayer.");
                        System.out.print("\u001B[0m");
                }
            }else {
                // Clear the invalid input
                scanner.nextLine();
                System.out.print("\u001B[31m");
                System.out.println("\n Choix invalide. Veuillez réessayer.");
                System.out.print("\u001B[0m");
            }

        } while( choice != 3);

    }
}
