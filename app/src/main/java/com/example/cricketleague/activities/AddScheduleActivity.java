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

public class AddScheduleActivity  extends AppCompatActivity {
    EditText etSDate;
    Button btnAddSchedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        getSupportActionBar().setTitle("Add Schedule");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etSDate =(EditText)findViewById(R.id.etSDate);
        btnAddSchedule =(Button) findViewById(R.id.btnAddSchedule);
        btnAddSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSchedule();
            }
        });
        loadAllTeams();
    }

    ProgressDialog pd;
    private void addSchedule(){
        pd = new ProgressDialog(AddScheduleActivity.this);
        pd.setTitle("Please wait,Data is being submitted.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<ResModel> call = api.addSchedule(spTeamName1.getSelectedItem().toString(),spTeamName2.getSelectedItem().toString(),etSDate.getText().toString());
        call.enqueue(new Callback<ResModel>() {
            @Override
            public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    ResModel rm=response.body();
                    if(rm.getStatus().equals("true")){
                        Toast.makeText(AddScheduleActivity.this,rm.getMessage(),Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),ManagerListActivity.class));
                        finish();
                    }else{
                        Toast.makeText(AddScheduleActivity.this,rm.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResModel> call, Throwable t) {
                pd.dismiss();
            }
        });
    }

    Spinner spTeamName1,spTeamName2;
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
                            spTeamName1 = (Spinner) findViewById(R.id.spTeamName1);
                            spTeamName2 = (Spinner) findViewById(R.id.spTeamName2);
                            ArrayList<String> teams = new ArrayList<String>();
                            for (int i = 0; i < teams1.size(); i++) {
                                teams.add(teams1.get(i).getTeam_name());
                            }
                            ArrayAdapter<String> adp = new ArrayAdapter<String>(AddScheduleActivity.this, android.R.layout.simple_spinner_dropdown_item, teams);
                            spTeamName1.setAdapter(adp);
                            spTeamName2.setAdapter(adp);
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