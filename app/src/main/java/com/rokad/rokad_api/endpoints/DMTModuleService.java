package com.rokad.rokad_api.endpoints;

import com.rokad.BuildConfig;
import com.rokad.dmt.pojos.BeneficiaryListResponsePOJO;
import com.rokad.dmt.pojos.BeneficiaryRegistrationResponsePOJO;
import com.rokad.dmt.pojos.FundTransferResponsePOJO;
import com.rokad.dmt.pojos.NewTransactionProcessResponsePOJO;
import com.rokad.dmt.pojos.SenderRegistrationResponsePOJO;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DMTModuleService {

    @POST(BuildConfig.BENEFICIARY_LIST)
    @FormUrlEncoded
    Call<BeneficiaryListResponsePOJO> BENEFICIARY_LIST_RESPONSE_CALL(@Field("CustomerMobileNo") String mobileNumber,
                                                                     @Field("Sender Name") String senderName,
                                                                     @Field("Userid") String userID);


    @POST(BuildConfig.PROCESS_NEW_TRANSACTION)
    @FormUrlEncoded
    Call<NewTransactionProcessResponsePOJO> NEW_TRANSACTION_PROCESS_CALL(@Field("processingBankId") String processingBankId,
                                                                                  @Field("processingBankName") String processingBankName,
                                                                                  @Field("CustomerMobileNo") String customerMobileNumber,
                                                                                  @Field("senderId") String senderId,
                                                                                  @Field("listBen") String beneficiaryID,
                                                                                  @Field("user_id") String userId,
                                                                                  @Field("type") String transactionType);

    @POST(BuildConfig.SENDER_REGISTRATION)
    @FormUrlEncoded
    Call<SenderRegistrationResponsePOJO> SENDER_REGISTRATION_CALL(@Field("CustomerMobileNo") String customerMobileNumber,
                                                                  @Field("FirstName") String customerFirstName,
                                                                  @Field("LastName") String lastName,
                                                                  @Field("CustomerZipcode") String zipcode,
                                                                  @Field("unique_session_id") String sessionId,
                                                                  @Field("state") String state,
                                                                  @Field("user_id") String userId);


    @POST(BuildConfig.BENEFICIARY_REGISTRATION)
    @FormUrlEncoded
    Call<BeneficiaryRegistrationResponsePOJO> BENEFICIARY_REGISTRATION_CALL(@Field("CustomerMobileNo ") String mobileNumber,
                                                                            @Field("SenderId") String senderID,
                                                                            @Field("FirstName") String firstName,
                                                                            @Field("LastName") String lastName,
                                                                            @Field("BenMobileNo") String beneficaryMobileNumber,
                                                                            @Field("bank_name") String bankName,
                                                                            @Field("AccNum:") String accountNumber,
                                                                            @Field("ReAccNum") String reenteredAccountNumber,
                                                                            @Field("ifscCode") String ifscCode,
                                                                            @Field("relation") String relation,
                                                                            @Field("br_name") String branchName,
                                                                            @Field("ifscOption") String ifscOption,
                                                                            @Field("beneficiaryCheck") String beneficiaryCheck,
                                                                            @Field("processingBankId ") String processingBankId,
                                                                            @Field("unique_session_id") String sessionId,
                                                                            @Field("user_id") String userId);


    @POST(BuildConfig.FUND_TRANSFER)   // TODO: FAULTY API ==> Check with team
    @FormUrlEncoded
    Call<FundTransferResponsePOJO> FUND_TRANSFER_CALL(@Field("processingBankId") String processingBankId,
                                                      @Field("processingBankName") String processingBankName,
                                                      @Field("amt") String transferAmount,
                                                      @Field("fee") String transferFee,
                                                      @Field("total_amt") String totalAmount,
                                                      @Field("wallet_id") String walletId,
                                                      @Field("wallet_type") String walletType,
                                                      @Field("wallet_bal") String walletBal,
                                                      @Field("neft_Limit") String neftLimit,
                                                      @Field("type") String transferType,
                                                      @Field("CustomerMobileNo") String customerMobileNumber,
                                                      @Field("SenderName") String senderName,
                                                      @Field("neftLimit") String neftLimit_, //TODO: PARAM Redundant
                                                      @Field("impsLimit") String impsLimit,
                                                      @Field("senderId") String senderId,
                                                      @Field("listBen") String listBen,
                                                      @Field("amount") String amount,
                                                      @Field("user_id") String userId);

}
