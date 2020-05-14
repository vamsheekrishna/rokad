package com.rokad.rokad_api.endpoints;

import com.rokad.BuildConfig;
import com.rokad.dmt.pojos.BankListResponsePOJO;
import com.rokad.dmt.pojos.BeneficiaryListResponsePOJO;
import com.rokad.dmt.pojos.FundTransferResponsePOJO;
import com.rokad.dmt.pojos.NewTransactionProcessResponsePOJO;
import com.rokad.dmt.pojos.OTPValidationResponsePOJO;
import com.rokad.dmt.pojos.PaytmVerificationRequest;
import com.rokad.dmt.pojos.ResendOTPResponsePOJO;
import com.rokad.dmt.pojos.ResponseBeneficiaryRegistration;
import com.rokad.dmt.pojos.SenderRegistrationResponsePOJO;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DMTModuleService {

    @POST(BuildConfig.BENEFICIARY_LIST)
    @FormUrlEncoded
    Call<BeneficiaryListResponsePOJO> getBeneficiaryLis(
            @Field("CustomerMobileNo") String mobileNumber,
            @Field("Userid") String userID,
            @Field("mobileapp") String mobileapp,
            @Field("mobileversionid") String mobileversionid
    );


    @POST(BuildConfig.PROCESS_NEW_TRANSACTION)
    @FormUrlEncoded
    Call<NewTransactionProcessResponsePOJO> doTransaction(@Field("processingBankId") String processingBankId,
                                                          @Field("processingBankName") String processingBankName,
                                                          @Field("amt") String amt,
                                                          @Field("fee") String fee,
                                                          @Field("total_amt") String total_amt,
                                                          @Field("wallet_id") String wallet_id,
                                                          @Field("wallet_type") String wallet_type,
                                                          @Field("wallet_bal") String wallet_bal,
                                                          @Field("neft_Limit") String neft_Limit,
                                                          @Field("type") String type,
                                                          @Field("CustomerMobileNo") String CustomerMobileNo,
                                                          @Field("SenderName") String SenderName,
                                                          @Field("impsLimit") String impsLimit,
                                                          @Field("senderId") String senderId,
                                                          @Field("listBen") String listBen,
                                                          @Field("amount") String amount,
                                                          @Field("user_id") String user_id,
                                                          @Field("mobileapp") String mobileapp,
                                                          @Field("mobileversionid") String mobileversionid
    );

    @POST(BuildConfig.SENDER_REGISTRATION)
    @FormUrlEncoded
    Call<SenderRegistrationResponsePOJO> senderRegistration(@Field("CustomerMobileNo") String customerMobileNumber,
                                                            @Field("FirstName") String customerFirstName,
                                                            @Field("LastName") String lastName,
                                                            @Field("state") String state,
                                                            @Field("user_id") String userId,
                                                            @Field("mobileapp") String mobileapp,
                                                            @Field("mobileversionid") String mobileversionid);


    @POST(BuildConfig.BENEFICIARY_REGISTRATION)
    @FormUrlEncoded
    Call<ResponseBeneficiaryRegistration> beneficiaryRegistration(@Field("CustomerMobileNo") String mobileNumber,
                                                                  @Field("SenderId") String senderID,
                                                                  @Field("FirstName") String firstName,
                                                                  @Field("LastName") String lastName,
                                                                  @Field("BenMobileNo") String beneficaryMobileNumber,
                                                                  @Field("bank_name") String bankName,
                                                                  @Field("AccNum") String accountNumber,
                                                                  @Field("ReAccNum") String reenteredAccountNumber,
                                                                  @Field("ifscCode") String ifscCode,
                                                                  // @Field("relation") String relation,
                                                                  @Field("ifscOption") String ifscOption,
                                                                  @Field("beneficiaryCheck") String beneficiaryCheck,
                                                                  // @Field("processingBankId ") String processingBankId,
                                                                  @Field("unique_session_id") String sessionId,
                                                                  @Field("user_id") String userId,
                                                                  @Field("mobileapp") String mobileapp,
                                                                  @Field("mobileversionid") String mobileversionid);


    @POST(BuildConfig.FUND_TRANSFER)   // TODO: FAULTY API ==> Check with team
    @FormUrlEncoded
    Call<FundTransferResponsePOJO> fundTransfer(
            @Field("CustomerMobileNo") String customerMobileNo,
            @Field("SenderName") String senderName,
            @Field("senderId") String senderId,
            @Field("listBen") String listBen,
            @Field("amount") int amount,
            @Field("user_id") String user_id,
            @Field("mobileapp") String mobileapp,
            @Field("mobileversionid") String mobileversionid
    );



    @POST(BuildConfig.BANK_LIST)
    Call<BankListResponsePOJO> getBanksList();


    @POST(BuildConfig.OTP_VERIFICATION)
    @FormUrlEncoded
    Call<OTPValidationResponsePOJO> OTPValidation(@Field("user_id") String userID,
                                                  @Field("unique_session_id") String uniqueSessionID,
                                                  @Field("mob_no") String mobileNumber,
                                                  @Field("fname") String firstName,
                                                  @Field("lname") String lastName,
                                                  @Field("otp") String otp,
                                                  @Field("registrationId") String regID,
                                                  @Field("paytmUserState") String paytmUserSate,
                                                  @Field("mobileapp") String mobileapp,
                                                  @Field("mobileversionid") String mobileversionid);


    @POST(BuildConfig.RESEND_OTP)
    @FormUrlEncoded
    Call<ResendOTPResponsePOJO> resendOTP(@Field("session_id") String sessionId,
                                          @Field("mob_no") String mobileNumber);

    @POST(BuildConfig.PAYTM_VERIFICATION_REQUEST)
    Call<PaytmVerificationRequest> paytmVerificationRequest(
            @Field("icwCode") String icwCode,
            @Field("senderMobileNo") String senderMobileNo,
            @Field("sourceId") String sourceId,
            @Field("username") String username,
            @Field("user_id") String user_id,
            @Field("mobileapp") String mobileapp,
            @Field("mobileversionid") String mobileversionid

    );
    @POST(BuildConfig.RESEND_BCSENDER_VERIFICATION)
    Call<ResponseResendBCSenderVerified> resendBCSsenderVerified(
            @Field("mob_no") String mob_no,
            @Field("user_id") String user_id,
            @Field("icwCode") String icwCode,
            @Field("sourceId") String sourceId,
            @Field("username") String username,
            @Field("mobileapp") String mobileapp,
            @Field("mobileversionid") String mobileversionid
    );
    @POST(BuildConfig.PROCESS_OTP_BC_SENDER_VERIFICATION)
    Call<ResponseProcessOTPbcSenderVerified> processOTPbcSenderVerified(
            @Field("username") String username,
            @Field("icwCode") String icwCode,
            @Field("mob_no") String mob_no,
            @Field("otp") String otp,
            @Field("sourceId") String sourceId,
            @Field("user_id") String user_id,
            @Field("mobileapp") String mobileapp,
            @Field("mobileversionid") String mobileversionid
    );
}
