package com.example.cricketleague.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cricketleague.R;


public class LoginActivity extends AppCompatActivity {
    Button bt_signup,bt_signin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        bt_signup=(Button)findViewById(R.id.bt_signup);
        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             /*   Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);

*/

            }
        });
        bt_signin=(Button)findViewById(R.id.bt_signin);
        bt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
