package com.rokad.mobile_recharge.views;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rokad.R;
import com.rokad.mobile_recharge.interfaces.OnMobileRechargeListener;
import com.rokad.mobile_recharge.interfaces.OnPlanSelectedHandler;
import com.rokad.mobile_recharge.adapters.RechargePlansAdapter;
import com.rokad.mobile_recharge.models.mPlans.RechargePlans;
import com.rokad.utilities.views.BaseFragment;

import java.io.Serializable;
import java.util.List;

public class MobileRechargePlans extends BaseFragment implements OnPlanSelectedHandler {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private OnMobileRechargeListener mListener;
    private List<RechargePlans> sm;
    public MobileRechargePlans() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnMobileRechargeListener) {
            mListener = (OnMobileRechargeListener) context;
        }
    }

    public static MobileRechargePlans newInstance(String param1, String param2) {
        MobileRechargePlans fragment = new MobileRechargePlans();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static BaseFragment newInstance(List<RechargePlans> sm) {
        MobileRechargePlans fragment = new MobileRechargePlans();
        Bundle args = new Bundle();
        args.putSerializable("sm", (Serializable) sm);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
          sm = (List<RechargePlans>) getArguments().getSerializable("sm");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rechagrge_plans, container, false);

        RecyclerView smsPlansList = view.findViewById(R.id.sms_plans);

        if (sm.isEmpty()){
            smsPlansList.setVisibility(View.GONE);
            ((AppCompatTextView)view.findViewById(R.id.empty_view)).setVisibility(View.VISIBLE);
        } else {
            RechargePlansAdapter adapter = new RechargePlansAdapter(this, getContext(), sm);
            smsPlansList.setAdapter(adapter);
        }

        return view;
    }

    @Override
    public void onClick(RechargePlans chosenSubscriber) {
        mListener.getMobileRechargeModule().setRechargeAmount(String.valueOf(chosenSubscriber.getRs()));
        requireActivity().onBackPressed();
    }
}
