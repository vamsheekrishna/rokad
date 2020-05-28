package com.rokad.AEPS;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rokad.R;
import com.rokad.dmt.pojos.TransactionProcessPOJO;
import com.rokad.mantra.MFS100Test;
import com.rokad.model.UserData;
import com.rokad.rokad_api.endpoints.AEPSService;
import com.rokad.rokad_api.RetrofitClientInstance;
import com.rokad.utilities.Utils;
import com.rokad.utilities.views.BaseFragment;
import com.rokad.utilities.views.EditTextWithTitleAndThumbIcon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AEPSHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AEPSHomeFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditTextWithTitleAndThumbIcon firstName, lastName, mobileNumber, email, amt;
    private AEPSService aepsService;
    private OnAEPSInteractionListener mListener;

    public AEPSHomeFragment() {
        // Required empty public constructor
    }

    public static AEPSHomeFragment newInstance(String param1, String param2) {
        AEPSHomeFragment fragment = new AEPSHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnAEPSInteractionListener) {
            mListener = (OnAEPSInteractionListener) context;
        }
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a_e_p_s_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.submit).setOnClickListener(this);
        firstName = view.findViewById(R.id.cust_fst_name);
        lastName = view.findViewById(R.id.cust_lst_name);
        mobileNumber = view.findViewById(R.id.cust_mobile_num);
        email = view.findViewById(R.id.cust_email);
        amt =  view.findViewById(R.id.cust_amt);

        mobileNumber.accessEditText().setInputType(InputType.TYPE_CLASS_PHONE);
        amt.accessEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        email.accessEditText().setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
    }

    @Override
    public void onClick(View view) {
        String _firstName = firstName.accessEditText().getText().toString();
        String _lastName = lastName.accessEditText().getText().toString();
        String _mobileNumber = mobileNumber.accessEditText().getText().toString();
        String _email = email.accessEditText().getText().toString();
        String _amt = amt.accessEditText().getText().toString();
        int amount = 0;
        try {
            amount = Integer.parseInt(_amt);
        } catch (Exception e) {
            Log.d("Exception: ", "Exception: "+e.toString());
        }
        if (!Utils.isValidWord(_firstName)){
            showDialog("Sorry!!",getString(R.string.error_first_name));
        } else if (!Utils.isValidWord(_lastName)){
            showDialog("Sorry!!",getString(R.string.erroe_last_name));
        } else if (!Utils.isValidMobile(_mobileNumber)){
            showDialog("Sorry!!",getString(R.string.error_valid_mobile));
        }/* else if (!Utils.isValidEmail(_email)){
            showDialog("Sorry!!",getString(R.string.error_valid_email));
        }*/ else if (amount<100 || amount>10000){
            showDialog("Sorry!!",getString(R.string.aeps_minimum_amount_alert));
        } else {
            progressBar.show();
            aepsService = RetrofitClientInstance.getRetrofitInstance().create(AEPSService.class);
            aepsService.transactionProcess(_firstName, _lastName, _mobileNumber, _amt,
                    UserData.getUserData().getId()).enqueue(new Callback<TransactionProcessPOJO>() {
                @Override
                public void onResponse(Call<TransactionProcessPOJO> call, Response<TransactionProcessPOJO> response) {
                    if(response.isSuccessful()) {
                        TransactionProcessPOJO data = response.body();
                        if(data.getStatus().equalsIgnoreCase("Success")) {
                            mListener.showWebView(data.getData());
                            // startActivity(new Intent(requireActivity(), MFS100Test.class));
                        }
                    } else {
                        showDialog("", response.body().getMsg());
                    }
                    progressBar.cancel();
                }

                @Override
                public void onFailure(Call<TransactionProcessPOJO> call, Throwable t) {
                    showDialog("", t.getMessage());
                    progressBar.cancel();
                }
            });
        }
    }
}
