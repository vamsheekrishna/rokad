package com.rokad.mobile_recharge;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rokad.R;
import com.rokad.utilities.views.BaseFragment;

import java.util.Objects;

public class RechargeHomeFragment extends BaseFragment implements View.OnClickListener, RecyclerOnClickHandler {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private OnMobileRechargeListener mListener;

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
        View view = inflater.inflate(R.layout.fragment_rechagre_home, container, false);

        Objects.requireNonNull(getActivity()).setTitle("New Recharge");
        RecyclerView recyclerView = view.findViewById(R.id.services_list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        SubcriberListRecyclerView listRecyclerView = new SubcriberListRecyclerView(this::onClick,getContext());
        recyclerView.setAdapter(listRecyclerView);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.mobile_recharge_nxt_btn).setOnClickListener(this);
        view.findViewById(R.id.see_plans).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.mobile_recharge_nxt_btn) {
            mListener.goToMakePaymentFragment();
        } else if(view.getId() == R.id.see_plans) {
            mListener.goToSeePlansFragment();
        }
    }

    @Override
    public void onClick(String chosenSubscriber) {

    }
}
