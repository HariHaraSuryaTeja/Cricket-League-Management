package com.example.cricketleague.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cricketleague.R;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.ResModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddTeamActivity  extends AppCompatActivity {
    EditText etTeam;
    Button btnAddTeam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        getSupportActionBar().setTitle("Add Team");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etTeam =(EditText)findViewById(R.id.etTeam);
        btnAddTeam =(Button) findViewById(R.id.btnAddTeam);
        btnAddTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
addTeam();
            }
        });
    }

    ProgressDialog pd;
    private void addTeam(){
        pd = new ProgressDialog(AddTeamActivity.this);
        pd.setTitle("Please wait,Data is being submitted.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<ResModel> call = api.addTeam(etTeam.getText().toString());
        call.enqueue(new Callback<ResModel>() {
            @Override
            public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    ResModel rm=response.body();
                    if(rm.getStatus().equals("true")){
                        Toast.makeText(AddTeamActivity.this,"Team is added successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }else{
                        Toast.makeText(AddTeamActivity.this,"Team is already added", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResModel> call, Throwable t) {
                pd.dismiss();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}