package com.sourcey.materiallogindemo.Staff;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.sourcey.materiallogindemo.LoginActivity;
import com.sourcey.materiallogindemo.R;
import com.sourcey.materiallogindemo.fragment.HistoryFragment;
import com.sourcey.materiallogindemo.fragment.HomeFragment;
import com.sourcey.materiallogindemo.fragment.OrderFragment;
import com.sourcey.materiallogindemo.fragment.ProfileFragment;
import com.sourcey.materiallogindemo.fragment_staff.KamarFragment;
import com.sourcey.materiallogindemo.fragment_staff.ReservasiFragment;
import com.sourcey.materiallogindemo.fragment_staff.StafHistoryFragment;
import com.sourcey.materiallogindemo.fragment_staff.StafProfileFragment;

import static com.sourcey.materiallogindemo.LoginActivity.TAG_EMAIL;
import static com.sourcey.materiallogindemo.LoginActivity.TAG_NAMA;

public class MainStaffActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(1);
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }

        setContentView(R.layout.activity_main_staff);

        loadFragment(new ReservasiFragment());

        BottomNavigationView bottomnav = findViewById(R.id.bottom_navigation);
        bottomnav.setOnNavigationItemSelectedListener(navListener);
        if (savedInstanceState == null) {
            bottomnav.setSelectedItemId(R.id.nav_reservasi); // change to whichever id should be default
        }
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }

        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_reservasi:
                            selectedFragment = new ReservasiFragment();
                            break;
                        case R.id.nav_kamar:
                            selectedFragment = new KamarFragment();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new StafProfileFragment();
                            break;
                        case R.id.nav_history:
                            selectedFragment = new StafHistoryFragment();
                            break;
                    }

                    return loadFragment(selectedFragment);
                }
            };


}
