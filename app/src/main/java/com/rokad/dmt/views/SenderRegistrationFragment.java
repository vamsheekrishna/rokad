package com.rokad.dmt.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;

import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;

import com.rokad.R;
import com.rokad.dmt.DMTUtilis;
import com.rokad.dmt.interfaces.OnDMTInteractionListener;
import com.rokad.utilities.Utils;
import com.rokad.utilities.views.BaseFragment;
import com.rokad.utilities.views.EditTextWithTitleAndThumbIcon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SenderRegistrationFragment extends BaseFragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private OnDMTInteractionListener mListener;

    private EditTextWithTitleAndThumbIcon firstName, lastName,senderMobileNumber;
    private AppCompatSpinner stateSelector;
    private HashMap<String, String> stateDetails;

    public SenderRegistrationFragment() {
        // Required empty public constructor
    }

    public static SenderRegistrationFragment newInstance(String param1, String param2) {
        SenderRegistrationFragment fragment = new SenderRegistrationFragment();
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
        getActivity().setTitle("Sender Registration");
        return inflater.inflate(R.layout.fragment_sender_registartion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstName = view.findViewById(R.id.fst_name);
        lastName = view.findViewById(R.id.lst_name);
        senderMobileNumber = view.findViewById(R.id.sender_mobile_num);
        senderMobileNumber.accessEditText().setInputType(InputType.TYPE_CLASS_PHONE);
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(10);
        senderMobileNumber.accessEditText().setFilters(fArray);
        stateSelector = view.findViewById(R.id.states_list);
        TypedArray selectedValue = getResources().obtainTypedArray(R.array.dmt_states_input_params);
         stateDetails = new HashMap<>();

        stateSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                stateDetails.put("state_name",stateSelector.getSelectedItem().toString());
                stateDetails.put("state_code", String.valueOf(selectedValue.getInt(position, -1)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        view.findViewById(R.id.sender_register).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sender_register:
                String fstName = firstName.accessEditText().getText().toString();
                String lstName = lastName.accessEditText().getText().toString();

                if (Utils.isValidWord(fstName) && Utils.isValidWord(lstName)){

                } else {
                    showDialog("Sorry!!", "Please enter your first and last name without any spaces and special characters");
                }

                String mobileNumber = senderMobileNumber.accessEditText().getText().toString();

                if (Utils.isValidMobile(mobileNumber)){

                } else {
                    showDialog("Sorry!!", "Please enter your valid mobile number");
                }

                if (stateSelector.getSelectedItem().equals(getString(R.string.spinner_prompt))){
                    showDialog("Sorry!!", "Please select your State");
                } else {

                    //TODO: Register Sender API call.
//                    DMTUtilis.getDMTUtilsInstance().
                }


//                Objects.requireNonNull(getActivity()).onBackPressed();
                // mListener.goToDMTHome();
                break;

            case R.id.send_otp:
                Toast.makeText(getContext(),"clicked", Toast.LENGTH_SHORT).show();
                //TODO: to be implemented.
        }
    }
}
