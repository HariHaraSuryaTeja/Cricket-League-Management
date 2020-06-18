package com.example.cricketleague.activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddScheduleActivity  extends AppCompatActivity {
    EditText etSDate;
    Button btnAddSchedule;
    private int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        getSupportActionBar().setTitle("Add Schedule");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        etSDate =(EditText)findViewById(R.id.etSDate);
        etSDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });
        btnAddSchedule =(Button) findViewById(R.id.btnAddSchedule);
        btnAddSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spTeamName1.getSelectedItem().toString().equals(spTeamName2.getSelectedItem().toString())){
                    Toast.makeText(AddScheduleActivity.this,"Both teams are same.",Toast.LENGTH_SHORT).show();
                }else{
                    addSchedule();
                }

            }
        });
        loadAllTeams();
    }
    String mdate="";
    private void setDate(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        etSDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        mdate=year+"-"+ (monthOfYear + 1)+"-"+dayOfMonth;

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    ProgressDialog pd;
    private void addSchedule(){
        pd = new ProgressDialog(AddScheduleActivity.this);
        pd.setTitle("Please wait,Data is being submitted.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<ResModel> call = api.addSchedule(spTeamName1.getSelectedItem().toString(),spTeamName2.getSelectedItem().toString(),mdate);
        call.enqueue(new Callback<ResModel>() {
            @Override
            public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    ResModel rm=response.body();
                    if(rm.getStatus().equals("true")){
                        Toast.makeText(AddScheduleActivity.this,rm.getMessage(),Toast.LENGTH_SHORT).show();
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
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}