package com.rokad.dmt.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;

import com.rokad.R;
import com.rokad.dmt.interfaces.OnDMTInteractionListener;
import com.rokad.dmt.pojos.NewTransactionProcessResponsePOJO;

import static com.rokad.mobile_recharge.views.RechargeDialogFragment.IS_SUCCESS;
import static com.rokad.mobile_recharge.views.RechargeDialogFragment.TRANSACTION;

public class PaymentStatusDialogFragment extends DialogFragment implements View.OnClickListener {

    private OnDMTInteractionListener mListener;
    NewTransactionProcessResponsePOJO transaction;
    private AppCompatTextView senderName, senderMobileNum, receiverName, receiverMobileNum, transferAmt, processingFee, netTransferAmt, statusText;
    private AppCompatImageView statusImg;

    public static PaymentStatusDialogFragment newInstance(boolean isSuccess, NewTransactionProcessResponsePOJO transaction) {

        PaymentStatusDialogFragment fragment = new PaymentStatusDialogFragment();
        Bundle args = new Bundle();
        args.putBoolean(IS_SUCCESS, isSuccess);
        args.putSerializable(TRANSACTION, transaction);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnDMTInteractionListener) {
            mListener = (OnDMTInteractionListener)context;
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        transaction = (NewTransactionProcessResponsePOJO) getArguments().getSerializable(TRANSACTION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.payment_success_dialog_fragment, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.home_btn).setOnClickListener(this);
        view.findViewById(R.id.repeat_payment_btn).setOnClickListener(this);

        senderName = view.findViewById(R.id.sender_name);
        senderMobileNum = view.findViewById(R.id.sender_mobile_num);
        receiverName = view.findViewById(R.id.receiver_name);
        receiverMobileNum = view.findViewById(R.id.receiver_mobile_num);
        transferAmt = view.findViewById(R.id.transfer_amt);
        processingFee = view.findViewById(R.id.processing_fee);
        netTransferAmt = view.findViewById(R.id.net_transfer_amt);
        statusText = view.findViewById(R.id.status_text);
        statusImg = view.findViewById(R.id.status_img);
    }

    @Override
    public void onResume() {
        super.onResume();
        senderName.setText(transaction.getData().getSenderName());
        senderMobileNum.setText(transaction.getData().getSenderMobileNo());
        receiverMobileNum.setText(transaction.getData().getBeneficiaryMobile());
        receiverName.setText(transaction.getData().getBeneficiaryName());
        transferAmt.setText(transaction.getData().getAmount());
        processingFee.setText(transaction.getData().getProcessingfee());
        netTransferAmt.setText(transaction.getData().getNetAmount());
        statusText.setText(transaction.getData().getResponseDesc());
        if(transaction.getData().getResponseCode().equalsIgnoreCase("Y")) {
            statusText.setTextColor(getResources().getColor(R.color.success));
            statusImg.setImageDrawable(getResources().getDrawable(R.drawable.success));
        } else if(transaction.getData().getResponseCode().equalsIgnoreCase("N")) {
            statusText.setTextColor(getResources().getColor(R.color.fail));
            statusImg.setImageDrawable(getResources().getDrawable(R.drawable.fail));
        } else if(transaction.getData().getResponseCode().equalsIgnoreCase("P")) {
            statusText.setTextColor(getResources().getColor(R.color.gray));
            statusImg.setImageDrawable(getResources().getDrawable(R.drawable.bank));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_btn:
                getActivity().finish();
                break;
            case R.id.repeat_payment_btn:
                mListener.makeAnotherPayment();
                dismiss();
                break;
        }
    }
}
