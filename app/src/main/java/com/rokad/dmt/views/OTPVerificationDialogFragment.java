package com.rokad.dmt.views;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;

import com.rokad.R;
import com.rokad.utilities.views.EditTextWithTitleAndThumbIcon;

import java.util.concurrent.TimeUnit;

public class OTPVerificationDialogFragment extends DialogFragment implements View.OnClickListener {


    private EditTextWithTitleAndThumbIcon otpValue;
    private AppCompatTextView timer, resendOTP;

    public OTPVerificationDialogFragment() {
    }

    public static OTPVerificationDialogFragment newInstance(String mobileNumber) {

        Bundle args = new Bundle();

        OTPVerificationDialogFragment fragment = new OTPVerificationDialogFragment();
        args.putString("mobile", mobileNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.otp_verification_dialog,container,false);
        otpValue = view.findViewById(R.id.otp_value);
        otpValue.accessEditText().setHint("Enter the OTP Number");
        otpValue.accessEditText().setTextSize(16);
        otpValue.accessEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        timer = view.findViewById(R.id.timer);
        resendOTP = view.findViewById(R.id.resend_otp_btn);

        CountDownTimer downTimer = new CountDownTimer(40000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText( "Secs : " + TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)%60 + "     ");
            }

            @Override
            public void onFinish() {
                timer.setVisibility(View.GONE);
                resendOTP.setVisibility(View.VISIBLE);
            }
        };

        downTimer.start();

        return view;
    }

    @Override
    public void onClick(View v) {


    }
}
