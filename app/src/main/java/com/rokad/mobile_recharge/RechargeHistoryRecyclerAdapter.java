package com.rokad.mobile_recharge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.rokad.R;

import java.util.ArrayList;

public class RechargeHistoryRecyclerAdapter extends RecyclerView.Adapter<RechargeHistoryRecyclerAdapter.HistoryHolder> {


    public RechargeHistoryRecyclerAdapter() {
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recharge_history_list_item, parent, false);

        return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        holder.subscriberImg.setImageResource(R.drawable.airtel);
        holder.planPrice.setText("Rs. 249");
        holder.stateName.setText("Telangana");
        holder.subscriberName.setText("AirTel");
        holder.mobileNum.setText("+91 1234567890");
    }

    @Override
    public int getItemCount() {
        return 1;
    }


    static class HistoryHolder extends RecyclerView.ViewHolder{

        AppCompatImageView subscriberImg;
        AppCompatTextView mobileNum, subscriberName, stateName, planPrice;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            subscriberImg = itemView.findViewById(R.id.service_provider_img);
            mobileNum = itemView.findViewById(R.id.mobile_num);
            subscriberName = itemView.findViewById(R.id.subscriber_name);
            stateName = itemView.findViewById(R.id.state_name);
            planPrice = itemView.findViewById(R.id.plan_price);
        }
    }
}
