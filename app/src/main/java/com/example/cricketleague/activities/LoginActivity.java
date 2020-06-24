package com.example.cricketleague.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cricketleague.R;
import com.example.cricketleague.adapters.PlayersAdapter;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.PlayerModel;
import com.example.cricketleague.models.ResModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    Button bt_signup,bt_signin;
    EditText et_uname,et_pwd;
    Spinner spUserType;
    TextView tv_guest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        et_uname=(EditText)findViewById(R.id.et_uname);
        et_pwd=(EditText)findViewById(R.id.et_pwd);
        spUserType=(Spinner)findViewById(R.id.spUserType);
        tv_guest=(TextView)findViewById(R.id.tv_guest);
        tv_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, GuestsDashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_signup=(Button)findViewById(R.id.bt_signup);
        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        bt_signin=(Button)findViewById(R.id.bt_signin);
        bt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pref = getApplicationContext().getSharedPreferences("cpl", 0);
                if(spUserType.getSelectedItem().toString().equals("Admin")){
                    loginAdmin();
                }else if(spUserType.getSelectedItem().toString().equals("Manager")){
                    loginManager();
                }else{
                    Intent intent = new Intent(LoginActivity.this, GuestsDashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    SharedPreferences pref;
    ProgressDialog pd;
    private void loginAdmin(){
        pd = new ProgressDialog(LoginActivity.this);
        pd.setTitle("Please wait,Data is being loaded.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<ResModel> call = api.loginAdmin(et_uname.getText().toString(),et_pwd.getText().toString());
        call.enqueue(new Callback<ResModel>() {
            @Override
            public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    ResModel rm=response.body();
                    if(rm.getStatus().equals("true")) {
                        SharedPreferences.Editor et=pref.edit();
                        et.putString("team_access","all");
                        et.commit();
                        Toast.makeText(LoginActivity.this, "Login successful.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Sorry Invalid Username/Password.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResModel> call, Throwable t) {
                pd.dismiss();
            }
        });
    }

    private void loginManager(){
        pd = new ProgressDialog(LoginActivity.this);
        pd.setTitle("Please wait,Data is being loaded.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<ResModel> call = api.loginManager(et_uname.getText().toString(),et_pwd.getText().toString());
        call.enqueue(new Callback<ResModel>() {
            @Override
            public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    ResModel rm=response.body();
                    if(rm.getStatus().equals("true")) {
                        SharedPreferences.Editor et=pref.edit();
                        et.putString("team_access",rm.getMessage());
                        et.commit();
                        Toast.makeText(LoginActivity.this, "" + rm.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity1.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Sorry Invalid Username/Password.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResModel> call, Throwable t) {
                pd.dismiss();
            }
        });
    }
}
