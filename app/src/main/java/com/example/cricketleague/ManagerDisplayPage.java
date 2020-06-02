package com.example.cricketleague;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ManagerDisplayPage extends AppCompatActivity {
    BottomNavigationView sbnav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_display_page);
        sbnav = findViewById(R.id.managerbottom_navigation);
        sbnav.setOnNavigationItemSelectedListener(staffLister);
    }
    BottomNavigationView.OnNavigationItemSelectedListener staffLister =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem staffMenuitem) {
                    Fragment selectedFragment =null;

                    switch (staffMenuitem.getItemId()){
                        case R.id.mannav_home:
                            selectedFragment = new TeamHomeFragment();
                            break;

                        case R.id.mannav_manage:
                            selectedFragment = new TeamManageFragment();
                            break;


                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.man_fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
}
