package com.rokad.demo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rokad.demo.R;
import com.rokad.demo.fragments.dummy.DummyContent;
import com.rokad.demo.fragments.dummy.DummyContent.DummyItem;
import com.rokad.demo.interfaces.OnHomeInteractionListener;

public class ServicesHomeFragment extends BaseFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 3;
    private OnHomeInteractionListener mListener;

    public ServicesHomeFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ServicesHomeFragment newInstance(int columnCount) {
        ServicesHomeFragment fragment = new ServicesHomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_services_home_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            DummyContent.createDummyItem(1, R.drawable.mobile, "Mobile");
            DummyContent.createDummyItem(2, R.drawable.advance_ticket_booking, "Advance Ticket Booking");
            DummyContent.createDummyItem(3, R.drawable.insurance, "Insurance");
            DummyContent.createDummyItem(4, R.drawable.current_bus_booking, "Current Bus Booking");
            DummyContent.createDummyItem(5, R.drawable.air_hotel_booking, "Hotel Booking");
            DummyContent.createDummyItem(6, R.drawable.car_rental, "Car Rental");
            DummyContent.createDummyItem(7, R.drawable.cash_cards, "Cash Cards");
            DummyContent.createDummyItem(8, R.drawable.dth, "DTH");
            DummyContent.createDummyItem(9, R.drawable.domestic_money_transfer, "Domestic Money Transfer");
            DummyContent.createDummyItem(10, R.drawable.e_paylater, "e-Pay Later");
            DummyContent.createDummyItem(11, R.drawable.electricity, "Electricity");
            DummyContent.createDummyItem(12, R.drawable.gold_loan, "Gold Loan");
            recyclerView.setAdapter(new MyServiceRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHomeInteractionListener) {
            mListener = (OnHomeInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
