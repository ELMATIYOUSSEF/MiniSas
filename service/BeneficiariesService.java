package service;

import model.Beneficiaries;
import repository.BeneficiariesRepository;
import repository.BibliothecaireRepository;


public class BeneficiariesService {
    private final BeneficiariesRepository BenRepository ;

    public BeneficiariesService() {
        this.BenRepository = new BeneficiariesRepository();
    }
    public Beneficiaries createBeneficiaryService(Beneficiaries beneficiary) {
        return BenRepository.createBeneficiary(beneficiary);
    }
    public void showAllBeneficiariesService(){
       BenRepository.showAllBeneficiaries();
    }
}
