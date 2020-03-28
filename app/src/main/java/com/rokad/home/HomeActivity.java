package com.rokad.home;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.rokad.R;
import com.rokad.authentication.LoginActivity;
import com.rokad.authentication.UserData;
import com.rokad.rokad_api.endpoints.pojos.User;
import com.rokad.utilities.views.BaseNavigationDrawerActivity;

public class HomeActivity extends BaseNavigationDrawerActivity implements View.OnClickListener {


    private NavController navController;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        signOut.setOnClickListener(this);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.blankFragment);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();

        // navController = Navigation.findNavController(Objects.requireNonNull(this.getCurrentFocus()));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_services) {
            navController.navigate(R.id.servicesHomeFragment);
        } else if (id == R.id.nav_about) {
            navController.navigate(R.id.aboutFragment);
        } else if (id == R.id.nav_terms) {
            navController.navigate(R.id.termsFragment);
        }
        dl.closeDrawer(nv);
        return false;
    }

    @Override
    public void onClick(View view) {
        dl.closeDrawer(nv);
        UserData.deleteInstance();
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        finish();
    }
}
