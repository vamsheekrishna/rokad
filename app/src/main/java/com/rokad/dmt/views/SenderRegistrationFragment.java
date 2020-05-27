package com.rokad.dmt.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;

import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;

import com.rokad.BuildConfig;
import com.rokad.R;
import com.rokad.model.UserData;
import com.rokad.dmt.interfaces.OnDMTInteractionListener;
import com.rokad.dmt.pojos.SenderRegistration.SenderData;
import com.rokad.dmt.pojos.SenderRegistrationResponsePOJO;
import com.rokad.rokad_api.RetrofitClientInstance;
import com.rokad.rokad_api.endpoints.DMTModuleService;
import com.rokad.utilities.Utils;
import com.rokad.utilities.views.BaseFragment;
import com.rokad.utilities.views.EditTextWithTitleAndThumbIcon;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SenderRegistrationFragment extends BaseFragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String STATE_NAME = "state_name";
    public static final String STATE_CODE = "state_code";

    private String mParam1;
    private String mParam2;
    private OnDMTInteractionListener mListener;

    private EditTextWithTitleAndThumbIcon firstName, lastName,senderMobileNumber;
    private AppCompatSpinner stateSelector;
    private HashMap<String, String> stateDetails;
    private Call<SenderRegistrationResponsePOJO> senderRegistration;

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
    public void onStop() {
        super.onStop();
        if(null != senderRegistration) {
            senderRegistration.cancel();
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
        view.findViewById(R.id.send_otp).setOnClickListener(this::onClick);
        firstName = view.findViewById(R.id.fst_name);
        lastName = view.findViewById(R.id.lst_name);
        senderMobileNumber = view.findViewById(R.id.sender_mobile_num);
        senderMobileNumber.accessEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(10);
        senderMobileNumber.accessEditText().setFilters(fArray);
        stateSelector = view.findViewById(R.id.states_list);
        TypedArray selectedValue = getResources().obtainTypedArray(R.array.dmt_states_input_params);
         stateDetails = new HashMap<>();

        stateSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                stateDetails.put(STATE_NAME,stateSelector.getSelectedItem().toString());
                stateDetails.put(STATE_CODE, String.valueOf(selectedValue.getInt(position, -1)));
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
                String mobileNumber = senderMobileNumber.accessEditText().getText().toString();
                if (fstName.length() < 2 || !Utils.isValidWord(fstName)){
                    showDialog("Sorry!!", "Please enter your first name without any spaces and special characters");
                } else if ( lstName.length() < 2 || !Utils.isValidWord(lstName)){
                    showDialog("Sorry!!", "Please enter your last name without any spaces and special characters");
                } else  if (!Utils.isValidMobile(mobileNumber)){
                    showDialog("Sorry!!", "Please enter your valid mobile number");
                } else if (stateSelector.getSelectedItem().equals(getString(R.string.spinner_prompt))){
                    showDialog("Sorry!!", "Please select your State");
                } else {
                    progressBar.show();
                    String code=stateDetails.get(STATE_CODE);
                    senderRegistration = RetrofitClientInstance.getRetrofitInstance().create(DMTModuleService.class).senderRegistration(mobileNumber, fstName, lstName, code, UserData.getUserData().getId(),
                            BuildConfig.MOBILE_APPLICATION,
                            BuildConfig.MOBILE_VERSION_ID);
                    senderRegistration.enqueue(new Callback<SenderRegistrationResponsePOJO>() {
                        @Override
                        public void onResponse(Call<SenderRegistrationResponsePOJO> call, Response<SenderRegistrationResponsePOJO> response) {
                            if (response.isSuccessful()) {
                                SenderRegistrationResponsePOJO data = response.body();
                                if (data.getStatus().equals("Success")) {
                                    SenderData senderData = data.getSenderData();
                                    mListener.showCustomOTPDialog(senderData, null);
                                } else {
                                    String text = response.body().getMsg();
                                    //String text1 = response.body().getError();
                                    showDialog("", text + " ");
                                }
                            } else {
                                showDialog("", response.message());
                            }
                            progressBar.cancel();
                        }
                        @Override
                        public void onFailure(Call<SenderRegistrationResponsePOJO> call, Throwable t) {
                            showDialog("", "Please try again in some time.");
                            progressBar.cancel();
                        }
                    });
                }
                break;
        }
    }
}
