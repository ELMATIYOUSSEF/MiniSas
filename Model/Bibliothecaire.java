package Model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.database;

public class Bibliothecaire extends Person{
    private String email ;
    private  String password ;


    public Bibliothecaire(int id,String name, String Email , String password){
        super( id , name );
        this.email = Email ;
        this.password = password ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean login(String email, String password) {
        try {
            String sql = "SELECT id FROM Bibliothecaires WHERE email = ? AND PASS_WORD = ?";
            PreparedStatement preparedStatement = database.connect().prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
