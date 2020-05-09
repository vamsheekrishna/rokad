package com.rokad.dmt.views;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rokad.dmt.pojos.BankListResponsePOJO;

import java.util.List;

class BankListAdapter extends BaseAdapter {
    List<BankListResponsePOJO.BanksList> bankList;
    public BankListAdapter(List<BankListResponsePOJO.BanksList> bankList) {
        this.bankList = bankList;
    }

    @Override
    public int getCount() {
        return bankList.size();
    }

    @Override
    public BankListResponsePOJO.BanksList getItem(int i) {
        return bankList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView =new TextView(viewGroup.getContext());
        textView.setText(getItem(i).getBankName());
        textView.setPadding(4,4,4,4);
        textView.setTextSize(16);
        return textView;
    }
}
