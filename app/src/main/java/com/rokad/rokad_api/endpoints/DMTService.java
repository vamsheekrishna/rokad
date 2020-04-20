package com.rokad.rokad_api.endpoints;

import java.util.ArrayList;

public interface DMTService {
    void verifyOTP();
    void getSenderInfo();
    ArrayList<String> getBeneficiaryList(String mobileNumber, String senderName, String userId);
    void fundTransfer();
    void getAllBanks();
}
