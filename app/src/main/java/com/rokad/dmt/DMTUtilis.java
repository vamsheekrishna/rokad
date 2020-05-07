package com.rokad.dmt;

import com.rokad.dmt.pojos.BankListResponsePOJO;
import com.rokad.dmt.pojos.BeneficiaryListResponsePOJO;
import com.rokad.dmt.pojos.BeneficiaryRegistrationResponsePOJO;
import com.rokad.dmt.pojos.FundTransferResponsePOJO;
import com.rokad.dmt.pojos.NewTransactionProcessResponsePOJO;
import com.rokad.dmt.pojos.OTPValidationResponsePOJO;
import com.rokad.dmt.pojos.ResendOTPResponsePOJO;
import com.rokad.dmt.pojos.SenderRegistrationResponsePOJO;
import com.rokad.rokad_api.RetrofitClientInstance;
import com.rokad.rokad_api.endpoints.DMTModuleService;

import retrofit2.Call;

public class DMTUtilis {

    private final DMTModuleService services;

    public DMTUtilis() {
        services = RetrofitClientInstance.getRetrofitInstance().create(DMTModuleService.class);
    }

    private DMTModuleService getServiceInstance(){
        return RetrofitClientInstance.getRetrofitInstance().create(DMTModuleService.class);
    }

    public Call<BeneficiaryListResponsePOJO> getBeneficiaryLis(String mobileNumber, String senderName, String userID) {
        return services.getBeneficiaryLis(mobileNumber, senderName, userID);
    }

    public Call<NewTransactionProcessResponsePOJO> doTransaction(String processingBankId, String processingBankName, String customerMobileNumber, String senderId, String beneficiaryID, String userId, String transactionType) {
        return services.doTransaction(processingBankId, processingBankName, customerMobileNumber, senderId, beneficiaryID, userId, transactionType);
    }

    public Call<SenderRegistrationResponsePOJO> senderRegistration(String senderMobileNumber, String senderFirstName, String lastName, String state, String userId) {
        return services.senderRegistration(senderMobileNumber, senderFirstName, lastName, state, userId);
    }

    public Call<BeneficiaryRegistrationResponsePOJO> beneficiaryRegistration(String mobileNumber, String senderID, String firstName, String lastName, String beneficaryMobileNumber, String bankName, String accountNumber, String reenteredAccountNumber, String ifscCode, String relation, String branchName, String ifscOption, String beneficiaryCheck, String processingBankId, String sessionId, String userId) {
        return null;
    }

    public Call<FundTransferResponsePOJO> fundTransfer(String processingBankId, String processingBankName, String transferAmount, String transferFee, String totalAmount, String walletId, String walletType, String walletBal, String neftLimit, String transferType, String customerMobileNumber, String senderName, String neftLimit_, String impsLimit, String senderId, String listBen, String amount, String userId) {
        return null;
    }

    public Call<BankListResponsePOJO> BANK_LIST_POJO_CALL() {
        return null;
    }

    public Call<OTPValidationResponsePOJO> OTPValidation(String userID, String uniqueSessionID, String mobileNumber, String firstName, String lastName, String otp, String regID, String paytmUserSate) {
        return services.OTPValidation(userID, uniqueSessionID, mobileNumber, firstName, lastName, otp, regID, paytmUserSate);
    }

    public Call<ResendOTPResponsePOJO> resendOTP(String sessionId, String mobileNumber) {
        return services.resendOTP(sessionId, mobileNumber);
    }
}
