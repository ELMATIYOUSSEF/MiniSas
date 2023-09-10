package repository;

import model.Beneficiaries;
import database.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    Beneficiaries ben ;
    public List<Beneficiaries> showAllBeneficiaries() {
        List<Beneficiaries> benList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM beneficiaires";
            PreparedStatement preparedStatement = connect().prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String phone = resultSet.getString("num_phone");
                ben = new Beneficiaries(name,phone);
                benList.add(ben);
            }
            resultSet.close();
            preparedStatement.close();
            database.disconnect();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return benList;
    }

}
