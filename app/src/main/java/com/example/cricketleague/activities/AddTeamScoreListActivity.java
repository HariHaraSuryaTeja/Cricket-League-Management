package com.example.cricketleague.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cricketleague.R;
import com.example.cricketleague.adapters.TeamResultAdapter;
import com.example.cricketleague.adapters.TeamScoreListAdapter;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.TeamResultModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTeamScoreListActivity  extends AppCompatActivity {
    ListView list_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team_score_list);
        getSupportActionBar().setTitle("Schedule");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list_view=(ListView)findViewById(R.id.list_view);
        loadSchedule();
    }

    ProgressDialog pd;
    private void loadSchedule(){
        pd = new ProgressDialog(AddTeamScoreListActivity.this);
        pd.setTitle("Please wait,Data is being loaded.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<List<TeamResultModel>> call = api.getMatchResults();
        call.enqueue(new Callback<List<TeamResultModel>>() {
            @Override
            public void onResponse(Call<List<TeamResultModel>> call, Response<List<TeamResultModel>> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    List<TeamResultModel> teams=response.body();
                    list_view.setAdapter(new TeamScoreListAdapter(teams,AddTeamScoreListActivity.this));
                }
            }
            @Override
            public void onFailure(Call<List<TeamResultModel>> call, Throwable t) {
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