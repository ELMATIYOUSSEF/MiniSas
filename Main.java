import model.Book;
import database.database;

import java.sql.SQLException;

import static console.Menu.menu;
import static console.authentification.checkLogin;
import static console.helloYoucode.helloYoucode;

public class Main {

    public static void main(String[] args ) throws SQLException {

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
