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
public class AddPlayer1Activity  extends AppCompatActivity {
    EditText etPlayerName,etPhno,etEmailID;
    Button btnAddPlayer;
    TextView tvTeam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player1);
        getSupportActionBar().setTitle("Add Player");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etPlayerName =(EditText)findViewById(R.id.etPlayerName);
        etPhno =(EditText)findViewById(R.id.etPhno);
        etEmailID =(EditText)findViewById(R.id.etEmailID);
        btnAddPlayer =(Button) findViewById(R.id.btnAddPlayer);
        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlayer();
            }
        });
        tvTeam =(TextView) findViewById(R.id.tvTeam);
        tvTeam.setText(getIntent().getStringExtra("team"));
    }

    ProgressDialog pd;
    private void addPlayer(){
        pd = new ProgressDialog(AddPlayer1Activity.this);
        pd.setTitle("Please wait,Data is being submitted.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<ResModel> call = api.addPlayer(etPlayerName.getText().toString(),etPhno.getText().toString(),etEmailID.getText().toString(),getIntent().getStringExtra("team"));
        call.enqueue(new Callback<ResModel>() {
            @Override
            public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    ResModel rm=response.body();
                    if(rm.getStatus().equals("true")){
                        Toast.makeText(AddPlayer1Activity.this,rm.getMessage(),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddPlayer1Activity.this, TeamDetails1Activity.class);
                        intent.putExtra("team_name",getIntent().getStringExtra("team"));
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(AddPlayer1Activity.this,rm.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResModel> call, Throwable t) {
                pd.dismiss();
            }
        });
    }

    Spinner spTeamName;
    private void loadAllTeams(){
        ApiService api = RetroClient.getApiService();
        Call<List<TeamModel>> call = api.getAllTeams();
        call.enqueue(new Callback<List<TeamModel>>() {
            @Override
            public void onResponse(Call<List<TeamModel>> call, Response<List<TeamModel>> response) {
                if (response.isSuccessful()) {
                    List<TeamModel> teams1=response.body();
                    if(teams1!=null) {
                        if(teams1.size()>0) {
                            spTeamName = (Spinner) findViewById(R.id.spTeamName);
                            ArrayList<String> teams = new ArrayList<String>();
                            for (int i = 0; i < teams1.size(); i++) {
                                teams.add(teams1.get(i).getTeam_name());
                            }
                            ArrayAdapter<String> adp = new ArrayAdapter<String>(AddPlayer1Activity.this, android.R.layout.simple_spinner_dropdown_item, teams);
                            spTeamName.setAdapter(adp);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<List<TeamModel>> call, Throwable t) {

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