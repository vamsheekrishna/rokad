package com.rokad.mobile_recharge.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rokad.R;
import com.rokad.mobile_recharge.adapters.RoamingPlansAdapter;
import com.rokad.mobile_recharge.interfaces.OnMobileRechargeListener;
import com.rokad.mobile_recharge.interfaces.OnPlanSelectedHandler;
import com.rokad.mobile_recharge.models.mPlans.SM;
import com.rokad.utilities.views.BaseFragment;

import java.io.Serializable;
import java.util.List;

public class RoamingPlans extends BaseFragment implements OnPlanSelectedHandler {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<SM> roaming;

    private OnMobileRechargeListener onMobileRechargeListener;

    public RoamingPlans() {
        // Required empty public constructor
    }


    public static BaseFragment newInstance(List<SM> roaming) {
        RoamingPlans fragment = new RoamingPlans();
        Bundle args = new Bundle();
        args.putSerializable("roaming", (Serializable) roaming);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           roaming = (List<SM>) getArguments().getSerializable("roaming");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_roaming_plans, container, false);

        RecyclerView roamingPlansList = view.findViewById(R.id.roaming_plans);

        if (roaming.isEmpty()) {
            roamingPlansList.setVisibility(View.GONE);
            ((AppCompatTextView)view.findViewById(R.id.empty_view)).setVisibility(View.VISIBLE);
        } else {
            RoamingPlansAdapter adapter = new RoamingPlansAdapter(chosenSubscriber -> onClick(chosenSubscriber), getContext(), roaming);
            roamingPlansList.setAdapter(adapter);
            roamingPlansList.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        return view;
    }

    @Override
    public void onClick(SM chosenSubscriber) {

    }
}
