package com.example.cricketleague.activities;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cricketleague.R;
import com.example.cricketleague.adapters.ScoreAdapter;
import com.example.cricketleague.models.PlayerScoreModel;

import java.util.ArrayList;
import java.util.List;

public class Team1PlayerScoreFragment extends Fragment {
    View view;
    Button firstButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_team1_players_score, container, false);
        loadScore();
        ListView list_view=(ListView)view.findViewById(R.id.list_view);
        list_view.setAdapter(new ScoreAdapter(pscore,getActivity()));
        return view;
    }
    List<PlayerScoreModel> pscore;
    private void loadScore(){
        pscore=new ArrayList<>();
        pscore.add(new PlayerScoreModel("Players1","43(10)"));
        pscore.add(new PlayerScoreModel("Players2","22(23)"));
        pscore.add(new PlayerScoreModel("Players3","54(32)"));
    }
}