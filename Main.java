import model.Book;
import database.database;
import rapport_statistique.statistique;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import static console.Menu.menu;
import static console.authentification.checkLogin;
import static console.helloYoucode.helloYoucode;
import static rapport_statistique.statistique.CreatepdfFile;

public class Main {

    public static void main(String[] args ) throws SQLException {
        statistique sta = new statistique();
        sta.showAll();
        helloYoucode();

        database.connect();
        int  count=1;

        while (!checkLogin()){
            count++;
            if (count ==3){
                System.exit(0);
            }
            checkLogin();
        }
        menu();


    }
}
