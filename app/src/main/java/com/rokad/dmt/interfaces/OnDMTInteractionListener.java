package com.rokad.dmt.interfaces;

import com.rokad.dmt.pojos.BeneficiaryListResponsePOJO;
import com.rokad.dmt.pojos.SenderRegistration.SenderData;
import com.rokad.dmt.pojos.beneficiaryList.Data;

public interface OnDMTInteractionListener {
    void goToDMTHome();
    void goToReBeneficiaryRegistration(Data senderData);
    void goToSenderRegistration();
    void goToDomesticFundTransfer(BeneficiaryListResponsePOJO beneficiaryListResponsePOJO);
    void goToConformation(com.rokad.dmt.pojos.FundTransfer.Data data);
    void showCustomDialog();
    void showCommissionDialog();
    void showCustomOTPDialog(SenderData senderData, BeneficiaryListResponsePOJO beneficiaryListResponsePOJO);
    void makeAnotherPayment();

//    DMTHomeScreenDetails getHomeScreenDetails();
//    FundTransferDetails getFundTransferDetails();
//    NewSenderRegistrationDetails getSenderRegistrationDetails();
//    BeneficiaryRegistrationDetails getBeneficiaryRegistrationDetails();
//    CommissionDetails getCommissionDetails();

}
