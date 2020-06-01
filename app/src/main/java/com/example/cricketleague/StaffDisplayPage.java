package com.example.cricketleague;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StaffDisplayPage extends AppCompatActivity {
    BottomNavigationView sbnav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_display_page);

        sbnav = findViewById(R.id.staffbottom_navigation);
        sbnav.setOnNavigationItemSelectedListener(staffLister);
    }
    BottomNavigationView.OnNavigationItemSelectedListener staffLister =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem staffMenuitem) {
                    Fragment selectedFragment =null;

                    switch (staffMenuitem.getItemId()){
                        case R.id.staffnav_home:
                            selectedFragment = new StaffHomeFragment();
                            break;

                        case R.id.staffnav_manage:
                            selectedFragment = new StaffManageFragment();
                            break;

                        case R.id.staffnav_news:
                            selectedFragment = new StaffSeasonsFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,
                            selectedFragment).commit();
                    return true;
                }
            };
}
