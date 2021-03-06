package com.rokad.dmt.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.rokad.BuildConfig;
import com.rokad.R;
import com.rokad.authentication.LoginActivity;
import com.rokad.dmt.interfaces.OnDMTInteractionListener;
import com.rokad.dmt.pojos.BeneficiaryListResponsePOJO;
import com.rokad.dmt.pojos.PaytmVerificationRequest;
import com.rokad.model.UserData;
import com.rokad.rokad_api.RetrofitClientInstance;
import com.rokad.rokad_api.endpoints.AuthenticationService;
import com.rokad.rokad_api.endpoints.DMTModuleService;
import com.rokad.rokad_api.endpoints.pojos.ResponseWalletBalance;
import com.rokad.utilities.Utils;
import com.rokad.utilities.views.BaseFragment;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DMTHomeFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private TextView mBalance;
    private EditText mobileNumber;
    private OnDMTInteractionListener mListener;
    private ProgressDialog progressBar;

    public DMTHomeFragment() {
        // Required empty public constructor
    }

    public static DMTHomeFragment newInstance(String param1, String param2) {
        DMTHomeFragment fragment = new DMTHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        progressBar = new ProgressDialog(getActivity(), R.style.mySpinnerTheme);
        progressBar.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        progressBar.setCancelable(false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//        if(context instanceof OnDMTInteractionListener)
        mListener = (OnDMTInteractionListener) context;
    }


    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle(getString(R.string.transfer_fund));
        updateWalletBalance();
        String walletBalance = UserData.getInstance().getWalletBalance();
        if (walletBalance != null)
            mBalance .setText(walletBalance);
        else
            startActivity(new Intent(getContext(), LoginActivity.class));

        mobileNumber.setText("");
    }

    private void updateWalletBalance() {
        progressBar.show();
        AuthenticationService authenticationService = RetrofitClientInstance.getRetrofitInstance().create(AuthenticationService.class);
        Call<ResponseWalletBalance> apiResponse = authenticationService.getWalletBalance(UserData.getInstance().getId());
        apiResponse.enqueue(new Callback<ResponseWalletBalance>() {
            @Override
            public void onResponse(Call<ResponseWalletBalance> call, Response<ResponseWalletBalance> response) {
                try {
                    if (response.body().getStatus().equals("success")) {
                        ResponseWalletBalance data = response.body();
                        String walletBalance = data.getAmount();// UserData.getInstance().getWalletBalance();
                        if (walletBalance != null) {
                            mBalance.setText(walletBalance);
                        }
                        else {
                            startActivity(new Intent(getContext(), LoginActivity.class));
                        }
                    }
                } catch (Exception e) {
                    showDialog("Sorry..!!", getString(R.string.server_failed_case));
                }
                progressBar.cancel();
            }

            @Override
            public void onFailure(Call<ResponseWalletBalance> call, Throwable t) {
                if(t instanceof SocketTimeoutException){
                    showDialog(getString(R.string.time_out_title), getString(R.string.time_out_msg));
                } else {
                    showDialog("Sorry..!!", getString(R.string.server_failed_case));
                    Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
                progressBar.cancel();
                // showDialog("Sorry..!!", getString(R.string.server_failed_case));
//                Log.e("===D"," errorrr");
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        requireActivity().setTitle("DMT Home");
        return inflater.inflate(R.layout.fragment_dmt_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.addMoney).setOnClickListener(this);
        view.findViewById(R.id.add_sender).setOnClickListener(this);
        view.findViewById(R.id.transfer_fund).setOnClickListener(this);
        view.findViewById(R.id.refresh).setOnClickListener(this);
        TextView subHeader = view.findViewById(R.id.sub_header);
        subHeader.setText("Mobile Number");
        mobileNumber = view.findViewById(R.id.edit_text_view);
        mobileNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
        mobileNumber.setFilters(new InputFilter[] { new InputFilter.LengthFilter(10) });
        String usrName = UserData.getInstance().getFirstName() +" "+ UserData.getInstance().getLastName();
        ((TextView)view.findViewById(R.id.name)).setText(usrName);
        mBalance = view.findViewById(R.id.balance);
//        view.findViewById(R.id.addMoneyRoot).setVisibility(View.GONE);
//        view.findViewById(R.id.addMoneytext).setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.refresh:
                updateWalletBalance();
                break;
            case R.id.addMoney:
                AlertDialog.Builder builder =new AlertDialog.Builder(requireActivity());
                builder.setTitle("Sorry....");
                builder.setMessage(R.string.feature_availability_msg);
                builder.setNegativeButton("close", (dialog, which) -> dialog.dismiss());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.transfer_fund:
                String mobile = mobileNumber.getText().toString();
                if(!Utils.isValidMobile(mobile)) {
                    showDialog("", "Please enter a valid mobile number");
                } else {
                    progressBar.show();
                    RetrofitClientInstance.getRetrofitInstance().create(DMTModuleService.class).getBeneficiaryLis(
                            mobile, UserData.getUserData().getId(),
                            BuildConfig.MOBILE_APPLICATION,
                            BuildConfig.MOBILE_VERSION_ID).enqueue(new Callback<BeneficiaryListResponsePOJO>() {
                        @Override
                        public void onResponse(Call<BeneficiaryListResponsePOJO> call, Response<BeneficiaryListResponsePOJO> response) {
                            if (response.isSuccessful()) {
                                try{
                                    if(response.body().getStatus().equalsIgnoreCase("Success")) {
                                        BeneficiaryListResponsePOJO beneficiaryListResponsePOJO = response.body();
                                        if (beneficiaryListResponsePOJO.getData().getBcSenderVerified().equalsIgnoreCase("N")) {
                                            RetrofitClientInstance.getRetrofitInstance().create(DMTModuleService.class).paytmVerificationRequest(
                                                    beneficiaryListResponsePOJO.getData().getIcwCode(),
                                                    beneficiaryListResponsePOJO.getData().getSenderMobileNo(),
                                                    beneficiaryListResponsePOJO.getData().getSourceId(),
                                                    beneficiaryListResponsePOJO.getData().getUsername(),
                                                    UserData.getUserData().getId(),
                                                    BuildConfig.MOBILE_APPLICATION,
                                                    BuildConfig.MOBILE_VERSION_ID
                                            ).enqueue(new Callback<PaytmVerificationRequest>() {
                                                @Override
                                                public void onResponse(Call<PaytmVerificationRequest> call, Response<PaytmVerificationRequest> response) {
                                                    if (response.isSuccessful()) {
                                                        PaytmVerificationRequest.PaytemResponse temp = response.body().getData();
                                                        if (temp.getResponseCode().equals("Y")) {
                                                            mListener.showCustomOTPDialog(null, beneficiaryListResponsePOJO);
                                                            Toast.makeText(requireContext(), temp.getResponseDesc()+" to "+ temp.getMobileNo(), Toast.LENGTH_LONG).show();
                                                        } else {
                                                            Toast.makeText(requireActivity(), response.message(), Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<PaytmVerificationRequest> call, Throwable t) {
                                                    if(t instanceof SocketTimeoutException){
                                                        showDialog(getString(R.string.time_out_title), getString(R.string.time_out_msg));
                                                    } else {
                                                        showDialog("Sorry..!!", getString(R.string.server_failed_case));
                                                        Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                                                    }
                                                    progressBar.cancel();
                                                }
                                            });
                                            mListener.showCustomOTPDialog(null, beneficiaryListResponsePOJO);
                                        } else {
                                            mListener.goToDomesticFundTransfer(beneficiaryListResponsePOJO);
                                        }
                                    } else {
                                        showDialog("", response.body().getMsg());
                                    }
                                } catch (Exception e) {
                                    Log.d("Exception", "Exception: "+e.getMessage());
                                }
                            } else {
                                Toast.makeText(requireActivity(), response.message(), Toast.LENGTH_LONG).show();
                            }
                            progressBar.cancel();
                        }

                        @Override
                        public void onFailure(Call<BeneficiaryListResponsePOJO> call, Throwable t) {
                            if(t instanceof SocketTimeoutException){
                                showDialog(getString(R.string.time_out_title), getString(R.string.time_out_msg));
                            } else {
                                showDialog("Sorry..!!", getString(R.string.server_failed_case));
                                Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                            // Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                            // Log.d("Failure", "Failure: "+t.getMessage());
                            progressBar.cancel();
                        }
                    });
                }
                break;
            case R.id.add_sender:
                mListener.goToSenderRegistration();
                break;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
