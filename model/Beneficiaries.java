package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.database;

import static database.database.connect;

public class Beneficiaries extends Person {
    private String phone;


    public Beneficiaries( String name , String phone){
        super(name);
        this.phone = phone;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public boolean createBeneficiary(Beneficiaries beneficiary) {
        try  {
            String sql = "INSERT INTO Beneficiaires (name, num_phone) VALUES (?, ?)";
            PreparedStatement preparedStatement = database.connect().prepareStatement(sql);
            preparedStatement.setString(1, beneficiary.getName());
            preparedStatement.setString(2, beneficiary.getPhone());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void showAllBeneficiaries() {
        try {
            String sql = "SELECT * FROM beneficiaires";
            PreparedStatement preparedStatement = connect().prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("List of Beneficiaries:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("num_phone");

                System.out.println("ID: " + id + ", Name: " + name + ", Phone: " + phone);
            }

            resultSet.close();
            preparedStatement.close();
            database.disconnect();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
