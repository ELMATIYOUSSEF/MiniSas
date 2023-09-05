import database.database;

import static console.Menu.menu;
import static console.authentification.checkLogin;
import static console.helloYoucode.helloYoucode;

public class Main {

    public static void main(String[] args ){
        helloYoucode();

        database.connect();

        while (checkLogin()==false){
            checkLogin();
        }
        menu();


    }
}
