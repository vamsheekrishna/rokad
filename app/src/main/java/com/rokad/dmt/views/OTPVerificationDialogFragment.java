package com.rokad.dmt.views;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;

import com.rokad.BuildConfig;
import com.rokad.R;
import com.rokad.dmt.interfaces.OnDMTInteractionListener;
import com.rokad.dmt.pojos.BeneficiaryListResponsePOJO;
import com.rokad.dmt.pojos.OTPValidationResponsePOJO;
import com.rokad.dmt.pojos.ResendOTPResponsePOJO;
import com.rokad.dmt.pojos.SenderRegistration.SenderData;
import com.rokad.dmt.pojos.beneficiaryList.Data;
import com.rokad.model.UserData;
import com.rokad.rokad_api.RetrofitClientInstance;
import com.rokad.rokad_api.endpoints.DMTModuleService;
import com.rokad.rokad_api.endpoints.ResponseProcessOTPbcSenderVerified;
import com.rokad.rokad_api.endpoints.ResponseResendBCSenderVerified;
import com.rokad.utilities.views.EditTextWithTitleAndThumbIcon;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPVerificationDialogFragment extends DialogFragment implements View.OnClickListener {


    public static final String SEDER = "seder";
    private static final String PAYTM_VERIFY = "paytm";
    private EditTextWithTitleAndThumbIcon otpValue;
    private AppCompatTextView resendOTP;
    private SenderData senderObject;
    private DMTModuleService DMTService;
    private OnDMTInteractionListener mListener;
    private ProgressDialog progressBar;
    private CountDownTimer downTimer;
    private BeneficiaryListResponsePOJO paytmVerification;
    public OTPVerificationDialogFragment() {
    }

    public static OTPVerificationDialogFragment newInstance(SenderData senderData, BeneficiaryListResponsePOJO paytmVerification) {

        Bundle args = new Bundle();
        OTPVerificationDialogFragment fragment = new OTPVerificationDialogFragment();
        args.putSerializable(SEDER, senderData);
        args.putSerializable(PAYTM_VERIFY, paytmVerification);
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressBar = new ProgressDialog(getActivity(), R.style.mySpinnerTheme);
        progressBar.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        progressBar.setCancelable(false);

        assert savedInstanceState != null;
        senderObject = (SenderData) getArguments().getSerializable(SEDER);
        paytmVerification = (BeneficiaryListResponsePOJO) getArguments().getSerializable(PAYTM_VERIFY);
        DMTService =  RetrofitClientInstance.getRetrofitInstance().create(DMTModuleService.class);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.otp_verification_dialog,container,false);
        otpValue = view.findViewById(R.id.otp_value);
        otpValue.accessSubHeaderTextView().setText("OTP Number");
        otpValue.accessEditText().setTextSize(16);
        otpValue.accessEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
//        otpValue.accessEditText().setBackgroundResource(R.drawable.edit_txt_rounded_corners);
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(6);
        otpValue.accessEditText().setFilters(fArray);

        resendOTP = view.findViewById(R.id.resend_otp_btn);
        view.findViewById(R.id.submit_otp).setOnClickListener(this);
        view.findViewById(R.id.close).setOnClickListener(this);
        downTimer = new CountDownTimer(40000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                resendOTP.setText( "Secs : " + TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)%60 + "     ");
            }

            @Override
            public void onFinish() {
                resendOTP.setText("Resend OTP?");
                resendOTP.setOnClickListener(OTPVerificationDialogFragment.this::onClick);
            }
        };

        downTimer.start();
        this.setCancelable(false);
        return view;
    }
    @Override
    public void onStart()
    {
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.resend_otp_btn:
                progressBar.show();
                if(null != senderObject) {
                    DMTService.resendOTP(senderObject.getSessionId(), senderObject.getMobileNo()).enqueue(new Callback<ResendOTPResponsePOJO>() {
                        @Override
                        public void onResponse(Call<ResendOTPResponsePOJO> call, Response<ResendOTPResponsePOJO> response) {
                            if(response.isSuccessful()) {
                                ResendOTPResponsePOJO data = response.body();
                                if(data.getStatus().equals("Success")) {
                                    ResendOTPResponsePOJO.ResendOTPData temp = data.getResendOTPData();
                                    if(temp.getResponseCode().equals("Y")) {
                                        otpValue.accessEditText().setText("");
                                        downTimer.start();
                                        Toast.makeText(requireContext(), "OTP sent successfully to "+temp.getMobileNo()+". Please check.", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(requireActivity(), data.getMsg(), Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(requireActivity(), response.message(), Toast.LENGTH_LONG).show();
                            }
                            progressBar.cancel();
                        }
                        @Override
                        public void onFailure(Call<ResendOTPResponsePOJO> call, Throwable t) {
                            if(t instanceof SocketTimeoutException){
                                Toast.makeText(requireActivity(),getString(R.string.time_out_msg), Toast.LENGTH_LONG).show();
                            } else {
                                // showDialog("Sorry..!!", getString(R.string.server_failed_case));
                                Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                            progressBar.cancel();
                            // Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    DMTService.resendBCSsenderVerified(
                            paytmVerification.getData().getSenderMobileNo(),
                            UserData.getUserData().getId(),
                            paytmVerification.getData().getIcwCode(),
                            paytmVerification.getData().getSourceId(),
                            paytmVerification.getData().getUsername(),
                            BuildConfig.MOBILE_APPLICATION,
                            BuildConfig.MOBILE_VERSION_ID
                    ).enqueue(new Callback<ResponseResendBCSenderVerified>() {
                        @Override
                        public void onResponse(Call<ResponseResendBCSenderVerified> call, Response<ResponseResendBCSenderVerified> response) {
                            if (response.isSuccessful()) {
                                assert response.body() != null;
                                ResponseResendBCSenderVerified.BCSenderVerifiedData temp = response.body().getBcSenderVerifiedData();
                                if (temp.getResponseCode().equals("Y")) {
                                    otpValue.accessEditText().setText("");
                                    downTimer.start();
                                    Toast.makeText(requireContext(), "OTP sent successfully to " + temp.getMobileNo() + ". Please check.", Toast.LENGTH_LONG).show();
                                }
                                progressBar.cancel();
                            } else {
                                progressBar.cancel();
                                Toast.makeText(requireActivity(), response.message(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseResendBCSenderVerified> call, Throwable t) {
                            if(t instanceof SocketTimeoutException){
                                Toast.makeText(requireActivity(),getString(R.string.time_out_msg), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                            progressBar.cancel();
                            // Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }

                break;
            case R.id.submit_otp:
                String otp = otpValue.accessEditText().getText().toString();
                if (otp.length() == 6) {
                    progressBar.show();

                    if (null != senderObject) {
                        DMTService.OTPValidation(UserData.getUserData().getId(), senderObject.getSessionId(), senderObject.getMobileNo(), senderObject.getFirstName(),
                                senderObject.getLastName(), otp, senderObject.getRegistrationId(), senderObject.getPaytmUserState(),
                                BuildConfig.MOBILE_APPLICATION,
                                BuildConfig.MOBILE_VERSION_ID).enqueue(new Callback<OTPValidationResponsePOJO>() {
                            @Override
                            public void onResponse(Call<OTPValidationResponsePOJO> call, Response<OTPValidationResponsePOJO> response) {
                                if (response.isSuccessful()) {
                                    OTPValidationResponsePOJO data = response.body();
                                    if (data.getStatus().equals("Success")) {
                                        mListener.makeAnotherPayment();
                                    } else {
                                        Toast.makeText(requireActivity(), data.getMsg(), Toast.LENGTH_LONG).show();
                                    }
                                }
                                progressBar.cancel();
                            }

                            @Override
                            public void onFailure(Call<OTPValidationResponsePOJO> call, Throwable t) {
                                if(t instanceof SocketTimeoutException){
                                    Toast.makeText(requireActivity(),getString(R.string.time_out_msg), Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                                }
                                progressBar.cancel();
                                // Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    } else if (null != paytmVerification){
                        Data temp = paytmVerification.getData();
                        DMTService.processOTPbcSenderVerified(
                                temp.getUsername(),
                                temp.getIcwCode(),
                                temp.getSenderMobileNo(),
                                otp,
                                temp.getSourceId(),
                                UserData.getUserData().getId(),
                                BuildConfig.MOBILE_APPLICATION,
                                BuildConfig.MOBILE_VERSION_ID
                        ).enqueue(new Callback<ResponseProcessOTPbcSenderVerified>() {
                            @Override
                            public void onResponse(Call<ResponseProcessOTPbcSenderVerified> call, Response<ResponseProcessOTPbcSenderVerified> response) {
                                // {"status":"success","amount":4013.76,"msg":"User Wallet Balance"}
                                if (response.isSuccessful()) {
                                    mListener.makeAnotherPayment();
                                    dismiss();
                                }
                                progressBar.cancel();
                            }

                            @Override
                            public void onFailure(Call<ResponseProcessOTPbcSenderVerified> call, Throwable t) {
                                if(t instanceof SocketTimeoutException){
                                    Toast.makeText(requireActivity(),getString(R.string.time_out_msg), Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                                }
                                progressBar.cancel();
                                // Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } else {
                    Toast.makeText(requireActivity(), "Please enter a valid OTP.", Toast.LENGTH_LONG).show();
                }


                break;

            case R.id.close:
                requireActivity().onBackPressed();
                break;
        }
    }
}
