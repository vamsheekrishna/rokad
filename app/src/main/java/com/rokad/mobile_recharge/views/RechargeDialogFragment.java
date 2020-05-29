package com.rokad.mobile_recharge.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;

import com.rokad.R;
import com.rokad.mobile_recharge.interfaces.OnMobileRechargeListener;
import com.rokad.mobile_recharge.models.MobileRecharge;
import com.rokad.model.UserData;

import java.math.BigDecimal;
import java.util.Objects;



public class RechargeDialogFragment extends DialogFragment implements View.OnClickListener {

    public static final String IS_SUCCESS = "is success";
    public static final String TRANSACTION = "transaction";
    private OnMobileRechargeListener mListener;
    private boolean mStatus;
    private String transactionID;

    public static RechargeDialogFragment newInstance(boolean isSuccess, String transactionID) {

        RechargeDialogFragment fragment = new RechargeDialogFragment();
        Bundle args = new Bundle();
        args.putBoolean(IS_SUCCESS, isSuccess);
        args.putString(TRANSACTION, transactionID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnMobileRechargeListener) {
            mListener = (OnMobileRechargeListener)context;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            Objects.requireNonNull(dialog.getWindow()).setLayout(width, height);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStatus = getArguments().getBoolean(IS_SUCCESS);
        if(mStatus) {
            transactionID = getArguments().getString(TRANSACTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.regharge_custom_dialog, container, false);
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    return keyCode == KeyEvent.KEYCODE_BACK;
                }
                return false;
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        view.startAnimation(shake);
        Button submit = view.findViewById(R.id.payment_btn);
        MobileRecharge data = mListener.getMobileRechargeModule();
        if(mStatus) {
            ((TextView)view.findViewById(R.id.status)).setText("Payment Success");
            ((TextView)view.findViewById(R.id.status)).setTextColor(getResources().getColor(R.color.success));
            ((ImageView)view.findViewById(R.id.status_logo)).setImageResource(R.drawable.success);
            submit.setText("DO ANOTHER");
            String balanceText = UserData.getInstance().getWalletBalance().replace(",","");
            //double balance = Double.valueOf(balanceText); //Double.parseDouble(balanceText);
            BigDecimal balance = new BigDecimal(balanceText);
            String rechargeText =  data.getRechargeAmount();
            /*float rechargeAmount = Float.parseFloat(rechargeText);
            double temp = balance - rechargeAmount;*/

            balance = balance.subtract(new BigDecimal(rechargeText));
            UserData.getInstance().setWalletBalance(balance.toString());
            view.findViewById(R.id.home).setOnClickListener(this);
            view.findViewById(R.id.home).setVisibility(View.VISIBLE);
        } else {
            ((TextView)view.findViewById(R.id.status)).setText("Payment Failed");
            ((TextView)view.findViewById(R.id.status)).setTextColor(getResources().getColor(R.color.fail));
            ((ImageView)view.findViewById(R.id.status_logo)).setImageResource(R.drawable.fail);
            submit.setText("TRY AGAIN");
            view.findViewById(R.id.home).setVisibility(View.GONE);
        }
        ((AppCompatTextView)view.findViewById(R.id.wallet_bal_amt)).setText(UserData.getInstance().getWalletBalance());
        submit.setOnClickListener(this);
        ((AppCompatImageView)view.findViewById(R.id.service_provider_img)).setImageResource(data.getImage());
        ((AppCompatTextView)view.findViewById(R.id.mobile_num)).setText(data.getMobileNumber());
        ((AppCompatTextView)view.findViewById(R.id.subscriber_name)).setText(data.getMobileOperator());
        ((AppCompatTextView)view.findViewById(R.id.state_name)).setText(data.getStateName());
        ((AppCompatTextView)view.findViewById(R.id.recharge_amt)).setText(data.getRechargeAmount());
        ((AppCompatTextView)view.findViewById(R.id.total_amt)).setText(data.getRechargeAmount());

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.payment_btn) {
            if(mStatus) {
                mListener.makeAnotherPayment();
                dismiss();
            } else {
                dismiss();
            }
        } else if(view.getId() == R.id.home) {
            requireActivity().finish();
        }
    }
}
