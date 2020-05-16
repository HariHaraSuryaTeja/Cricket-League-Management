package com.example.cricketleague;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class teamLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_login);
        EditText teamun=(EditText)findViewById(R.id.teamun);
        EditText teampwd=(EditText)findViewById(R.id.teampwd);
        Button login=(Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(teamLogin.this,teamHome.class);
                startActivity(i);
            }
        });
    }
}
