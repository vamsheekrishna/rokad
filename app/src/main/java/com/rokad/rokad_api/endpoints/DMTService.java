package com.rokad.rokad_api.endpoints;

import com.rokad.dmt.pojos.beneficiaryList.Beneficiary;

import java.util.ArrayList;

public interface DMTService {
    void resendOTP();
    void verifyOTP();
    void getSenderInfo();
    ArrayList<Beneficiary> getBeneficiaryList(String mobileNumber, String senderName, String userId);
    void fundTransfer();
    void getAllBanks();
    void processNewTransaction(String processingBankId,String processingBankName,
                               String customerMobileNumber,String senderId,
                               String beneficiaryID,String userId,String transactionType);
}
