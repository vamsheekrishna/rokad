package com.rokad.dmt.interfaces;

import com.rokad.dmt.RequestPackets.BeneficiaryRegistrationDetails;
import com.rokad.dmt.RequestPackets.CommissionDetails;
import com.rokad.dmt.RequestPackets.DMTHomeScreenDetails;
import com.rokad.dmt.RequestPackets.FundTransferDetails;
import com.rokad.dmt.RequestPackets.NewSenderRegistrationDetails;

public interface OnDMTInteractionListener {
    void goToDMTHome();
    void goToReBeneficiaryRegistration();
    void goToSenderRegistration();
    void goToDomesticFundTransfer();
    void goToConformation();
    void showCustomDialog();
    void showCommissionDialog();
    void showCustomOTPDialog(String mobileNumber);
    void makeAnotherPayment();
    DMTHomeScreenDetails getHomeScreenDetails();
    FundTransferDetails getFundTransferDetails();
    NewSenderRegistrationDetails getSenderRegistrationDetails();
    BeneficiaryRegistrationDetails getBeneficiaryRegistrationDetails();
    CommissionDetails getCommissionDetails();

}
