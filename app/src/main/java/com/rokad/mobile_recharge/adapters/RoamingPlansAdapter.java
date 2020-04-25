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
import com.rokad.mobile_recharge.models.mPlans.Romaing;

import java.util.List;

public class RoamingPlansAdapter extends RecyclerView.Adapter<RoamingPlansAdapter.PlanHolder> implements RecyclerOnClickHandler {


    private RecyclerOnClickHandler mRecyclerOnClickHandler;
    private Context mContext;
    private List<Romaing> roaming;

    public RoamingPlansAdapter(RecyclerOnClickHandler mRecyclerOnClickHandler, Context mContext, List<Romaing> roaming) {
        this.mRecyclerOnClickHandler = mRecyclerOnClickHandler;
        this.mContext = mContext;
        this.roaming = roaming;
    }

    @NonNull
    @Override
    public PlanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recharge_plan_item,parent, false);
        return new RoamingPlansAdapter.PlanHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanHolder holder, int position) {
        holder.desc.setText(roaming.get(position).getDesc());
        holder.planPrice.setText(String.valueOf(roaming.get(position).getRs()));
        holder.validity.setText(roaming.get(position).getValidity());
        holder.lastUpdate.setText(roaming.get(position).getLastUpdate());
    }

    @Override
    public int getItemCount() {
        return roaming.size();
    }

    @Override
    public void onClick(int chosenSubscriber) {
        mRecyclerOnClickHandler.onClick(chosenSubscriber);
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
