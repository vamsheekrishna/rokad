package com.rokad.mobile_recharge.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.rokad.mobile_recharge.models.mPlans.PostpaidData;
import com.rokad.mobile_recharge.models.mPlans.RechargePlans;
import com.rokad.mobile_recharge.models.mPlans.Records;
import com.rokad.mobile_recharge.models.mPlans.ResponseGetPlans;
import com.rokad.mobile_recharge.models.mPlans.ResponseGetPostpaidPlans;
import com.rokad.rokad_api.RetrofitClientInstance;
import com.rokad.rokad_api.endpoints.MobileRechargeService;
import com.rokad.utilities.views.BaseFragment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
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
    Bundle saveInstanceBundle;
    private String planKey;
    // private int selectedSubscriber = -1;
    AppCompatTextView seePlans;
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
        saveInstanceBundle = new Bundle();
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
        amountLayout = view.findViewById(R.id.amount);
        recyclerView = view.findViewById(R.id.services_list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        subscriberModules.clear();

        stateSelector = view.findViewById(R.id.state_select);

        rechargeTypeGroup.setOnCheckedChangeListener(this::onCheckedChanged);

        return view;
    }



    private void displayPrepaidSubscriberList(){
        updateOperator();
        subscriberModules.clear();

        subscriberModules.add(new SubscriberModule(0, R.drawable.airtel, "AirtelExpress", "AE","Airtel"));
        subscriberModules.add(new SubscriberModule(1, R.drawable.reliance, "Reliance GSM", "RG",""));
        subscriberModules.add(new SubscriberModule(2, R.drawable.bsnl, "BSNL", "B", "BSNL"));
        subscriberModules.add(new SubscriberModule(3, R.drawable.idea, "Idea", "I", "idea"));
        subscriberModules.add(new SubscriberModule(4, R.drawable.vodafone, "Vodafone", "V", "Vodafone"));
        subscriberModules.add(new SubscriberModule(5,R.drawable.jio,"JOE","JOE", "jio"));
        subscriberModules.add(new SubscriberModule(6,R.drawable.docomo,"Tata Docomo","TD",""));
        subscriberModules.add(new SubscriberModule(7,R.drawable.indicom,"Tata Indicom", "TI","Tata Indicom"));
        subscriberModules.add(new SubscriberModule(8,R.drawable.aircel,"Aircel", "AI",""));
        if(mListener.getMobileRechargeModule().getSelectedSubscriber()!=-1) {
            subscriberModules.get(mListener.getMobileRechargeModule().getSelectedSubscriber()).setSelected(true);
        }
        SubscriberListAdapter listRecyclerView = new SubscriberListAdapter(this::onClick,getContext(),
                subscriberModules);
        recyclerView.setAdapter(listRecyclerView);
    }

    private void displayPostpaidSubscriberList() {

        updateOperator();

        subscriberModules.clear();
        subscriberModules.add(new SubscriberModule(0, R.drawable.airtel, "AirtelExpress","AB", "Airtel"));
        subscriberModules.add(new SubscriberModule(1, R.drawable.reliance,"Reliance", "RB",""));
        subscriberModules.add(new SubscriberModule(2, R.drawable.bsnl, "BSNL", "BB", "BSNL"));
        subscriberModules.add(new SubscriberModule(3, R.drawable.idea,  "Idea", "IB","idea"));
        subscriberModules.add(new SubscriberModule(4, R.drawable.vodafone,  "Vodafone", "VB", "Vodafone"));
        subscriberModules.add(new SubscriberModule(5,R.drawable.indicom,"Tata Indicom", "TB", "Tata Indicom"));
//        subscriberModules.add(new SubscriberModule(6,R.drawable.jio,"JOE","JOE","jio"));
        try {
            if(mListener.getMobileRechargeModule().getSelectedSubscriber()!=-1) {
                subscriberModules.get(mListener.getMobileRechargeModule().getSelectedSubscriber()).setSelected(true);
            }
        } catch (Exception e) { }
        SubscriberListAdapter listRecyclerView = new SubscriberListAdapter(chosenSubscriber -> onClick(chosenSubscriber),getContext(), subscriberModules);
        recyclerView.setAdapter(listRecyclerView);
    }

    private void updateOperator() {
        recyclerView.setVisibility(View.VISIBLE);
        noServiceTxtView.setVisibility(View.GONE);
        amountLayout.setVisibility(View.VISIBLE);
        nxtBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nxtBtn = view.findViewById(R.id.mobile_recharge_nxt_btn);
        nxtBtn.setOnClickListener(this::onClick);
        seePlans = view.findViewById(R.id.see_plans);
        seePlans.setOnClickListener(this);
        mobileRechargeNum = view.findViewById(R.id.mobile_recharge_num);
//        mobileRechargeNum.setText(BuildConfig.USERNAME);
        rechargeAmount = view.findViewById(R.id.recharge_amount);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.recharge_type_prepaid:
                    racType = getResources().getString(R.string.prepaid_radio_btn);
                    seePlans.setVisibility(View.VISIBLE);
                    mListener.getMobileRechargeModule().setPlanType("Prepaid Mobile");
                    mListener.getMobileRechargeModule().setRechargeType("0");
                    saveInstanceBundle.putInt("recharge_type_id",R.id.recharge_type_prepaid);
                    displayPrepaidSubscriberList();
                break;
            case R.id.recharge_type_postpaid:
                    racType = getResources().getString(R.string.postpaid_radio_btn);
                    seePlans.setVisibility(View.GONE);
                    mListener.getMobileRechargeModule().setRechargeType("1");
                    mListener.getMobileRechargeModule().setPlanType("Postpaid Mobile");
                    saveInstanceBundle.putInt("recharge_type_id",R.id.recharge_type_postpaid);
                    displayPostpaidSubscriberList();
                break;
        }
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        String phone = Objects.requireNonNull(mobileRechargeNum.getText()).toString();
        String amount = Objects.requireNonNull(rechargeAmount.getText()).toString();

        switch (id){
            case R.id.mobile_recharge_nxt_btn:

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

                if (subscriberName != null && stateSelector!= null && rechargeTypeGroup!=null && mListener != null) {
                    if (subscriberName.isEmpty() && stateSelector.getSelectedItem().equals(getString(R.string.spinner_prompt))
                            && rechargeTypeGroup.getCheckedRadioButtonId() == -1) {
                        showDialog("Sorry!!", "Please choose your Mobile Operator, your State, Recharge type above.");
                    } else if (!isValidMobile(phone)) {
                        showDialog("Sorry!!", getString(R.string.phone_number_check_msg));
                    } else if (rechargeTypeGroup.getCheckedRadioButtonId() == -1) {
                        showDialog("Sorry!!", getString(R.string.recharge_type_chk_msg));
                    } else if (mListener.getMobileRechargeModule().getPreOperator().length() <= 0) {
                        showDialog("Sorry!!", getString(R.string.mobile_operator_check_msg));
                    } else if (String.valueOf(stateSelector.getSelectedItem()).equals(getString(R.string.spinner_prompt))) {
                        showDialog("Sorry!!", getString(R.string.spinner_prompt));
                    } else {

                        ProgressDialog progressBar = new ProgressDialog(getActivity(), R.style.mySpinnerTheme);
                        progressBar.setCancelable(false);
                        progressBar.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                        progressBar.show();

                        // String rechargeType = racType.equals(getString(R.string.prepaid_radio_btn)) ? "P" : "PO";
                        if (racType.equals(getString(R.string.prepaid_radio_btn))) {
                            String state = stateSelector.getSelectedItem().toString();
                            Call<ResponseGetPlans> getPlansCall = rechargeService.getPrepaidPlans(planKey,
                                    state,"P", BuildConfig.MOBILE_APPLICATION,BuildConfig.MOBILE_VERSION_ID);
                            getPlansCall.enqueue(new Callback<ResponseGetPlans>() {
                                @Override
                                public void onResponse(Call<ResponseGetPlans> call, Response<ResponseGetPlans> response) {
                                    Records records = new Records();
                                    // progressBar.dismiss();
                                    if (response.code() == 200) {
                                        if (response.body().getStatus().equalsIgnoreCase("Success")) {
                                            records = response.body().getData().getRecords();
                                            /*if (null == records) {
                                                showDialog("", "Plans are not available");
                                            } else {
                                                // mListener.goToSeePlansFragment( response.body().getData().getRecords());
                                            }*/
                                        } else {
                                            //showDialog("Sorry!!", "Plans are not available");
                                        }
                                        getSpecialPlans(phone, rechargeService, progressBar, records);
                                    } else {
                                        showDialog("Sorry!!", "Looks like there's a network or server problem. Please try again in sometime.");
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseGetPlans> call, Throwable t) {
                                    progressBar.dismiss();
                                    Toast.makeText(getContext(), "Plans Not Available. Please try again later", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                } else {
                    showDialog("Sorry!!", "Please fill in Subscriber and State details to see the plans.");
                }

                break;
            default:
                throw new UnsupportedOperationException("Don't know where you've clicked");
        }
    }

    private void getSpecialPlans(String phone, MobileRechargeService rechargeService, ProgressDialog progressBar, Records records) {
        Call<ResponseGetPostpaidPlans> getPlansCall = rechargeService.getPostpaidPlans(planKey, stateSelector.getSelectedItem().toString(),
                "PO", phone, BuildConfig.MOBILE_APPLICATION,BuildConfig.MOBILE_VERSION_ID);
        getPlansCall.enqueue(new Callback<ResponseGetPostpaidPlans>() {
            @Override
            public void onResponse(Call<ResponseGetPostpaidPlans> call, Response<ResponseGetPostpaidPlans> response) {
                progressBar.dismiss();
                if (response.code() == 200) {
                    if (response.body().getStatus().equalsIgnoreCase("Success")) {
                        List<RechargePlans> data = response.body().getData().getRecords();
                        if (null == data || null == data.get(0).getDesc()|| data.get(0).getDesc().contains("Not Available")) {
                            // showDialog("", "Plans are not available");
                        } else {
                            records.setSpecialPlans(data);
                        }
                    } else {
                        // showDialog("Sorry!!", "Plans are not available");
                    }

                    mListener.goToSeePlansFragment(records);
                } else {
                    showDialog("Sorry!!", "Looks like there's a network or server problem. Please try again in sometime.");
                }

            }

            @Override
            public void onFailure(Call<ResponseGetPostpaidPlans> call, Throwable t) {
                progressBar.dismiss();
                Toast.makeText(getContext(), "Plans Not Available. Please try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle("New Recharge");
        rechargeAmount.setText(mListener.getMobileRechargeModule().getRechargeAmount());
//        if (selectedSubscriber != -1) {
//            SubscriberListAdapter datapter = (SubscriberListAdapter) recyclerView.getAdapter();
//            datapter.
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == 123){
                // Toast.makeText(getContext(),"kirkiriii",Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onClick(int chosenSubscriber) {
        mListener.getMobileRechargeModule().setSelectedSubscriber(chosenSubscriber);
        if(chosenSubscriber == -1) {
            subscriberName = "";
            mListener.getMobileRechargeModule().setPreOperator("");
            mListener.getMobileRechargeModule().setMobileOperator("");
        } else {
            subscriberName = subscriberModules.get(chosenSubscriber).getName();
            subscriberModules.get(chosenSubscriber);
            planKey = subscriberModules.get(chosenSubscriber).getPlanKey();
            saveInstanceBundle.putInt("chosenSubscriber", chosenSubscriber);
            mListener.getMobileRechargeModule().setPreOperator(subscriberModules.get(chosenSubscriber).getKey());
            mListener.getMobileRechargeModule().setImage(subscriberModules.get(chosenSubscriber).getImage());
            mListener.getMobileRechargeModule().setMobileOperator(subscriberName);
        }
        rechargeAmount.setText("");
    }
}
