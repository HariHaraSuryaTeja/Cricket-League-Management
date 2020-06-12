package com.example.cricketleague.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cricketleague.R;
import com.example.cricketleague.adapters.Players1Adapter;
import com.example.cricketleague.adapters.PlayersAdapter;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.ManagerModel;
import com.example.cricketleague.models.PlayerModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamDetails1Activity extends AppCompatActivity {
    TextView tv_team_name,tv_manager,tv_manager_edit;
    ListView list_view;
    SharedPreferences pref;
    Button btnAddPlayer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);
        pref = getSharedPreferences("cpl", 0);
        getSupportActionBar().setTitle("Team Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_team_name=(TextView)findViewById(R.id.tv_team_name);
        btnAddPlayer=(Button)findViewById(R.id.btnAddPlayer);
        btnAddPlayer.setVisibility(View.VISIBLE);
        tv_manager=(TextView)findViewById(R.id.tv_manager);
        tv_manager_edit=(TextView)findViewById(R.id.tv_manager_edit);
        tv_manager_edit.setVisibility(View.VISIBLE);
        list_view=(ListView)findViewById(R.id.list_view);
        tv_team_name.setText(getIntent().getStringExtra("team_name"));
        getManager();
        loadAllPlayers();

        if(!pref.getString("team_access","").equals("all")){
            if(!pref.getString("team_access","").equals(getIntent().getStringExtra("team_name"))){
                tv_manager_edit.setVisibility(View.GONE);
                btnAddPlayer.setVisibility(View.GONE);
            }

        }

        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeamDetails1Activity.this, AddPlayer1Activity.class);
                intent.putExtra("team", mm.getTeam());
                startActivity(intent);
                finish();
            }
        });

        tv_manager_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mm!=null) {
                    Intent intent = new Intent(TeamDetails1Activity.this, EditManagerActivity.class);
                    intent.putExtra("id", mm.getId());
                    intent.putExtra("name", mm.getName());
                    intent.putExtra("phno", mm.getPhno());
                    intent.putExtra("email", mm.getEmail());
                    intent.putExtra("team", mm.getTeam());
                    startActivity(intent);
                }else{
                    Toast.makeText(TeamDetails1Activity.this,"No manager is assigned.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    ManagerModel mm;
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
                            mm=teams.get(0);
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
    private void loadAllPlayers(){
        pd = new ProgressDialog(TeamDetails1Activity.this);
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
                    list_view.setAdapter(new PlayersAdapter(getIntent().getStringExtra("team_name"),players,TeamDetails1Activity.this));
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
