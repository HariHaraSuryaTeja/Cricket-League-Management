package com.example.cricketleague.activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditScheduleActivity  extends AppCompatActivity {
    EditText etSDate;
    Button btnEditSchedule;
    private int mYear, mMonth, mDay;
    Spinner spSeasons;
    TextView tv_team1,tv_team2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_schedule);
        getSupportActionBar().setTitle("Edit Schedule");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        etSDate =(EditText)findViewById(R.id.etSDate);
        tv_team1 =(TextView)findViewById(R.id.tv_team1);
        tv_team2 =(TextView)findViewById(R.id.tv_team2);
        tv_team1.setText(getIntent().getStringExtra("team1"));
        tv_team2.setText(getIntent().getStringExtra("team2"));
        etSDate.setText(getIntent().getStringExtra("sdate"));
        etSDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });
        btnEditSchedule =(Button) findViewById(R.id.btnEditSchedule);
        btnEditSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(etSDate.getText().toString().isEmpty()){
                    Toast.makeText(EditScheduleActivity.this,"Please select schedule date.",Toast.LENGTH_SHORT).show();
                }else{
                   editSchedule();
                }

            }
        });
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
    private void editSchedule(){
        pd = new ProgressDialog(EditScheduleActivity.this);
        pd.setTitle("Please wait,Data is being submitted.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<ResModel> call = api.editSchedule(tv_team1.getText().toString(),tv_team2.getText().toString(),mdate,getIntent().getStringExtra("id"));
        call.enqueue(new Callback<ResModel>() {
            @Override
            public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    ResModel rm=response.body();
                    if(rm.getStatus().equals("true")){
                        Toast.makeText(EditScheduleActivity.this,rm.getMessage(),Toast.LENGTH_SHORT).show();

                        finish();
                    }else{
                        Toast.makeText(EditScheduleActivity.this,rm.getMessage(),Toast.LENGTH_SHORT).show();
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