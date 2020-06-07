package com.example.cricketleague.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cricketleague.R;
import com.example.cricketleague.adapters.ManagersAdapter;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.ManagerModel;
import com.example.cricketleague.models.TeamModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ManagerListActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<TeamModel> al;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managers_list);
        Button btnAddManger=(Button)findViewById(R.id.btnAddManger);
        list_view=(ListView)findViewById(R.id.list_view);

        btnAddManger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManagerListActivity.this,AddManagerActivity.class));
                finish();
            }
        });

        getSupportActionBar().setTitle("Add/Edit Managers");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadAllMangers();
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

    ProgressDialog pd;
    private void loadAllMangers(){
        pd = new ProgressDialog(ManagerListActivity.this);
        pd.setTitle("Please wait,Data is being loaded.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<List<ManagerModel>> call = api.getAllMangers();
        call.enqueue(new Callback<List<ManagerModel>>() {
            @Override
            public void onResponse(Call<List<ManagerModel>> call, Response<List<ManagerModel>> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    List<ManagerModel> managers=response.body();
                    //Toast.makeText(ManagerListActivity.this,""+managers.size(),Toast.LENGTH_SHORT).show();
                    list_view.setAdapter(new ManagersAdapter(managers,ManagerListActivity.this));
                }
            }
            @Override
            public void onFailure(Call<List<ManagerModel>> call, Throwable t) {
                pd.dismiss();
            }
        });
    }
}