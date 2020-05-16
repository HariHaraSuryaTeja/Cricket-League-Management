package com.example.cricketleague;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class leagueLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_login);
        EditText leagueun=(EditText)findViewById(R.id.leagueun);
        EditText leaguepwd=(EditText)findViewById(R.id.leaguepwd);
        Button login=(Button)findViewById(R.id.leaguelogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(leagueLogin.this,leagueHome.class);
                startActivity(i);
            }
        });
    }
}
