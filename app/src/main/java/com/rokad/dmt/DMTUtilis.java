package com.rokad.dmt;

import com.rokad.dmt.pojos.BeneficiaryListResponsePOJO;
import com.rokad.rokad_api.RetrofitClientInstance;
import com.rokad.rokad_api.endpoints.DMTModuleService;
import com.rokad.rokad_api.endpoints.DMTService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DMTUtilis implements DMTService {
    @Override
    public void verifyOTP() {

    }

    @Override
    public void getSenderInfo() {

    }

    @Override
    public ArrayList<String> getBeneficiaryList(String mobileNumber, String senderName, String userId) {
         ArrayList<String> beneficiaryList = new ArrayList<>();
        getServiceInstance().BENEFICIARY_LIST_RESPONSE_CALL(mobileNumber,senderName,userId).enqueue(new Callback<BeneficiaryListResponsePOJO>() {
            @Override
            public void onResponse(Call<BeneficiaryListResponsePOJO> call, Response<BeneficiaryListResponsePOJO> response) {
                //TODO: return array list of beneficiaries.
                beneficiaryList.add("");
            }

            @Override
            public void onFailure(Call<BeneficiaryListResponsePOJO> call, Throwable t) {
                //TODO: return failure response i.e. null.
                beneficiaryList.clear();
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

    }

    private DMTModuleService getServiceInstance(){
        return RetrofitClientInstance.getRetrofitInstance().create(DMTModuleService.class);
    }
}
