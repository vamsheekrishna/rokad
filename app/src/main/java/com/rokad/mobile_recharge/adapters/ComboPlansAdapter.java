package com.rokad.mobile_recharge.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.rokad.R;
import com.rokad.mobile_recharge.interfaces.RecyclerOnClickHandler;
import com.rokad.mobile_recharge.models.mPlans.COMBO;
import com.rokad.mobile_recharge.views.MobileHomeFragment;
import com.rokad.mobile_recharge.views.RechargeHomeFragment;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ComboPlansAdapter extends RecyclerView.Adapter<ComboPlansAdapter.PlanHolder> implements RecyclerOnClickHandler {

    private RecyclerOnClickHandler mRecyclerOnClickHandler;
    private FragmentActivity mContext;
    private List<COMBO> combo;
    private List<Fragment> test;

    public ComboPlansAdapter(RecyclerOnClickHandler mRecyclerOnClickHandler, FragmentActivity mContext, List<COMBO> combo) {
        this.mRecyclerOnClickHandler = mRecyclerOnClickHandler;
        this.mContext = mContext;
        this.combo = combo;
    }

    @NonNull
    @Override
    public PlanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recharge_plan_item,parent, false);

        return new ComboPlansAdapter.PlanHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanHolder holder, int position) {
        holder.desc.setText(combo.get(position).getDesc());
        holder.planPrice.setText(String.valueOf(combo.get(position).getRs()));
        holder.validity.setText(combo.get(position).getValidity());
        holder.lastUpdate.setText(combo.get(position).getLastUpdate());

        holder.chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerOnClickHandler.onClick(combo.get(position).getRs());
                Intent intent = new Intent(mContext, mContext.getClass());
                intent.putExtra("planPrice", combo.get(position).getRs());
                mContext.getSupportFragmentManager().getFragments().get(0).onActivityResult(
                        mContext.getSupportFragmentManager().getFragments().get(0).getTargetRequestCode(), RESULT_OK, intent);

                mContext.getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public int getItemCount() {
        return combo.size();
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
