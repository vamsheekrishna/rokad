package com.rokad.mobile_recharge.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rokad.BuildConfig;
import com.rokad.R;
import com.rokad.authentication.UserData;
import com.rokad.mobile_recharge.adapters.SubscriberListAdapter;
import com.rokad.mobile_recharge.interfaces.OnMobileRechargeListener;
import com.rokad.mobile_recharge.interfaces.RecyclerOnClickHandler;
import com.rokad.mobile_recharge.models.SubscriberModule;
import com.rokad.rokad_api.RetrofitClientInstance;
import com.rokad.rokad_api.endpoints.MobileRechargeService;
import com.rokad.rokad_api.endpoints.pojos.ResponseGetPlans;
import com.rokad.utilities.views.BaseFragment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rokad.utilities.Utils.isValidMobile;

public class RechargeHomeFragment extends BaseFragment implements View.OnClickListener,
        RecyclerOnClickHandler, RadioGroup.OnCheckedChangeListener{

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
    private RecyclerView recyclerView;
    private AppCompatTextView noServiceTxtView;
    private LinearLayout amountLayout;
    private AppCompatButton nxtBtn;
    private AppCompatSpinner stateSelector;
    private String subscriberName;
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
        noServiceTxtView = view.findViewById(R.id.no_service_msg);
        rechargeTypeGroup.setOnCheckedChangeListener(this::onCheckedChanged);

        amountLayout = view.findViewById(R.id.amount);
        recyclerView = view.findViewById(R.id.services_list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        subscriberModules.clear();

         stateSelector = view.findViewById(R.id.state_select);
        return view;
    }

    private void displayPrepaidSubscriberList(){
        recyclerView.setVisibility(View.VISIBLE);
        noServiceTxtView.setVisibility(View.GONE);
        amountLayout.setVisibility(View.VISIBLE);
        nxtBtn.setVisibility(View.VISIBLE);

        subscriberModules.clear();

        subscriberModules.add(new SubscriberModule(0, R.drawable.airtel, "AirtelExpress", "AE"));
        subscriberModules.add(new SubscriberModule(1, R.drawable.reliance, "Reliance GSM", "RG"));
        subscriberModules.add(new SubscriberModule(2, R.drawable.bsnl, "BSNL", "B"));
        subscriberModules.add(new SubscriberModule(3, R.drawable.idea, "Idea", "I"));
        subscriberModules.add(new SubscriberModule(4, R.drawable.vodafone, "Vodafone", "V"));
        subscriberModules.add(new SubscriberModule(5,R.drawable.jio,"JIO","JOE"));
        subscriberModules.add(new SubscriberModule(6,R.drawable.docomo,"Tata Docomo","TD"));
        subscriberModules.add(new SubscriberModule(7,R.drawable.indicom,"Tata Indicom", "TI"));
        subscriberModules.add(new SubscriberModule(8,R.drawable.aircel,"Aircel", "AI"));

        SubscriberListAdapter listRecyclerView = new SubscriberListAdapter(chosenSubscriber -> onClick(chosenSubscriber),getContext(),
                subscriberModules);
        recyclerView.setAdapter(listRecyclerView);
    }

    private void displayPostpaidSubscriberList() {
        recyclerView.setVisibility(View.VISIBLE);
        noServiceTxtView.setVisibility(View.GONE);
        amountLayout.setVisibility(View.VISIBLE);
        nxtBtn.setVisibility(View.VISIBLE);

        subscriberModules.clear();
        subscriberModules.add(new SubscriberModule(0, R.drawable.airtel, "AirtelExpress", "AB"));
        subscriberModules.add(new SubscriberModule(1, R.drawable.reliance,"Reliance", "RB"));
        subscriberModules.add(new SubscriberModule(2, R.drawable.bsnl, "BSNL", "BB"));
        subscriberModules.add(new SubscriberModule(3, R.drawable.idea,  "Idea", "IB"));
        subscriberModules.add(new SubscriberModule(4, R.drawable.vodafone,  "Vodafone", "VB"));
        subscriberModules.add(new SubscriberModule(5,R.drawable.indicom,"Tata", "TB"));

        SubscriberListAdapter listRecyclerView = new SubscriberListAdapter(chosenSubscriber -> onClick(chosenSubscriber),getContext(),
                subscriberModules);
        recyclerView.setAdapter(listRecyclerView);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nxtBtn = view.findViewById(R.id.mobile_recharge_nxt_btn);
        nxtBtn.setOnClickListener(this::onClick);
        view.findViewById(R.id.see_plans).setOnClickListener(this);
        mobileRechargeNum = view.findViewById(R.id.mobile_recharge_num);
        mobileRechargeNum.setText(BuildConfig.USERNAME);
        rechargeAmount = view.findViewById(R.id.recharge_amount);
    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.recharge_type_prepaid:
                    racType = getResources().getString(R.string.prepaid_radio_btn);
                    mListener.getMobileRechargeModule().setPlanType("Prepaid Mobile");
                    displayPrepaidSubscriberList();
                break;
            case R.id.recharge_type_postpaid:
                racType = getResources().getString(R.string.postpaid_radio_btn);
                mListener.getMobileRechargeModule().setPlanType("Postpaid Mobile");
                displayPostpaidSubscriberList();
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
                if(!isValidMobile(phone)) {
                    showDialog("Sorry!!", getString(R.string.phone_number_check_msg));
                }else if (rechargeTypeGroup.getCheckedRadioButtonId() == -1){
                    showDialog("Sorry!!", getString(R.string.recharge_type_chk_msg));
                } else if(mListener.getMobileRechargeModule().getPreOperator().length() <= 0) {
                    showDialog("Sorry!!", getString(R.string.mobile_operator_check_msg));
                } else if (String.valueOf(stateSelector.getSelectedItem()).equals(getString(R.string.spinner_prompt))){
                    showDialog("Sorry!!", getString(R.string.spinner_prompt));
                } else if(amount.isEmpty() || Integer.parseInt(amount) <= 9) {
                    showDialog("Sorry!!", getString(R.string.valid_recharge_amt_check_msg));
                }
                else {
                    mListener.getMobileRechargeModule().setMobileNumber(phone);
                    mListener.getMobileRechargeModule().setRechargeAmount(amount);
                    mListener.getMobileRechargeModule().setRecType(racType);
                    mListener.getMobileRechargeModule().setStateName(String.valueOf(stateSelector.getSelectedItem()));
                    BigDecimal balance = new BigDecimal(UserData.getInstance().getWalletBalance());
                    BigDecimal rechargeAmount = new BigDecimal(mListener.getMobileRechargeModule().getRechargeAmount());
                    if(balance.compareTo(rechargeAmount) >= 0) {
                        mListener.goToMakePaymentFragment();
                    } else  {
                        showDialog("", "Insufficient balance in your wallet.");
                    }
                }
                break;

            case R.id.see_plans:
                MobileRechargeService rechargeService = RetrofitClientInstance.getRetrofitInstance().create(MobileRechargeService.class);

                if (subscriberName.isEmpty() && stateSelector.getSelectedItem().equals(getString(R.string.spinner_prompt))
                && rechargeTypeGroup.getCheckedRadioButtonId() == -1){
                    showDialog("Sorry!!", "Please choose your Mobile Operator, your State, Recharge type above.");
                } else {

                    ProgressDialog progressBar = new ProgressDialog(getActivity(), R.style.mySpinnerTheme);
                    progressBar.setCancelable(false);
                    progressBar.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                    progressBar.show();

                    String rechargeType = racType.equals(getString(R.string.prepaid_radio_btn)) ? "P" : "PO";

                    Call<ResponseGetPlans> getPlansCall = rechargeService.getPlans(subscriberName,
                            stateSelector.getSelectedItem().toString(), rechargeType);
                    getPlansCall.enqueue(new Callback<ResponseGetPlans>() {
                        @Override
                        public void onResponse(Call<ResponseGetPlans> call, Response<ResponseGetPlans> response) {
                            progressBar.dismiss();
                            Toast.makeText(getContext(),"body : " + response.body(),Toast.LENGTH_SHORT).show();
                        }



                        @Override
                        public void onFailure(Call<ResponseGetPlans> call, Throwable t) {
                            progressBar.dismiss();
                            Toast.makeText(getContext(),"body : " + t.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
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
            subscriberName = "";
            mListener.getMobileRechargeModule().setPreOperator("");
            mListener.getMobileRechargeModule().setMobileOperator("");
        } else {
            subscriberName = subscriberModules.get(chosenSubscriber).getName();
            subscriberModules.get(chosenSubscriber);
            mListener.getMobileRechargeModule().setPreOperator(subscriberModules.get(chosenSubscriber).getKey());
            mListener.getMobileRechargeModule().setImage(subscriberModules.get(chosenSubscriber).getImage());
            mListener.getMobileRechargeModule().setMobileOperator(subscriberName);
        }
    }
}
