package com.example.cricketleague;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    TextView forgotPassword,notStaff;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameET);
        password = findViewById(R.id.passwordET);
        forgotPassword = findViewById(R.id.forgotPassTv);
        login = findViewById(R.id.loginBtn);
        notStaff = findViewById(R.id.notStaffTv);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,StaffDisplayPage.class);
                startActivity(i);
            }
        });

        notStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ns =new Intent(LoginActivity.this,MainDisplayPage.class);
                startActivity(ns);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fp = new Intent(LoginActivity.this,Forgotpassword.class);
                startActivity(fp);
            }
        });
    }
}
