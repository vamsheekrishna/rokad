package com.rokad.dmt.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.rokad.R;
import com.rokad.dmt.interfaces.OnDMTInteractionListener;
import com.rokad.mobile_recharge.interfaces.OnMobileRechargeListener;
import com.rokad.mobile_recharge.views.RechargeDialogFragment;

import static com.rokad.mobile_recharge.views.RechargeDialogFragment.IS_SUCCESS;
import static com.rokad.mobile_recharge.views.RechargeDialogFragment.TRANSACTION;

public class PaymentDialogFragment extends DialogFragment implements View.OnClickListener {

    private OnDMTInteractionListener mListener;

    public static PaymentDialogFragment newInstance(boolean isSuccess, String transactionID) {

        PaymentDialogFragment fragment = new PaymentDialogFragment();
        Bundle args = new Bundle();
        args.putBoolean(IS_SUCCESS, isSuccess);
        args.putString(TRANSACTION, transactionID);
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
