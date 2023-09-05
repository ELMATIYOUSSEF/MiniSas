package Model;

import Intefaces.LibraryBeneficiaries;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import database.database;

public abstract class Beneficiaries extends Person implements LibraryBeneficiaries {
    private String phone;


    public Beneficiaries(int id , String name , String phone){
        super(id,name);
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void register(){

    }
    @Override
    public void returnBook(){

    }
    @Override
    public void borrowBook(){

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
}
