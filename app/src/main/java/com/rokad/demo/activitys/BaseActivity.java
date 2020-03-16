package com.rokad.demo.activitys;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.rokad.demo.R;
import com.rokad.demo.fragments.BaseFragment;

import java.util.Objects;

import static com.rokad.demo.fragments.BaseFragment.DIALOG;


public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(!Utilities.internetConnectionAvailable(30000)) {
//            showDialog(null, R.drawable.ic_launcher_foreground, getString(R.string.network_error_msg));
//        } else {
//
//        }
        // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }


    protected void replaceFragment(BaseFragment baseFragment, String fragment_id, boolean isAddToBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.base_container, baseFragment, fragment_id);
        if(isAddToBackStack) {
            fragmentTransaction.addToBackStack(fragment_id);
        }
        fragmentTransaction.commit();
    }
    protected void addFragment(BaseFragment baseFragment, String fragment_id, boolean isAddToBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.base_container, baseFragment, fragment_id);
        if(isAddToBackStack) {
            fragmentTransaction.addToBackStack(fragment_id);
        }
        fragmentTransaction.commit();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

//    void showDialog(String score, int bg_image, String title) {
//        FragmentTransaction ft = Objects.requireNonNull(this).getSupportFragmentManager().beginTransaction();
//        ft.addToBackStack(null);
//        final DialogFragment dialogFragment = DialogBoxFragment.newInstance(title ,score, bg_image);
//        dialogFragment.setCancelable(false);
//        dialogFragment.show(ft, DIALOG);
//    }
}
