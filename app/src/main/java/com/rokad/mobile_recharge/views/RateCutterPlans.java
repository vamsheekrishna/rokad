package com.rokad.mobile_recharge.views;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rokad.R;
import com.rokad.mobile_recharge.adapters.RateCutterPlansAdapter;
import com.rokad.mobile_recharge.interfaces.OnPlanSelectedHandler;
import com.rokad.mobile_recharge.interfaces.RecyclerOnClickHandler;
import com.rokad.mobile_recharge.models.mPlans.SM;
import com.rokad.utilities.views.BaseFragment;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RateCutterPlans#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RateCutterPlans extends BaseFragment implements OnPlanSelectedHandler {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<SM> ratecutter;

    public RateCutterPlans() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RateCutterPlans.
     */
    // TODO: Rename and change types and number of parameters
    public static RateCutterPlans newInstance(String param1, String param2) {
        RateCutterPlans fragment = new RateCutterPlans();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static BaseFragment newInstance(List<SM> ratecutter) {
        RateCutterPlans fragment = new RateCutterPlans();
        Bundle args = new Bundle();
        args.putSerializable("cutter", (Serializable) ratecutter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
          ratecutter  = (List<SM>) getArguments().getSerializable("cutter");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rate_cutter_plans, container, false);

        RecyclerView rateCutterPlansList = view.findViewById(R.id.rate_cutter_plans);

        if (ratecutter.isEmpty()){
            rateCutterPlansList.setVisibility(View.GONE);
            ((AppCompatTextView)view.findViewById(R.id.empty_view)).setVisibility(View.VISIBLE);
        } else {

            RateCutterPlansAdapter adapter = new RateCutterPlansAdapter(chosenSubscriber -> onClick(chosenSubscriber), getContext(), ratecutter);
            rateCutterPlansList.setAdapter(adapter);
            rateCutterPlansList.setHasFixedSize(true);
            rateCutterPlansList.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        return view;
    }

    @Override
    public void onClick(SM chosenSubscriber) {

    }
}
