package com.rokad.dmt.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;

import com.rokad.R;
import com.rokad.dmt.interfaces.OnDMTInteractionListener;
import com.rokad.utilities.Utils;
import com.rokad.utilities.views.BaseFragment;
import com.rokad.utilities.views.EditTextWithTitleAndThumbIcon;

public class DomesticFundTransferFragment extends BaseFragment implements View.OnClickListener, View.OnFocusChangeListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnDMTInteractionListener mListener;
    private EditTextWithTitleAndThumbIcon senderName, transferLimit, transferAmount;
    private AppCompatSpinner beneficiariesSpinner;
    private EditTextWithTitleAndThumbIcon senderMobileNumber, senderRegID;
    private RadioGroup transferTypeGroup;
    private RadioButton transferType;

    public DomesticFundTransferFragment() {
        // Required empty public constructor
    }
    public static DomesticFundTransferFragment newInstance(String param1, String param2) {
        DomesticFundTransferFragment fragment = new DomesticFundTransferFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnDMTInteractionListener) {
            mListener = (OnDMTInteractionListener) context;
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
        requireActivity().setTitle("Transfer Money");
        return inflater.inflate(R.layout.fragment_domestic_fund_transfer, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.reg_beneficiary).setOnClickListener(this);
        view.findViewById(R.id.check_commission).setOnClickListener(this);
        view.findViewById(R.id.submit).setOnClickListener(this);

        senderMobileNumber = view.findViewById(R.id.mobile_number);
        senderMobileNumber.accessEditText().setInputType(InputType.TYPE_CLASS_PHONE);
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(10);
        senderMobileNumber.accessEditText().setFilters(filterArray);

        senderName = view.findViewById(R.id.sender_name);
        senderName.accessSubHeaderTextView().setText("Sender Name");

        senderName.accessEditText().setOnFocusChangeListener(this);

        transferLimit = view.findViewById(R.id.transfer_limit);
        transferLimit.accessEditText().setInputType(InputType.TYPE_CLASS_NUMBER);

        senderRegID = view.findViewById(R.id.sender_reg_id);
        transferTypeGroup = view.findViewById(R.id.transfer_type);

        transferAmount = view.findViewById(R.id.transfer_amt);
        transferAmount.accessEditText().setInputType(InputType.TYPE_CLASS_NUMBER);

        transferTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                transferType = view.findViewById(checkedId);

                if (transferType.getText().equals("NEFT")){
                    transferLimit.accessSubHeaderTextView().setText("Sender NEFT transfer limit");
                } else {
                    transferLimit.accessSubHeaderTextView().setText("Sender IMPS transfer limit");
                }
            }
        });

        beneficiariesSpinner = view.findViewById(R.id.spinner_view);

//        ArrayList<String> beneficiaryNames = utils.getBeneficiaryList("9920132129", "dhiraj", "338");
//        ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()),
//                R.layout.support_simple_spinner_dropdown_item, beneficiaryNames);
//        if (beneficiaryNames.isEmpty() || beneficiaryNames == null){
//            beneficiariesSpinner.setEnabled(false);
//        } else {
//            beneficiariesSpinner.setAdapter(namesAdapter);
//        }

//        utils.getAllBanks();

//        utils.processNewTransaction("RMB000000000003","ITZCASH  CARD LTD",
//                "1234567890", "TREG00000005659","BFC000000001114","338", "IMPS");
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.sender_name:

                if (!hasFocus) {
                    String mobile = senderMobileNumber.accessEditText().getText().toString();
                    String name = senderName.accessEditText().getText().toString();
                    if (Utils.isValidMobile(mobile) && Utils.isValidWord(name)) {
                        ProgressDialog progressBar = new ProgressDialog(getActivity(), R.style.mySpinnerTheme);
                        progressBar.setCancelable(false);
                        progressBar.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                        progressBar.show();
                        // mListener.getFundTransferDetails().setMobileNumber(mobile);
                        // mListener.getFundTransferDetails().setSenderName(name);
                        // DMTUtilis.getDMTUtilsInstance().getBeneficiaryList(mobile,name,"338");
                    } else {
                        showDialog("Sorry!!", "Please check and enter the mobile number and Sender's name again");
                        senderMobileNumber.accessEditText().setText("");
                        senderName.accessEditText().setText("");
                    }
                }



        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reg_beneficiary:
                mListener.goToReBeneficiaryRegistration();
                break;
            case R.id.check_commission:
                mListener.showCommissionDialog();
                break;
            case R.id.submit:

                if (!Utils.isValidMobile(senderMobileNumber.accessEditText().getText().toString())){
                    showDialog("Sorry!!", "Please check the sender's mobile number.");
                } else if (!Utils.isValidWord(senderName.accessEditText().getText().toString())){
                    showDialog("Sorry!!", "Please enter the sender's Name properly.");
                } else if (senderRegID.accessEditText().getText().equals("")){
                    showDialog("Sorry!!", "Please enter sender's Registration ID.");
                } else if (beneficiariesSpinner.getSelectedItem().equals(getString(R.string.default_beneficiary_spinner_prompt)) ||
                beneficiariesSpinner.getSelectedItem().equals("Please select a Beneficiary.")){
                    showDialog("Sorry!!","Please select a Beneficiary.");
                } else if (transferAmount.accessEditText().getText().equals("")){
                    showDialog("Sorry!!", "Please enter the Transfer Amount.");
                } else {
                    mListener.goToConformation();
                }

                break;
        }
    }


}
