package com.example.cricketleague.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cricketleague.R;
import com.example.cricketleague.adapters.Players1Adapter;
import com.example.cricketleague.adapters.PlayersAdapter;
import com.example.cricketleague.adapters.TeamsAdapter;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.ManagerModel;
import com.example.cricketleague.models.PlayerModel;
import com.example.cricketleague.models.TeamModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamDetailsActivity extends AppCompatActivity {
    TextView tv_team_name,tv_manager;
    ListView list_view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        getSupportActionBar().setTitle("Team Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_team_name=(TextView)findViewById(R.id.tv_team_name);
        tv_manager=(TextView)findViewById(R.id.tv_manager);
        list_view=(ListView)findViewById(R.id.list_view);
        tv_team_name.setText(getIntent().getStringExtra("team_name"));
        getManager();
        loadAllPlayers();
    }

    private void getManager(){
        tv_manager.setText("Loading from server");
        ApiService api = RetroClient.getApiService();
        Call<List<ManagerModel>> call = api.getManagerByTeam(getIntent().getStringExtra("team_name"));
        call.enqueue(new Callback<List<ManagerModel>>() {
            @Override
            public void onResponse(Call<List<ManagerModel>> call, Response<List<ManagerModel>> response) {

                if (response.isSuccessful()) {
                    List<ManagerModel> teams=response.body();
                    if(teams!=null){
                        if(teams.size()>0){
                            tv_manager.setText(teams.get(0).getName());
                        }else{

                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<List<ManagerModel>> call, Throwable t) {

            }
        });
    }

    ProgressDialog pd;
    int no_of_players=0;
    private void loadAllPlayers(){
        pd = new ProgressDialog(TeamDetailsActivity.this);
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
                    //Toast.makeText(ManagerListActivity.this,""+managers.size(),Toast.LENGTH_SHORT).show();
                    if(players!=null) {
                        if (players.size() > 0) {
                            no_of_players = players.size();
                            list_view.setAdapter(new Players1Adapter(players, TeamDetailsActivity.this));
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
