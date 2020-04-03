package com.rokad.mobile_recharge.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rokad.R;
import com.rokad.mobile_recharge.interfaces.RecyclerOnClickHandler;

public class TopUpRechargePlansRecyclerAdapter extends RecyclerView.Adapter<TopUpRechargePlansRecyclerAdapter.PlanHolder> implements RecyclerOnClickHandler {

    private RecyclerOnClickHandler mRecyclerOnClickHandler;
    private Context mContext;

    public TopUpRechargePlansRecyclerAdapter(RecyclerOnClickHandler mRecyclerOnClickHandler, Context mContext) {
        this.mRecyclerOnClickHandler = mRecyclerOnClickHandler;
        this.mContext = mContext;
    }


    @Override
    public void onClick(int chosenSubscriber) {

    }

    @NonNull
    @Override
    public PlanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recharge_plan_item,parent, false);
        return new PlanHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

     static class PlanHolder extends RecyclerView.ViewHolder{

        PlanHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}