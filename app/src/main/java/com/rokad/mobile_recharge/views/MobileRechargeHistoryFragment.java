package com.rokad.mobile_recharge.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rokad.R;
import com.rokad.authentication.UserData;
import com.rokad.mobile_recharge.adapters.RechargeHistoryRecyclerAdapter;
import com.rokad.mobile_recharge.interfaces.OnMobileRechargeListener;
import com.rokad.mobile_recharge.models.SubscriberModule;
import com.rokad.rokad_api.RetrofitClientInstance;
import com.rokad.rokad_api.endpoints.MobileRechargeService;
import com.rokad.rokad_api.endpoints.pojos.LastTransaction;
import com.rokad.rokad_api.endpoints.pojos.ResponseGetHistory;
import com.rokad.utilities.views.BaseFragment;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileRechargeHistoryFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private AppCompatTextView emptyView;
    private OnMobileRechargeListener mListener;

    public MobileRechargeHistoryFragment() {
        // Required empty public constructor
    }

    public static MobileRechargeHistoryFragment newInstance(String param1, String param2) {
        MobileRechargeHistoryFragment fragment = new MobileRechargeHistoryFragment();
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
        if(context instanceof OnMobileRechargeListener) {
            mListener = (OnMobileRechargeListener)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mobile_recharge_history, container, false);
        emptyView = view.findViewById(R.id.empty_view);
        return view;
    }


    public void loadRechargeHistory(){
//        ProgressDialog progressBar = new ProgressDialog(getActivity(), R.style.mySpinnerTheme);
//        progressBar.setCancelable(false);
//        progressBar.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
//        progressBar.show();
        emptyView.setVisibility(View.VISIBLE);
        emptyView.setText("Loading..!!");
        MobileRechargeService mobileRechargeService = RetrofitClientInstance.getRetrofitInstance().create(MobileRechargeService.class);
        mobileRechargeService.getHistory(UserData.getUserData().getId()).enqueue(new Callback<ResponseGetHistory>() {
            @Override
            public void onResponse(Call<ResponseGetHistory> call, Response<ResponseGetHistory> response) {
                if(response.isSuccessful()) {
                    if(response.body().getStatus().equals("success") && response.body().getLastTransaction().length > 0) {
//                        progressBar.dismiss();
                        emptyView.setVisibility(View.GONE);
                        for (LastTransaction lastTransaction : response.body().getLastTransaction()) {
                            SubscriberModule operator = mListener.getMobileRechargeModule().getPrepaidSubscriber(lastTransaction.getOperator());
                            lastTransaction.setOperatorLogo(operator.getImage());
                            lastTransaction.setOperatorName(operator.getName());
                            lastTransaction.setStateName(lastTransaction.getStateName());
                        }
                        RechargeHistoryRecyclerAdapter recyclerAdapter = new RechargeHistoryRecyclerAdapter(response.body().getLastTransaction()
                                , MobileRechargeHistoryFragment.this);
                        recyclerView.setAdapter(recyclerAdapter);
                    } else {
                        showDialog("", response.message());
                    }
                    // LastTransaction[] data = response.body().getLastTransaction();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetHistory> call, Throwable t) {
//                progressBar.dismiss();
                recyclerView.setVisibility(View.GONE);
                emptyView.setText("Please try again later.");
                emptyView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (!visible) {
            try {
                mListener.resetMobileRechargeModule();
            } catch (Exception e) {

            }
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recharge_history);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle("Mobile Recharge");
        loadRechargeHistory();
    }

    @Override
    public void onClick(View view) {
        LastTransaction data = (LastTransaction) view.getTag();
        SubscriberModule operator = mListener.getMobileRechargeModule().getPrepaidSubscriber(data.getOperator());
        mListener.getMobileRechargeModule().setMobileNumber(data.getRechargeOn());
        mListener.getMobileRechargeModule().setPlanType("Prepaid Mobile");
        mListener.getMobileRechargeModule().setRechargeType("0");
        mListener.getMobileRechargeModule().setStateName(data.getStateName());
        mListener.getMobileRechargeModule().setPreOperator(operator.getKey());
        mListener.getMobileRechargeModule().setImage(operator.getImage());
        mListener.getMobileRechargeModule().setMobileOperator(operator.getName());
        mListener.getMobileRechargeModule().setRechargeAmount(data.getLastTransactionAmount());

        mListener.goToMakePaymentFragment();
//        Toast.makeText(getActivity(), "LastTransaction: "+data.getRechargeOn(), Toast.LENGTH_LONG).show();
    }
}
