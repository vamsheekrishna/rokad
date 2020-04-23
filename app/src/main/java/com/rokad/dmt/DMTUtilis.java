package com.rokad.dmt;

import android.util.Log;

import com.rokad.dmt.pojos.BankListResponsePOJO;
import com.rokad.dmt.pojos.BeneficiaryListResponsePOJO;
import com.rokad.dmt.pojos.NewTransactionProcessResponsePOJO;
import com.rokad.rokad_api.RetrofitClientInstance;
import com.rokad.rokad_api.endpoints.DMTModuleService;
import com.rokad.rokad_api.endpoints.DMTService;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DMTUtilis implements DMTService {

   private static DMTUtilis dmtUtilis = null;

    private DMTUtilis() {

    }

    public static DMTUtilis getDMTUtilsInstance(){
        if (dmtUtilis == null)
            dmtUtilis = new DMTUtilis();
        return dmtUtilis;
    }

    @Override
    public void verifyOTP() {

    }

    @Override
    public void getSenderInfo() {

    }

    @Override
    public ArrayList<String> getBeneficiaryList(String mobileNumber, String senderName, String userId) {
         ArrayList<String> beneficiaryList = new ArrayList<>();
        getServiceInstance().BENEFICIARY_LIST_RESPONSE_CALL(mobileNumber,senderName,userId)
                .enqueue(new Callback<BeneficiaryListResponsePOJO>() {
            @Override
            public void onResponse(Call<BeneficiaryListResponsePOJO> call, Response<BeneficiaryListResponsePOJO> response) {
                //TODO: return array list of beneficiaries.

//                Log.e("===lllD", response.raw().toString());
                if(response.isSuccessful())
                for (int i = 0; i < response.body().getData().getBeneficiaries().getBeneficiary().size() ; i++) {
                    beneficiaryList.add(response.body().getData().getBeneficiaries().getBeneficiary().get(i).getBeneficiaryFullName());
                }
                else{
                    Log.e(">>>>>>", "failed : " + response.message());
                }

            }

            @Override
            public void onFailure(Call<BeneficiaryListResponsePOJO> call, Throwable t) {
                //TODO: return failure response i.e. null.
                Log.e("===lllD", Objects.requireNonNull(t.getCause().toString()));
                t.printStackTrace();
            }
        });
        return beneficiaryList;
    }

    @Override
    public void fundTransfer() {
        //TODO: check with team for params.
//        getServiceInstance().FUND_TRANSFER_CALL()
    }

    @Override
    public void getAllBanks() {
        getServiceInstance().BANK_LIST_POJO_CALL().enqueue(new Callback<BankListResponsePOJO>() {
            @Override
            public void onResponse(Call<BankListResponsePOJO> call, Response<BankListResponsePOJO> response) {
                Log.e("=== D Bank list : " , response.raw().toString());
            }

            @Override
            public void onFailure(Call<BankListResponsePOJO> call, Throwable t) {
                Log.e( "Bank list errrorrrrr:" , t.getMessage() );
            }
        });
    }

    @Override
    public void processNewTransaction(String processingBankId,String processingBankName,
                                      String customerMobileNumber,String senderId,
                                      String beneficiaryID,String userId,String transactionType) {

        getServiceInstance().NEW_TRANSACTION_PROCESS_CALL(processingBankId,processingBankName,
                customerMobileNumber,senderId,beneficiaryID,userId,transactionType)
                .enqueue(new Callback<NewTransactionProcessResponsePOJO>() {
                    @Override
                    public void onResponse(Call<NewTransactionProcessResponsePOJO> call, Response<NewTransactionProcessResponsePOJO> response) {

                        Log.e("===lllD", response.raw().toString());
                    }

                    @Override
                    public void onFailure(Call<NewTransactionProcessResponsePOJO> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

    }

    private DMTModuleService getServiceInstance(){
        return RetrofitClientInstance.getRetrofitInstance().create(DMTModuleService.class);
    }
}
