package com.rokad.dmt.views;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;

import com.rokad.R;
import com.rokad.dmt.interfaces.OnDMTInteractionListener;
import com.rokad.utilities.Utils;
import com.rokad.utilities.views.BaseFragment;
import com.rokad.utilities.views.EditTextWithTitleAndThumbIcon;

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
        stateSelector = view.findViewById(R.id.states_list);

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

//                Objects.requireNonNull(getActivity()).onBackPressed();
                // mListener.goToDMTHome();
                break;

            case R.id.send_otp:
                //TODO: to be implemented.
        }
    }
}
