package com.rokad.dmt.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;

import com.rokad.BuildConfig;
import com.rokad.R;
import com.rokad.dmt.interfaces.OnDMTInteractionListener;
import com.rokad.dmt.pojos.BankListResponsePOJO;
import com.rokad.dmt.pojos.ResponseBeneficiaryRegistration;
import com.rokad.dmt.pojos.beneficiaryList.Data;
import com.rokad.model.UserData;
import com.rokad.rokad_api.RetrofitClientInstance;
import com.rokad.rokad_api.endpoints.DMTModuleService;
import com.rokad.utilities.Utils;
import com.rokad.utilities.views.BaseFragment;
import com.rokad.utilities.views.EditTextWithTitleAndThumbIcon;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeneficiaryRegistrationFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Data senderData;
    private String mParam2;
    private OnDMTInteractionListener mListener;
    private EditTextWithTitleAndThumbIcon firstName, lastName, ifscCode;
    private EditTextWithTitleAndThumbIcon bankAccNumber, confirmBankAccNumber;
    private AppCompatSpinner banksListSpinner, relationSpinner;
    private EditTextWithTitleAndThumbIcon senderMobileNumber,beneficiaryMobileNumber;
    List<BankListResponsePOJO.BanksList> bankList = new ArrayList<>();
    BankListResponsePOJO.BanksList selectedBank;
    private ProgressDialog progressBar;
    // private AppCompatEditText bankBranch;

    public BeneficiaryRegistrationFragment() {
        // Required empty public constructor
    }

    public static BeneficiaryRegistrationFragment newInstance(Data senderData, String param2) {
        BeneficiaryRegistrationFragment fragment = new BeneficiaryRegistrationFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, senderData);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            senderData = (Data) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnDMTInteractionListener) {
            mListener = (OnDMTInteractionListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Beneficiary Registration");
        progressBar = new ProgressDialog(getActivity(), R.style.mySpinnerTheme);
        progressBar.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        return inflater.inflate(R.layout.fragment_beneficiary_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstName = view.findViewById(R.id.beneficiary_fst_name);
        lastName = view.findViewById(R.id.beneficiary_last_name);
        senderMobileNumber = view.findViewById(R.id.sender_mobile_num);
        String mobile = senderData.getSenderMobileNo();
        senderMobileNumber.accessEditText().setText(mobile);
        senderMobileNumber.accessEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        senderMobileNumber.setFocusable(false);
        senderMobileNumber.accessEditText().setEnabled(false);

        beneficiaryMobileNumber = view.findViewById(R.id.beneficiary_mobile_num);
        beneficiaryMobileNumber.accessEditText().setInputType(InputType.TYPE_CLASS_PHONE);
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(10);
        beneficiaryMobileNumber.accessEditText().setFilters(fArray);
        banksListSpinner = view.findViewById(R.id.bank_list_spinner);
        banksListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedBank = bankList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        relationSpinner = view.findViewById(R.id.relation_spinner_view);
        bankAccNumber = view.findViewById(R.id.bank_account_num);
        confirmBankAccNumber = view.findViewById(R.id.confirm_bank_account_num);

        // bankBranch = ((EditTextWithTitleAndThumbIcon)view.findViewById(R.id.bank_branch)).accessEditText();
        ifscCode = view.findViewById(R.id.ifsc_code);
        view.findViewById(R.id.reg_beneficiary).setOnClickListener(this);
        progressBar.setCancelable(false);
        progressBar.show();
        RetrofitClientInstance.getRetrofitInstance().create(DMTModuleService.class).getBanksList().enqueue(new Callback<BankListResponsePOJO>() {
            @Override
            public void onResponse(Call<BankListResponsePOJO> call, Response<BankListResponsePOJO> response) {
                if (response.isSuccessful()) {
                    // BankListResponsePOJO bankList = response.body();
                    assert response.body() != null;
                    bankList = response.body().getBanksLists();
                    banksListSpinner.setAdapter(new BankListAdapter(bankList));
                } else {
                    Toast.makeText(requireActivity(), ""+response.message(),Toast.LENGTH_LONG).show();
                }
                progressBar.cancel();
            }

            @Override
            public void onFailure(Call<BankListResponsePOJO> call, Throwable t) {
                progressBar.cancel();
                if(t instanceof SocketTimeoutException){
                    showDialog(getString(R.string.time_out_title), getString(R.string.time_out_msg));
                } else {
                    showDialog("", t.getMessage());
                }
//                Log.d("onFailure", "onFailure: "+t.getMessage());
//                Toast.makeText(requireActivity(), ""+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        /*ifscCode.accessEditText().setText("HDFC0000967");
        confirmBankAccNumber.accessEditText().setText("50100147713545");
        bankAccNumber.accessEditText().setText("50100147713545");
        firstName.accessEditText().setText("Vamshee");
        lastName.accessEditText().setText("Krishna");
        beneficiaryMobileNumber.setText("8919251923");*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.reg_beneficiary:
                String _firstName = firstName.accessEditText().getText().toString();
                String _lastName = lastName.accessEditText().getText().toString();
                String _beneficiaryMobileNumber = beneficiaryMobileNumber.accessEditText().getText().toString();
                String _bankAccNumber = bankAccNumber.accessEditText().getText().toString();
                String _confirmBankAccNumber = confirmBankAccNumber.accessEditText().getText().toString();
                String _ifscCode = ifscCode.accessEditText().getText().toString();
                // String  _bankBranch = bankBranch.getText().toString();
                if (!Utils.isValidWord(_firstName)){
                    showDialog("Sorry!!","Please enter your first name without any spaces and special characters.");
                } else if (!Utils.isValidWord(_lastName)){
                    showDialog("Sorry!!","Please enter your last name without any spaces and special characters.");
                } else if (!Utils.isValidMobile(senderMobileNumber.accessEditText().getText().toString())){
                    showDialog("Sorry!!","Please enter your valid Sender's Mobile Number.");
                }  else if (!Utils.isValidMobile(_beneficiaryMobileNumber)){
                    showDialog("Sorry!!","Please enter your valid Beneficiary's Mobile Number.");
                } else if (_bankAccNumber.isEmpty()){
                    showDialog("Sorry!!","Please enter your valid Bank Account Number.");
                } else if (_confirmBankAccNumber.isEmpty()){
                    showDialog("Sorry!!","Please re-enter your valid Bank Account Number to confirm.");
                } else if (! _bankAccNumber.equals(_confirmBankAccNumber)){
                    showDialog("Sorry!!","Mismatch of Bank Account Numbers. Please enter again.");
                    bankAccNumber.accessEditText().setText("");
                    confirmBankAccNumber.accessEditText().setText("");
                } else if (_ifscCode.length() < 4){
                    showDialog("Sorry!!","Please enter your bank IFSC code.");
                } else {

                    progressBar.setCancelable(false);
                    progressBar.show();

                    RetrofitClientInstance.getRetrofitInstance().create(DMTModuleService.class).beneficiaryRegistration(senderData.getSenderMobileNo(), senderData.getSenderId(), _firstName,
                            _lastName, _beneficiaryMobileNumber, selectedBank.getBankId(),_bankAccNumber, _confirmBankAccNumber,_ifscCode,"ByIfsc"
                    ,"N", senderData.getSessionId(), UserData.getUserData().getId(),
                            BuildConfig.MOBILE_APPLICATION,
                            BuildConfig.MOBILE_VERSION_ID).enqueue(new Callback<ResponseBeneficiaryRegistration>() {
                        @Override
                        public void onResponse(Call<ResponseBeneficiaryRegistration> call, Response<ResponseBeneficiaryRegistration> response) {
                            if(response.isSuccessful() && response.body().getStatus().equalsIgnoreCase("Success")) {
                                requireActivity().onBackPressed();
                            } else {
                               showDialog("", response.body().getMsg());
                            }
                            progressBar.cancel();
                        }
                        @Override
                        public void onFailure(Call<ResponseBeneficiaryRegistration> call, Throwable t) {
                            progressBar.cancel();
                            if(t instanceof SocketTimeoutException){
                                showDialog(getString(R.string.time_out_title), getString(R.string.time_out_msg));
                            } else {
                                showDialog("", t.getMessage());
                            }
                        }
                    });
                }
                break;

        }
    }
}
