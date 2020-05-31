package com.rokad.AEPS;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.rokad.R;
import com.rokad.home.MyServiceRecyclerViewAdapter;

public class ReceiptSplitupRecyclerAdapter extends RecyclerView.Adapter<ReceiptSplitupRecyclerAdapter.ViewHolder> {

    private int serialNumber = 1;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_split_up_aeps_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.serialNumber.setText(String.valueOf(serialNumber++));
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView serialNumber,serviceName,amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serialNumber = itemView.findViewById(R.id.serial_num);
            serviceName = itemView.findViewById(R.id.service_name_split);
            amount = itemView.findViewById(R.id.paid_amt);
        }
    }
}
