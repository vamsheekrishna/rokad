package com.rokad.dmt.interfaces;

import com.rokad.dmt.pojos.BeneficiaryListResponsePOJO;
import com.rokad.dmt.pojos.NewTransactionProcessResponsePOJO;
import com.rokad.dmt.pojos.SenderRegistration.SenderData;
import com.rokad.dmt.pojos.beneficiaryList.Beneficiary;
import com.rokad.dmt.pojos.beneficiaryList.Data;

public interface OnDMTInteractionListener {
    void goToDMTHome();
    void goToReBeneficiaryRegistration(Data senderData);
    void goToSenderRegistration();
    void goToDomesticFundTransfer(BeneficiaryListResponsePOJO beneficiaryListResponsePOJO);
    void goToConformation(com.rokad.dmt.pojos.FundTransfer.Data data, Beneficiary selectedBeneficiary);
    void showCustomDialog(NewTransactionProcessResponsePOJO newTransactionProcessResponsePOJO);
    void showCommissionDialog();
    void showCustomOTPDialog(SenderData senderData, BeneficiaryListResponsePOJO beneficiaryListResponsePOJO);
    void makeAnotherPayment();

//    DMTHomeScreenDetails getHomeScreenDetails();
//    FundTransferDetails getFundTransferDetails();
//    NewSenderRegistrationDetails getSenderRegistrationDetails();
//    BeneficiaryRegistrationDetails getBeneficiaryRegistrationDetails();
//    CommissionDetails getCommissionDetails();

}
