package com.rokad.mobile_recharge.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rokad.R;
import com.rokad.mobile_recharge.interfaces.OnMobileRechargeListener;
import com.rokad.mobile_recharge.interfaces.RecyclerOnClickHandler;
import com.rokad.mobile_recharge.models.SubscriberModule;
import com.rokad.mobile_recharge.adapters.SubscriberListAdapter;
import com.rokad.utilities.views.BaseFragment;

import java.util.ArrayList;
import java.util.Objects;

public class RechargeHomeFragment extends BaseFragment implements View.OnClickListener,
        RecyclerOnClickHandler, AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener, View.OnFocusChangeListener{

    private ArrayList<SubscriberModule> subscriberModules = new ArrayList<>();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText mobileRechargeNum, rechargeAmount;
    private String mParam1;
    private String mParam2;
    private OnMobileRechargeListener mListener;
    private ArrayAdapter<String> statesSpinnerAdapter;

    private RadioGroup rechargeTypeGroup;
     RadioButton rechargeType;
    private String racType;
    private boolean perfectMobileNumer;
//    private AppCompatSpinner stateSelector;

    public RechargeHomeFragment() {
        // Required empty public constructor
    }

    public static RechargeHomeFragment newInstance(String param1, String param2) {
        RechargeHomeFragment fragment = new RechargeHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnMobileRechargeListener) {
            mListener = (OnMobileRechargeListener)context;
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
        View view = inflater.inflate(R.layout.fragment_recharge_home, container, false);

        rechargeTypeGroup = view.findViewById(R.id.recharge_type_group);
        rechargeTypeGroup.setOnCheckedChangeListener(this::onCheckedChanged);

        RecyclerView recyclerView = view.findViewById(R.id.services_list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        subscriberModules.clear();
        subscriberModules.add(new SubscriberModule(0, R.drawable.airtel, "Airtel", "A"));
        subscriberModules.add(new SubscriberModule(1, R.drawable.jio, "Reliance GSM", "R"));
        subscriberModules.add(new SubscriberModule(2, R.drawable.bsnl, "BSNL", "B"));
        subscriberModules.add(new SubscriberModule(3, R.drawable.idea, "Idea", "I"));
        subscriberModules.add(new SubscriberModule(4, R.drawable.vodafone, "Vodafone", "V"));

        SubscriberListAdapter listRecyclerView = new SubscriberListAdapter(chosenSubscriber -> onClick(chosenSubscriber),getContext(), subscriberModules);
        recyclerView.setAdapter(listRecyclerView);

        AppCompatSpinner stateSelector = view.findViewById(R.id.state_select);

        String[] States = {"Maharashtra Goa"};
        statesSpinnerAdapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,States);
        stateSelector.setAdapter(statesSpinnerAdapter);

        stateSelector.setPrompt("Select State");
        stateSelector.setOnItemSelectedListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.mobile_recharge_nxt_btn).setOnClickListener(this);
        view.findViewById(R.id.see_plans).setOnClickListener(this);
        mobileRechargeNum = view.findViewById(R.id.mobile_recharge_num);
        mobileRechargeNum.setOnFocusChangeListener(this::onFocusChange);
        rechargeAmount = view.findViewById(R.id.recharge_amount);
    }

    private boolean isValidMobile(String phone) {
        if ((phone != null &&  !phone.isEmpty())
                &&
        (Integer.parseInt(String.valueOf(phone.charAt(0))) > 5 && Integer.parseInt(String.valueOf(phone.charAt(0))) < 10))
         {
            return android.util.Patterns.PHONE.matcher(phone).matches();
        }
        else {
            showDialog("Sorry!!","Please enter a valid mobile number");
        }
        return false;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.recharge_type_prepaid:
                    racType = getResources().getString(R.string.prepaid_radio_btn);
                break;
            case R.id.recharge_type_postpaid:
                racType = getResources().getString(R.string.postpaid_radio_btn);
                break;
        }
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){
            case R.id.mobile_recharge_nxt_btn:
                String phone = Objects.requireNonNull(mobileRechargeNum.getText()).toString();
                String amount = Objects.requireNonNull(rechargeAmount.getText()).toString();
                if(null == phone || phone.length() < 10) {
                    showDialog("Sorry!!", "Please enter a valid phone number");
                } else if(null == amount || amount.length() <= 0) {
                    showDialog("Sorry!!", "Please enter a valid amount. You can choose it from available plans.");
                } else if(mListener.getMobileRechargeModule().getPreOperator().length() <= 0) {
                    showDialog("Sorry!!", "Please select the Operator.");
                } else if (rechargeTypeGroup.getCheckedRadioButtonId() == -1){
                    showDialog("Sorry!!", "Please check if your recharge type is Prepaid or Postpaid");
                }
                else if (perfectMobileNumer){
                    mListener.getMobileRechargeModule().setMobileNumber(phone);
                    mListener.getMobileRechargeModule().setRechargeAmount(amount);
                    mListener.getMobileRechargeModule().setRecType(racType);
                    mListener.goToMakePaymentFragment();
                }
                break;

            case R.id.see_plans:
                mListener.goToSeePlansFragment();
                break;
            default:
                throw new UnsupportedOperationException("Don't know where you've clicked");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(getActivity()).setTitle("New Recharge");
    }

    @Override
    public void onClick(int chosenSubscriber) {
        if(chosenSubscriber == -1) {
            mListener.getMobileRechargeModule().setPreOperator("");
            mListener.getMobileRechargeModule().setMobileOperator("");
        } else {
            subscriberModules.get(chosenSubscriber);
            mListener.getMobileRechargeModule().setPreOperator(subscriberModules.get(chosenSubscriber).getKey());
            mListener.getMobileRechargeModule().setImage(subscriberModules.get(chosenSubscriber).getImage());
            mListener.getMobileRechargeModule().setMobileOperator(subscriberModules.get(chosenSubscriber).getName());
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getContext(),"Selected State : " + statesSpinnerAdapter.getItem(position),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();

        switch (id){
            case R.id.mobile_recharge_num:
                if (!hasFocus){
                    String phone = mobileRechargeNum.getText().toString();
                    perfectMobileNumer = isValidMobile(phone);
                }
            break;
        }
    }

}
