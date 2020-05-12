package com.rokad.dmt.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.rokad.R;
import com.rokad.authentication.UserData;
import com.rokad.dmt.interfaces.OnDMTInteractionListener;
import com.rokad.dmt.pojos.FundTransfer.Data;
import com.rokad.dmt.pojos.NewTransactionProcessResponsePOJO;
import com.rokad.dmt.pojos.beneficiaryList.Beneficiary;
import com.rokad.rokad_api.RetrofitClientInstance;
import com.rokad.rokad_api.endpoints.DMTModuleService;
import com.rokad.utilities.views.BaseFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmPaymentFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Data transferData;
    private String mParam2;
    private OnDMTInteractionListener mListener;
    private AppCompatTextView senderName, senderMobileNum, receiverName, receiverMobileNum, transferAmt, processingFee, netTransferAmt;
    Beneficiary selectedBeneficiary;
    public ConfirmPaymentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (OnDMTInteractionListener) context;
    }

    public static ConfirmPaymentFragment newInstance(Data param1, Beneficiary param2) {
        ConfirmPaymentFragment fragment = new ConfirmPaymentFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            transferData = (Data) getArguments().getSerializable(ARG_PARAM1);
            selectedBeneficiary = (Beneficiary) getArguments().getSerializable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm_payment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.confirm_payment_btn).setOnClickListener(this);

        senderName = view.findViewById(R.id.sender_name);
        senderMobileNum = view.findViewById(R.id.sender_mobile_num);
        receiverName = view.findViewById(R.id.receiver_name);
        receiverMobileNum = view.findViewById(R.id.receiver_mobile_num);
        transferAmt = view.findViewById(R.id.transfer_amt);
        processingFee = view.findViewById(R.id.processing_fee);
        netTransferAmt = view.findViewById(R.id.net_transfer_amt);
    }

    @Override
    public void onResume() {
        super.onResume();
        senderName.setText(transferData.getSenderName());
        senderMobileNum.setText(transferData.getSenderMobileNo());
        receiverMobileNum.setText(selectedBeneficiary.getBeneficiaryMobileNo());
        receiverName.setText(selectedBeneficiary.getBeneficiaryFullName());
        transferAmt.setText(transferData.getAmount());
        processingFee.setText(transferData.getProcessingfee());
        netTransferAmt.setText(transferData.getNetAmount());
    }

    @Override
    public void onClick(View view) {
        progressBar.show();
        RetrofitClientInstance.getRetrofitInstance().create(DMTModuleService.class).doTransaction(
                transferData.getProcessingBankId(),
                transferData.getProcessingBankName(),
                transferData.getSenderMobileNo(),
                transferData.getSenderId(),
                selectedBeneficiary.getBeneficiaryId(),
                UserData.getUserData().getId(),
                transferData.getRemitType()
        ).enqueue(new Callback<NewTransactionProcessResponsePOJO>() {
            @Override
            public void onResponse(Call<NewTransactionProcessResponsePOJO> call, Response<NewTransactionProcessResponsePOJO> response) {
                if(response.isSuccessful()) {
                    NewTransactionProcessResponsePOJO newTransactionProcessResponsePOJO= response.body();
                    if(newTransactionProcessResponsePOJO.getStatus().equalsIgnoreCase("Success")) {
                        mListener.showCustomDialog(newTransactionProcessResponsePOJO);
                    } else {
                        showDialog("", newTransactionProcessResponsePOJO.getMsg());
                    }
                } else {
                    showDialog("", response.message());
                }
                progressBar.cancel();
            }

            @Override
            public void onFailure(Call<NewTransactionProcessResponsePOJO> call, Throwable t) {
                showDialog("", t.getMessage());
                progressBar.cancel();
            }
        });
    }
}
