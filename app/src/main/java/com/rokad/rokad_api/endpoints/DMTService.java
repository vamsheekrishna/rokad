package com.rokad.rokad_api.endpoints;

import java.util.ArrayList;

public interface DMTService {
    void verifyOTP();
    void getSenderInfo();
    ArrayList<String> getBeneficiaryList(String mobileNumber, String senderName, String userId);
    void fundTransfer();
    void getAllBanks();
    void processNewTransaction(String processingBankId,String processingBankName,
                               String customerMobileNumber,String senderId,
                               String beneficiaryID,String userId,String transactionType);
}
