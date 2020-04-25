package com.rokad.mobile_recharge.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.rokad.R;
import com.rokad.mobile_recharge.interfaces.RecyclerOnClickHandler;
import com.rokad.mobile_recharge.models.mPlans.RATECUTTER;

import java.util.List;

public class RateCutterPlansAdapter extends RecyclerView.Adapter<RateCutterPlansAdapter.PlanHolder> implements RecyclerOnClickHandler{

    private RecyclerOnClickHandler mRecyclerOnClickHandler;
    private Context mContext;
    private List<RATECUTTER> ratecutter;

    public RateCutterPlansAdapter(RecyclerOnClickHandler mRecyclerOnClickHandler, Context mContext, List<RATECUTTER> ratecutter) {
        this.mRecyclerOnClickHandler = mRecyclerOnClickHandler;
        this.mContext = mContext;
        this.ratecutter = ratecutter;
    }

    @NonNull
    @Override
    public PlanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recharge_plan_item,parent, false);
        return new RateCutterPlansAdapter.PlanHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanHolder holder, int position) {
        holder.desc.setText(ratecutter.get(position).getDesc());
        holder.planPrice.setText(String.valueOf(ratecutter.get(position).getRs()));
        holder.validity.setText(ratecutter.get(position).getValidity());
        holder.lastUpdate.setText(ratecutter.get(position).getLastUpdate());
    }

    @Override
    public int getItemCount() {
        return ratecutter.size();
    }

    @Override
    public void onClick(int chosenSubscriber) {

    }

    static class PlanHolder extends RecyclerView.ViewHolder{

        AppCompatTextView desc, validity,lastUpdate, planPrice;
        AppCompatButton chooseButton;

        PlanHolder(@NonNull View itemView) {
            super(itemView);
            desc = itemView.findViewById(R.id.desc);
            validity = itemView.findViewById(R.id.validity);
            lastUpdate = itemView.findViewById(R.id.last_update);
            planPrice = itemView.findViewById(R.id.top_up_plan_price);
            chooseButton = itemView.findViewById(R.id.select_plan);
        }
    }
}
