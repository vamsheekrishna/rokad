package com.rokad.dmt.views;

import android.content.Context;
import android.content.DialogInterface;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.rokad.R;
import com.rokad.authentication.LoginActivity;
import com.rokad.authentication.UserData;
import com.rokad.dmt.interfaces.OnDMTInteractionListener;
import com.rokad.rokad_api.RetrofitClientInstance;
import com.rokad.rokad_api.endpoints.AuthenticationService;
import com.rokad.rokad_api.endpoints.pojos.ResponseWalletBalance;
import com.rokad.utilities.Utils;
import com.rokad.utilities.views.BaseFragment;

import java.util.Objects;

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
        Objects.requireNonNull(getActivity()).setTitle("Services Home");

        updateWalletBalance();
        String walletBalance = UserData.getInstance().getWalletBalance();

        if (walletBalance != null)
            mBalance .setText(walletBalance);
        else
            startActivity(new Intent(getContext(), LoginActivity.class));
    }
    private void updateWalletBalance() {
        AuthenticationService authenticationService = RetrofitClientInstance.getRetrofitInstance().create(AuthenticationService.class);
        Call<ResponseWalletBalance> apiResponse = authenticationService.getWalletBalance(UserData.getInstance().getId());
        apiResponse.enqueue(new Callback<ResponseWalletBalance>() {
            @Override
            public void onResponse(Call<ResponseWalletBalance> call, Response<ResponseWalletBalance> response) {
                try {
                    if (response.body().getStatus().equals("success")) {
                        String walletBalance = UserData.getInstance().getWalletBalance();
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
            }

            @Override
            public void onFailure(Call<ResponseWalletBalance> call, Throwable t) {
                showDialog("Sorry..!!", getString(R.string.server_failed_case));
//                Log.e("===D"," errorrr");
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("DMT Home");
        return inflater.inflate(R.layout.fragment_dmt_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.addMoney).setOnClickListener(this);
        view.findViewById(R.id.add_sender).setOnClickListener(this);
        view.findViewById(R.id.transfer_fund).setOnClickListener(this);
        mobileNumber = view.findViewById(R.id.edit_text_view);
        mobileNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
        mobileNumber.setFilters(new InputFilter[] { new InputFilter.LengthFilter(10) });
        String usrName = UserData.getInstance().getFirstName() +" "+ UserData.getInstance().getLastName();
        ((TextView)view.findViewById(R.id.name)).setText(usrName);
        mBalance = view.findViewById(R.id.balance);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addMoney:
                AlertDialog.Builder builder =new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
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
                    mListener.getHomeScreenDetails().setMobileNumber(mobile);
                    mListener.goToDomesticFundTransfer();
                }
                break;
            case R.id.add_sender:
                mListener.goToSenderRegistration();
                break;
        }
    }
}
