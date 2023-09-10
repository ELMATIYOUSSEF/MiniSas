package controller;

import model.Beneficiaries;
import model.Book;
import service.BeneficiariesService;

import java.util.List;
import java.util.Scanner;

public class BeneficiariesController {
    BeneficiariesService beneficiariesService = new BeneficiariesService();
    Beneficiaries ben ;
    List<Beneficiaries> List_beneficiaries ;
    public void addBeneficiaries(){
        Scanner myObj = new Scanner(System.in);
        System.out.print("\u001B[32m");
        System.out.println("<=============== Add Beneficiaries ===============> \n");
        System.out.println("Enter le Nom : ");
        String Nom = myObj.nextLine();
        System.out.println("le numéro de telephone: ");
        String Phone = myObj.nextLine();
         ben = new Beneficiaries(Nom,Phone);

        if (beneficiariesService.createBeneficiaryService(ben)!= null) {
            System.out.print("\u001B[34m");
            System.out.println("Beneficiaries added successfully!");
            System.out.print("\u001B[0m");
        } else {
            System.out.print("\u001B[31m");
            System.out.println("Error: Please try again.");
            System.out.print("\u001B[0m");
        }
    }
    public void showAll(){
        System.out.print("\u001B[32m");
        System.out.println("<=============== ALL book ===============> \n");

        System.out.print("\u001B[0m");
        if(List_beneficiaries!= null)List_beneficiaries.clear();
       List_beneficiaries = beneficiariesService.showAllBeneficiariesService();
        HeaderTable();
        for (Beneficiaries beneficiaries : List_beneficiaries) {
            displayBenInfoInTable(beneficiaries);
        }

    }
    public void HeaderTable() {
        System.out.format("%-20s %-20s%n", "Nom", "Phone");
        System.out.println("--------------------------------------------------");
    }

    public void displayBenInfoInTable(Beneficiaries beneficiary) {
        if (beneficiary != null) {
            System.out.format("%-20s %-20s%n", beneficiary.getName(), beneficiary.getPhone());
        }
    }

}
