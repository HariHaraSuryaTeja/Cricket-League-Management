package com.example.cricketleague.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cricketleague.R;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.ResModel;
import com.example.cricketleague.models.TeamModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditManagerActivity  extends AppCompatActivity {
    EditText etManagerName,etPhno,etEmailID;
    Button btnAddManger;
    TextView team;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_manager);
        getSupportActionBar().setTitle("Edit Manager");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        team=(TextView)findViewById(R.id.spTeamName);

        team.setText(getIntent().getStringExtra("team"));
        etManagerName =(EditText)findViewById(R.id.etManagerName);
        etManagerName.setText(getIntent().getStringExtra("name"));
        etPhno =(EditText)findViewById(R.id.etPhno);
        etPhno.setText(getIntent().getStringExtra("phno"));
        etEmailID =(EditText)findViewById(R.id.etEmailID);
        etEmailID.setText(getIntent().getStringExtra("email"));
        btnAddManger =(Button) findViewById(R.id.btnAddManger);
        btnAddManger.setText("Edit Manager");
        btnAddManger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editManager();
            }
        });
    }

    ProgressDialog pd;
    private void editManager(){
        pd = new ProgressDialog(EditManagerActivity.this);
        pd.setTitle("Please wait,Data is being submitted.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<ResModel> call = api.editManager(getIntent().getStringExtra("id"),etManagerName.getText().toString(),etPhno.getText().toString(),etEmailID.getText().toString(),team.getText().toString());
        call.enqueue(new Callback<ResModel>() {
            @Override
            public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    ResModel rm=response.body();
                    if(rm.getStatus().equals("true")){
                        Toast.makeText(EditManagerActivity.this,rm.getMessage(),Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),TeamDetails1Activity.class);
                        intent.putExtra("team_name",getIntent().getStringExtra("team"));
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(EditManagerActivity.this,rm.getMessage(),Toast.LENGTH_SHORT).show();
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
                Intent intent=new Intent(getApplicationContext(),TeamDetails1Activity.class);
                intent.putExtra("team_name",getIntent().getStringExtra("team"));
                startActivity(intent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}