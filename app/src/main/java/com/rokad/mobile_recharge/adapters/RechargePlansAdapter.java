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
import com.rokad.mobile_recharge.interfaces.OnPlanSelectedHandler;
import com.rokad.mobile_recharge.models.mPlans.RechargePlans;

import java.util.List;

public class RechargePlansAdapter extends RecyclerView.Adapter<RechargePlansAdapter.PlanHolder> implements  View.OnClickListener {

    private OnPlanSelectedHandler mOnClickHandler;
    private List<RechargePlans> sms;

    public RechargePlansAdapter(OnPlanSelectedHandler mOnClickHandler, Context mContext, List<RechargePlans> sms) {
        this.mOnClickHandler = mOnClickHandler;
        this.sms = sms;
    }

    @NonNull
    @Override
    public PlanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recharge_plan_item,parent, false);
        PlanHolder viewObject = new PlanHolder(view);
        viewObject.mItemView.setOnClickListener(this);
        return viewObject;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanHolder holder, int position) {
        holder.desc.setText(sms.get(position).getDesc());
        holder.planPrice.setText(String.valueOf(sms.get(position).getRs()));
        holder.validity.setText(sms.get(position).getValidity());
        holder.lastUpdate.setText(sms.get(position).getLastUpdate());
        holder.mItemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return sms.size();
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        mOnClickHandler.onClick(sms.get(position));
    }

    static class PlanHolder extends RecyclerView.ViewHolder{

        AppCompatTextView desc, validity,lastUpdate, planPrice;
        AppCompatButton chooseButton;
        View mItemView;
        PlanHolder(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView;
            desc = itemView.findViewById(R.id.desc);
            validity = itemView.findViewById(R.id.validity);
            lastUpdate = itemView.findViewById(R.id.last_update);
            planPrice = itemView.findViewById(R.id.top_up_plan_price);
            chooseButton = itemView.findViewById(R.id.select_plan);
        }
    }
}
