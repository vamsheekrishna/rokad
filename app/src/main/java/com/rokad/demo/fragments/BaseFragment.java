package com.rokad.demo.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BaseFragment extends Fragment {
    public static final String DIALOG = "dialog";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!internetConnectionAvailable(3000)) {
            try {
                AlertDialog.Builder builder =new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
                builder.setTitle("No internet Connection");
                builder.setMessage("Please turn on internet connection to continue");
                builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Objects.requireNonNull(getActivity()).finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

//                AlertDialog alertDialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity())).create();
//
//                alertDialog.setTitle("Info");
//                alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
//                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
//                alertDialog.show();
            } catch (Exception e) {
                Log.d("SyncStateContract.Constants.TAG", "Show Dialog: " + e.getMessage());
            }
        }
        //Toast.makeText(getActivity(), String.valueOf(this.getTag()), Toast.LENGTH_SHORT).show();
    }

    public boolean internetConnectionAvailable(int timeOut) {
        InetAddress inetAddress = null;
        try {
            Future<InetAddress> future = Executors.newSingleThreadExecutor().submit(new Callable<InetAddress>() {
                @Override
                public InetAddress call() {
                    try {
                        return InetAddress.getByName("google.com");
                    } catch (UnknownHostException e) {
                        return null;
                    }
                }
            });
            inetAddress = future.get(timeOut, TimeUnit.MILLISECONDS);
            future.cancel(true);
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        } catch (TimeoutException e) {
        }
        return inetAddress!=null && !inetAddress.equals("");
    }
//    void showDialog(String score, int bg_image, String title) {
//        FragmentTransaction ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
//        ft.addToBackStack(null);
//        final DialogFragment dialogFragment = DialogBoxFragment.newInstance(title ,score, bg_image);
//        dialogFragment.show(ft, DIALOG);
//
//        int SPLASH_TIME_OUT = 3000;
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                dialogFragment.dismiss();
//                Objects.requireNonNull(getActivity()).onBackPressed();
//            }
//        }, SPLASH_TIME_OUT);
//    }
}
