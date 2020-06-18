package com.example.cricketleague.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cricketleague.R;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.ResModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddTeamScoreActivity  extends AppCompatActivity {
    TextView tv_team1_score,tv_team2_score;
    EditText etTeam1Score,etTeam2Score,etResult;
    Button btnAddResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team_score);
        getSupportActionBar().setTitle("Add Scores");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_team1_score=(TextView)findViewById(R.id.tv_team1_score);
        tv_team1_score.setText(getIntent().getStringExtra("team1")+" Score");

        tv_team2_score=(TextView)findViewById(R.id.tv_team2_score);
        tv_team2_score.setText(getIntent().getStringExtra("team2")+" Score");

        etTeam1Score=(EditText)findViewById(R.id.etTeam1Score);
        etTeam2Score=(EditText)findViewById(R.id.etTeam2Score);
        etResult=(EditText)findViewById(R.id.etResult);
        btnAddResult=(Button)findViewById(R.id.btnAddResult);
        btnAddResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addScore();
            }
        });
    }

    ProgressDialog pd;
    private void addScore(){
        pd = new ProgressDialog(AddTeamScoreActivity.this);
        pd.setTitle("Please wait,Data is being submitted.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<ResModel> call = api.addTeamResultScore(getIntent().getStringExtra("id"),etTeam1Score.getText().toString(),etTeam2Score.getText().toString(),etResult.getText().toString());
        call.enqueue(new Callback<ResModel>() {
            @Override
            public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    ResModel rm=response.body();
                    if(rm.getStatus().equals("true")){
                        Toast.makeText(AddTeamScoreActivity.this,"score is added successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }else{
                        Toast.makeText(AddTeamScoreActivity.this,"score is already added",Toast.LENGTH_SHORT).show();
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
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}