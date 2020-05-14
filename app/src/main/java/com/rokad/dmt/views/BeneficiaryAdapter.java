package com.rokad.dmt.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.rokad.R;
import com.rokad.dmt.pojos.beneficiaryList.Beneficiary;
import com.rokad.rokad_api.endpoints.pojos.BaseResponse;

import java.util.List;

class BeneficiaryAdapter extends BaseAdapter {
    List<Beneficiary> beneficiaryList;
    public BeneficiaryAdapter(List<Beneficiary> beneficiary) {
        beneficiaryList = beneficiary;
    }

    @Override
    public int getCount() {
        return beneficiaryList.size();
    }

    @Override
    public Beneficiary getItem(int i) {
        return beneficiaryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_view_row, viewGroup, false);
        TextView index = rowView.findViewById(R.id.index);
        // index.setText((i+1)+".");
        index.setText("");
        TextView textView = rowView.findViewById(R.id.text);
        textView.setText(getItem(i).getBeneficiaryFullName());
        return rowView;
    }
}
