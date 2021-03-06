package com.example.cricketleague.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;
import android.widget.TextView;

import com.example.cricketleague.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TeamPlayersScoreDetailsActivity  extends AppCompatActivity {
    Button btn_team1,btn_team2;
    public  String t1="",t2="",id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_players);
        TextView team1=(TextView)findViewById(R.id.team1);
        team1.setText(getIntent().getStringExtra("team1"));
        t1 = getIntent().getStringExtra("team1");
        t2 = getIntent().getStringExtra("team2");
        id = getIntent().getStringExtra("id");

        TextView team1_score=(TextView)findViewById(R.id.team1_score);
        team1_score.setText(getIntent().getStringExtra("team1_score"));

        TextView team2=(TextView)findViewById(R.id.team2);
        team2.setText(getIntent().getStringExtra("team2"));

        TextView team2_score=(TextView)findViewById(R.id.team2_score);
        team2_score.setText(getIntent().getStringExtra("team2_score"));

        TextView tv_result=(TextView)findViewById(R.id.tv_result);
        tv_result.setText(getIntent().getStringExtra("result"));
        btn_team1=(Button)findViewById(R.id.btn_team1);
        btn_team1.setText(getIntent().getStringExtra("team1"));
        btn_team2=(Button)findViewById(R.id.btn_team2);
        btn_team2.setText(getIntent().getStringExtra("team2"));
        btn_team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_team1.setBackgroundColor(Color.parseColor("#fcdb00"));
                btn_team2.setBackgroundColor(Color.parseColor("#d5d5d5"));
                loadFragment(new Team1PlayerScoreFragment());
            }
        });
        btn_team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_team2.setBackgroundColor(Color.parseColor("#fcdb00"));
                btn_team1.setBackgroundColor(Color.parseColor("#d5d5d5"));
                loadFragment(new Team2PlayerScoreFragment());
            }
        });
        btn_team1.setBackgroundColor(Color.parseColor("#fcdb00"));
        btn_team2.setBackgroundColor(Color.parseColor("#d5d5d5"));
        loadFragment(new Team1PlayerScoreFragment());
    }
    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}
