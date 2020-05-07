package com.rokad.dmt.interfaces;

import com.rokad.dmt.DMTUtilis;
import com.rokad.dmt.pojos.SenderRegistration.SenderData;

public interface OnDMTInteractionListener {
    void goToDMTHome();
    void goToReBeneficiaryRegistration();
    void goToSenderRegistration();
    void goToDomesticFundTransfer();
    void goToConformation();
    void showCustomDialog();
    void showCommissionDialog();
    void showCustomOTPDialog(SenderData mobileNumber);
    void makeAnotherPayment();
    DMTUtilis getDMTUtili();

//    DMTHomeScreenDetails getHomeScreenDetails();
//    FundTransferDetails getFundTransferDetails();
//    NewSenderRegistrationDetails getSenderRegistrationDetails();
//    BeneficiaryRegistrationDetails getBeneficiaryRegistrationDetails();
//    CommissionDetails getCommissionDetails();

}
