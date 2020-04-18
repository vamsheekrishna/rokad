package com.rokad.dmt.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.rokad.R;

public class DMTCommissionDialogFragment extends DialogFragment implements View.OnClickListener {

    public static DMTCommissionDialogFragment newInstance(boolean b) {
        // DialogFragment fragment = new DialogFragment();
        return new DMTCommissionDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dmt_commission_dialog_fragment, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.ok_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }
}
