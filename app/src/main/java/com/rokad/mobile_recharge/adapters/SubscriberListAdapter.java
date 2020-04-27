package com.rokad.mobile_recharge.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.rokad.R;
import com.rokad.mobile_recharge.interfaces.RecyclerOnClickHandler;
import com.rokad.mobile_recharge.models.SubscriberModule;

import java.util.ArrayList;

public class SubscriberListAdapter extends RecyclerView.Adapter<SubscriberListAdapter.ListViewHolder> implements View.OnClickListener {

    //ArrayList<Integer> subscriberImgs;
    ArrayList<SubscriberModule> subscriberModules;
    public RecyclerOnClickHandler mRecyclerOnClickHandler;
    private Context mContext;
    private int selected;
    private View selectedView;

    public SubscriberListAdapter(RecyclerOnClickHandler mRecyclerOnClickHandler, Context mContext, ArrayList<SubscriberModule> _subscriberModules) {
        this.mRecyclerOnClickHandler = mRecyclerOnClickHandler;
        this.mContext = mContext;
        subscriberModules = _subscriberModules;
        // mSelected = selected;
        /*subscriberImgs = new ArrayList<>();
        subscriberImgs.add(R.drawable.airtel);
        subscriberImgs.add(R.drawable.jio);
        subscriberImgs.add(R.drawable.bsnl);
        subscriberImgs.add(R.drawable.idea);*/
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subscriber_list_item,parent,false);
        ListViewHolder listViewHolder = new ListViewHolder(view);
        return listViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.subscriberImg.setImageResource(subscriberModules.get(position).getImage());
        holder.subscriberImg.setTag(position);
        holder.subscriberImg.setOnClickListener(this);
        if(subscriberModules.get(position).isSelected()) {
            holder.subscriberImg.setAlpha(1f);
            if(selected != position) {
                selectedView = holder.subscriberImg;
                selected = position;
            }
        } else {
            holder.subscriberImg.setAlpha(.5f);
        }
    }

    @Override
    public int getItemCount() {
        return subscriberModules.size();
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if(null != selectedView) {
            selectedView.setAlpha(.5f);
        }
        selectedView = v;
        if(selected != position) {
            subscriberModules.get(selected).setSelected(false);
        }
        selected = position;
        subscriberModules.get(position).setSelected(!subscriberModules.get(position).isSelected());

        if(subscriberModules.get(position).isSelected()) {
            v.setAlpha(1f);
            mRecyclerOnClickHandler.onClick(selected);
        } else {
            v.setAlpha(.5f);
            mRecyclerOnClickHandler.onClick(-1);
        }
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView subscriberImg;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            subscriberImg = itemView.findViewById(R.id.subscriber_img);
        }


    }
}
