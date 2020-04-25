package com.rokad.dmt.views;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;

import com.rokad.R;
import com.rokad.utilities.views.EditTextWithTitleAndThumbIcon;

public class OTPVerificationDialog extends DialogFragment implements View.OnClickListener {

    private EditTextWithTitleAndThumbIcon otpValue;
    private AppCompatButton submitOtp;
    private AppCompatTextView timer, resendOtp;


    public static OTPVerificationDialog newInstance() {

        Bundle args = new Bundle();

        OTPVerificationDialog fragment = new OTPVerificationDialog();
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
        View v = inflater.inflate(R.layout.otp_verification_dialog, container, false);

        otpValue = v.findViewById(R.id.otp_value);
        otpValue.accessEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        otpValue.accessEditText().setFilters(new InputFilter[] { new InputFilter.LengthFilter(6) });
        submitOtp = v.findViewById(R.id.submit_otp);
        timer = v.findViewById(R.id.timer);
        resendOtp = v.findViewById(R.id.resend_otp_btn);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.submit_otp:break;

            case R.id.resend_otp_btn:break;

        }
    }
}
