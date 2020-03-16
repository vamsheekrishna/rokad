package com.rokad.demo.fragments;

import android.R.layout;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.rokad.demo.R;
import com.rokad.demo.interfaces.OnAuthenticationInteractionListener;

import java.util.Objects;

import fr.ganfra.materialspinner.MaterialSpinner;

import static android.R.*;

public class MobileRechargeFragment extends BaseFragment implements View.OnClickListener,AdapterView.OnItemSelectedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnMobileInteractionInteractionListener mListener;

    public MobileRechargeFragment() {
        // Required empty public constructor
    }
    public static MobileRechargeFragment newInstance(String param1, String param2) {
        MobileRechargeFragment fragment = new MobileRechargeFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mobile_recharge, container, false);
        MaterialSpinner operatorSpin = view.findViewById(R.id.select_operator);
        MaterialSpinner locationSpin = view.findViewById(R.id.state);
        AppCompatTextView choosePlansTv = view.findViewById(R.id.recharge_plans);
        operatorSpin.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()),
                layout.simple_spinner_item,
                getActivity().getResources().getStringArray(R.array.mobile_operators));
        adapter.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        operatorSpin.setAdapter(adapter);


        ArrayAdapter<String> locAdapter = new ArrayAdapter<String>(getContext(),layout.simple_spinner_item,getResources().getStringArray(R.array.states));
        locAdapter.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        locationSpin.setAdapter(locAdapter);

        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnAuthenticationInteractionListener) {
//            mListener = (OnAuthenticationInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.recharge_plans){
            getActivity().getSupportFragmentManager().beginTransaction().add(new MobileRechargeFragment(),"mobile_recharge_fragment").commit();
        }
    }
}
