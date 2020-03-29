package com.rokad.mobile_recharge;

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
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;

import com.rokad.R;
import com.rokad.authentication.UserData;

import java.util.Objects;


public class RechargeDialogFragment extends DialogFragment implements View.OnClickListener {

    public static final String IS_SUCCESS = "is success";
    private OnMobileRechargeListener mListener;
    private boolean mStatus;

    public static RechargeDialogFragment newInstance(boolean isSuccess) {

        RechargeDialogFragment fragment = new RechargeDialogFragment();
        Bundle args = new Bundle();
        args.putBoolean(IS_SUCCESS, isSuccess);
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
            int balance = Integer.parseInt(UserData.getInstance().getWalletBalance());
            int rechargeAmount = Integer.parseInt(data.getRechargeAmount());
            int temp = balance - rechargeAmount;
            UserData.getInstance().setWalletBalance(String.valueOf(temp));
            ((AppCompatTextView)view.findViewById(R.id.wallet_bal_amt)).setText(UserData.getInstance().getWalletBalance());
        } else {
            ((TextView)view.findViewById(R.id.status)).setText("Payment Success");
            ((TextView)view.findViewById(R.id.status)).setTextColor(getResources().getColor(R.color.fail));
            ((ImageView)view.findViewById(R.id.status_logo)).setImageResource(R.drawable.fail);
            submit.setText("TRY AGAIN");
        }
        submit.setOnClickListener(this);
        ((AppCompatTextView)view.findViewById(R.id.mobile_num)).setText(data.getMobileNumber());
        ((AppCompatTextView)view.findViewById(R.id.subscriber_name)).setText(data.getMobileOperator());
        ((AppCompatTextView)view.findViewById(R.id.state_name)).setText(data.getStateName());
        ((AppCompatTextView)view.findViewById(R.id.recharge_amt)).setText(data.getRechargeAmount());
        ((AppCompatTextView)view.findViewById(R.id.total_amt)).setText(data.getRechargeAmount());
        view.findViewById(R.id.home).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.payment_btn) {
            if(mStatus) {
                dismiss();
            } else {
                dismiss();
            }
        } else if(view.getId() == R.id.home) {
            Objects.requireNonNull(getActivity()).finish();
        }
    }
}
