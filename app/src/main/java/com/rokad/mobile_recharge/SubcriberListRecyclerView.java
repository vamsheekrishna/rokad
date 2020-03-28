package com.rokad.mobile_recharge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.rokad.R;

import java.util.ArrayList;

public class SubcriberListRecyclerView extends RecyclerView.Adapter<SubcriberListRecyclerView.ListViewHolder> {

    ArrayList<Integer> subscriberImgs;

    public SubcriberListRecyclerView() {
        subscriberImgs = new ArrayList<>();
        subscriberImgs.add(R.drawable.airtel);
        subscriberImgs.add(R.drawable.jio);
        subscriberImgs.add(R.drawable.bsnl);
        subscriberImgs.add(R.drawable.idea);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subscriber_list_item,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.subscriberImg.setImageResource(subscriberImgs.get(position));
    }

    @Override
    public int getItemCount() {
        return subscriberImgs.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder{

        AppCompatImageView subscriberImg;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            subscriberImg = itemView.findViewById(R.id.subscriber_img);
        }
    }
}