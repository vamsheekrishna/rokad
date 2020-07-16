package com.rokad.AEPS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.rokad.R;
import com.rokad.rokad_api.endpoints.pojos.AEPSBank;
import java.util.ArrayList;

class BankListAdapter extends BaseAdapter {
    private ArrayList<AEPSBank> bankList;
    public BankListAdapter(ArrayList<AEPSBank> bankList) {
        this.bankList = bankList;
    }

    @Override
    public int getCount() {
        return bankList.size();
    }

    @Override
    public AEPSBank getItem(int i) {
        return bankList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_view_row, viewGroup, false);
        TextView index = rowView.findViewById(R.id.index);
        // index.setText((i+1)+".");
        index.setText("");
        TextView textView = rowView.findViewById(R.id.text);
        textView.setText(getItem(i).getBankName());
        return rowView;
    }
}
