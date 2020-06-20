package com.example.cricketleague.activities;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cricketleague.R;
import com.example.cricketleague.adapters.ScoreAdapter;
import com.example.cricketleague.adapters.TeamResultAdapter;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.PlayerScoreModel;
import com.example.cricketleague.models.TeamResultModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Team1PlayerScoreFragment extends Fragment {
    View view;
    Button firstButton;
    ListView list_view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_team1_players_score, container, false);

         list_view=(ListView)view.findViewById(R.id.list_view);

        String team=((TeamPlayersScoreDetailsActivity) getActivity()).t1;
        String id=((TeamPlayersScoreDetailsActivity) getActivity()).id;

        getPlayersScore(team,id);
        return view;
    }

    ProgressDialog pd;
    private void getPlayersScore(String team,String id){
        pd = new ProgressDialog(getActivity());
        pd.setTitle("Please wait,Data is being loaded.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<List<PlayerScoreModel>> call = api.getPlayersScore(id,team);
        call.enqueue(new Callback<List<PlayerScoreModel>>() {
            @Override
            public void onResponse(Call<List<PlayerScoreModel>> call, Response<List<PlayerScoreModel>> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    List<PlayerScoreModel> teams=response.body();
                    if(teams!=null) {
                        list_view.setAdapter(new ScoreAdapter(teams, getActivity()));
                    }
                }
            }
            @Override
            public void onFailure(Call<List<PlayerScoreModel>> call, Throwable t) {
                pd.dismiss();
            }
        });
    }
}