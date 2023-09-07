package repository;

import model.Beneficiaries;
import database.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static database.database.connect;

public class BeneficiariesRepository {

    public Beneficiaries createBeneficiary(Beneficiaries beneficiary) {
        try  {
            String sql = "INSERT INTO Beneficiaires (name, num_phone) VALUES (?, ?)";
            PreparedStatement preparedStatement = database.connect().prepareStatement(sql);
            preparedStatement.setString(1, beneficiary.getName());
            preparedStatement.setString(2, beneficiary.getPhone());

            int rowsInserted = preparedStatement.executeUpdate();
            if(rowsInserted > 0){
                return beneficiary ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void showAllBeneficiaries() {
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
