package com.rokad.dmt.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;

import com.rokad.R;
import com.rokad.dmt.DMTUtilis;
import com.rokad.dmt.interfaces.OnDMTInteractionListener;
import com.rokad.utilities.views.BaseFragment;
import com.rokad.utilities.views.EditTextWithTitleAndThumbIcon;

import java.util.ArrayList;
import java.util.Objects;

public class DomesticFundTransferFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnDMTInteractionListener mListener;
    private EditTextWithTitleAndThumbIcon mobileNumber, senderName;
    private AppCompatSpinner beneficiariesSpinner;

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
        Objects.requireNonNull(getActivity()).setTitle("Transfer Money");
        return inflater.inflate(R.layout.fragment_domestic_fund_transfer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.reg_beneficiary).setOnClickListener(this);
        view.findViewById(R.id.check_commission).setOnClickListener(this);
        view.findViewById(R.id.submit).setOnClickListener(this);
        beneficiariesSpinner = view.findViewById(R.id.spinner_view);

        DMTUtilis utils = DMTUtilis.getDMTUtilsInstance();
        ArrayList<String> beneficiaryNames = utils.getBeneficiaryList("1234567890", "testname", "338");
        ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()),
                R.layout.support_simple_spinner_dropdown_item, beneficiaryNames);
        if (beneficiaryNames.isEmpty() || beneficiaryNames == null){
            beneficiariesSpinner.setEnabled(false);
        } else {
            beneficiariesSpinner.setAdapter(namesAdapter);
        }

//        utils.getAllBanks();
//        utils.processNewTransaction("RMB000000000003","ITZCASH  CARD LTD",
//                "1234567890", "TREG00000005659","BFC000000001114","338", "IMPS");
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
                mListener.goToConformation();
                break;
        }
    }
}
