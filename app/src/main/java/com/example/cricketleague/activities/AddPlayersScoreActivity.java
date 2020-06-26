package com.example.cricketleague.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cricketleague.R;
import com.example.cricketleague.adapters.Players1Adapter;
import com.example.cricketleague.adapters.PlayersScoreAdapter;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.PlayerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddPlayersScoreActivity  extends AppCompatActivity {
    ListView list_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players_score);
        getSupportActionBar().setTitle("Add Players Score");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list_view=(ListView)findViewById(R.id.list_view);
        loadAllPlayers();
    }
    ProgressDialog pd;
    private void loadAllPlayers(){
        pd = new ProgressDialog(AddPlayersScoreActivity.this);
        pd.setTitle("Please wait,Data is being loaded.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<List<PlayerModel>> call = api.getPlayersByTeam(getIntent().getStringExtra("team_name"));
        call.enqueue(new Callback<List<PlayerModel>>() {
            @Override
            public void onResponse(Call<List<PlayerModel>> call, Response<List<PlayerModel>> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    List<PlayerModel> players=response.body();
                    if(players!=null) {
                        if (players.size() > 0) {
                            list_view.setAdapter(new PlayersScoreAdapter(players, AddPlayersScoreActivity.this,getIntent().getStringExtra("sid"),getIntent().getStringExtra("team_name")));
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<List<PlayerModel>> call, Throwable t) {
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