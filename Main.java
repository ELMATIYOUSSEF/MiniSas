import model.Book;
import database.database;
import rapport_statistique.statistique;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import static console.Menu.menu;
import static console.authentification.checkLogin;
import static console.helloYoucode.helloYoucode;

public class Main {

    public static void main(String[] args ) throws SQLException {

        helloYoucode();
        database.connect();
        int maxLoginAttempts = 3;
        int count = 0;

        while (count < maxLoginAttempts) {
            if (checkLogin()) {
                System.out.println("+-----------------------------------+");
                System.out.println("| Login successful!                |");
                System.out.println("+-----------------------------------+");
                break;
            } else {
                count++;
                System.out.println("+-----------------------------------+");
                System.out.println("| Login failed.                    |");
                System.out.println("| Please try again.                |");
                System.out.println("+-----------------------------------+");
            }
        }
        menu();


    }
}
