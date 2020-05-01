package com.rokad.dmt.views;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rokad.R;
import com.rokad.dmt.interfaces.OnDMTInteractionListener;
import com.rokad.utilities.Utils;
import com.rokad.utilities.views.BaseFragment;
import com.rokad.utilities.views.EditTextWithTitleAndThumbIcon;

public class BeneficiaryRegistrationFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private OnDMTInteractionListener mListener;
    private EditTextWithTitleAndThumbIcon firstName, lastName, ifscCode;
    private EditTextWithTitleAndThumbIcon bankAccNumber, confirmBankAccNumber;
    private AppCompatSpinner banksListSpinner, relationSpinner;
    private AppCompatEditText senderMobileNumber,beneficiaryMobileNumber;

    public BeneficiaryRegistrationFragment() {
        // Required empty public constructor
    }

    public static BeneficiaryRegistrationFragment newInstance(String param1, String param2) {
        BeneficiaryRegistrationFragment fragment = new BeneficiaryRegistrationFragment();
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
        if(context instanceof OnDMTInteractionListener) {
            mListener = (OnDMTInteractionListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Beneficiary Registration");
        return inflater.inflate(R.layout.fragment_beneficiary_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstName = view.findViewById(R.id.beneficiary_fst_name);
        lastName = view.findViewById(R.id.beneficiary_last_name);
        senderMobileNumber = view.findViewById(R.id.sender_mobile_num);
        beneficiaryMobileNumber = view.findViewById(R.id.beneficiary_mobile_num);
        banksListSpinner = view.findViewById(R.id.bank_list_spinner);
        relationSpinner = view.findViewById(R.id.relation_spinner_view);
        bankAccNumber = view.findViewById(R.id.bank_account_num);
        confirmBankAccNumber = view.findViewById(R.id.confirm_bank_account_num);
        ifscCode = view.findViewById(R.id.ifsc_code);
        view.findViewById(R.id.reg_beneficiary).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.reg_beneficiary:

                if (!Utils.isValidWord(firstName.accessEditText().getText().toString())){
                    showDialog("Sorry!!","Please enter your first name without any spaces and special characters.");
                } else if (!Utils.isValidWord(lastName.accessEditText().getText().toString())){
                    showDialog("Sorry!!","Please enter your last name without any spaces and special characters.");
                } else if (!Utils.isValidMobile(senderMobileNumber.getText().toString())){
                    showDialog("Sorry!!","Please enter your valid Sender's Mobile Number.");
                }  else if (!Utils.isValidMobile(beneficiaryMobileNumber.getText().toString())){
                    showDialog("Sorry!!","Please enter your valid Beneficiary's Mobile Number.");
                } else if (bankAccNumber.accessEditText().getText().toString().isEmpty()){
                    showDialog("Sorry!!","Please enter your valid Bank Account Number.");
                } else if (confirmBankAccNumber.accessEditText().getText().toString().isEmpty()){
                    showDialog("Sorry!!","Please re-enter your valid Bank Account Number to confirm.");
                    confirmBankAccNumber.accessEditText().setFocusable(true);
                } else if (!bankAccNumber.accessEditText().getText().equals(confirmBankAccNumber.accessEditText().getText())){
                    showDialog("Sorry!!","Mismatch of Bank Account Numbers. Please enter again.");
                    bankAccNumber.accessEditText().setText("");
                    bankAccNumber.accessEditText().setFocusable(true);
                    confirmBankAccNumber.accessEditText().setText("");
                } else if (ifscCode.accessEditText().getText().equals("")){
                    showDialog("Sorry!!","Please enter your Bank IFSC code.");
                    ifscCode.accessEditText().setFocusable(true);
                }

                break;

        }
    }
}
