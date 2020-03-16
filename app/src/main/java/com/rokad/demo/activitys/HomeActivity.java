package com.rokad.demo.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.rokad.demo.R;
import com.rokad.demo.fragments.AboutFragment;
import com.rokad.demo.fragments.HomeFragment;
import com.rokad.demo.fragments.ServicesHomeFragment;
import com.rokad.demo.fragments.TermsFragment;
import com.rokad.demo.fragments.dummy.DummyContent;
import com.rokad.demo.interfaces.OnHomeInteractionListener;

import java.util.Objects;

public class HomeActivity extends BaseNavigationDrawerActivity implements OnHomeInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        addFragment(HomeFragment.newInstance("", ""), "HomeFragment", false);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_services) {
            goToServicesFragment();
        } else if (id == R.id.nav_about) {
            goToAboutFragment();
        } else if (id == R.id.nav_terms) {
            goToTermsFragment();
        } else if (id == R.id.sign_out_btn) {
            goToSignOut();
        }
        dl.closeDrawer(nv);
        return false;
    }
    @Override
    public void goToHomeFragment() {
        replaceFragment(HomeFragment.newInstance("", ""), "HomeFragment", true);
    }

    @Override
    public void goToServicesFragment() {
        replaceFragment(ServicesHomeFragment.newInstance(3), "ServicesHomeFragment", true);
    }

    @Override
    public void goToAboutFragment() {
        replaceFragment(AboutFragment.newInstance("", ""), "AboutFragment", true);
    }

    @Override
    public void goToTermsFragment() {
        replaceFragment(TermsFragment.newInstance("", ""), "TermsFragment", true);
    }

    @Override
    public void goToSignOut() {
        finish();
        startActivity(new Intent(HomeActivity.this, Authentication.class));
    }

    @Override
    public void onSelectedServiceInteraction(DummyContent.DummyItem mItem) {
        if(mItem.id == 1) {
            startActivity(new Intent(HomeActivity.this, MobileRechargeActivity.class));
        } else {
            AlertDialog.Builder builder =new AlertDialog.Builder(Objects.requireNonNull(HomeActivity.this));
            builder.setTitle("Sorry....");
            builder.setMessage("Please wait, this feature will be available soon");
            builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}