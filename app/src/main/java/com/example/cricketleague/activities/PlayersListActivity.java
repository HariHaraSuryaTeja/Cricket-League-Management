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
import com.example.cricketleague.adapters.PlayersAdapter;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.ManagerModel;
import com.example.cricketleague.models.PlayerModel;
import com.example.cricketleague.models.TeamModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PlayersListActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<PlayerModel> al;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_list);
        Button btnAddPlayer=(Button)findViewById(R.id.btnAddPlayer);
        list_view=(ListView)findViewById(R.id.list_view);
        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayersListActivity.this,AddPlayerActivity.class));
                finish();
            }
        });

        getSupportActionBar().setTitle("Add/Edit Player");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadAllPlayers();
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
    private void loadAllPlayers(){
        pd = new ProgressDialog(PlayersListActivity.this);
        pd.setTitle("Please wait,Data is being loaded.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<List<PlayerModel>> call = api.getAllPlayers();
        call.enqueue(new Callback<List<PlayerModel>>() {
            @Override
            public void onResponse(Call<List<PlayerModel>> call, Response<List<PlayerModel>> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    List<PlayerModel> players=response.body();
                    //Toast.makeText(ManagerListActivity.this,""+managers.size(),Toast.LENGTH_SHORT).show();
                    list_view.setAdapter(new PlayersAdapter(players,PlayersListActivity.this));
                }
            }
            @Override
            public void onFailure(Call<List<PlayerModel>> call, Throwable t) {
                pd.dismiss();
            }
        });
    }
}