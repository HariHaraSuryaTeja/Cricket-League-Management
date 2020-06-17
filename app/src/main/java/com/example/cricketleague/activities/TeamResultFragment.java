package com.example.cricketleague.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cricketleague.R;
import com.example.cricketleague.adapters.TeamResultAdapter;
import com.example.cricketleague.adapters.TeamsAdapter;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.TeamModel;
import com.example.cricketleague.models.TeamResultModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamResultFragment extends Fragment {
    ListView list_view;
    ProgressDialog progressDialog;
    List<TeamModel> al;
    View view;
    public static TeamResultFragment getTeamsFragment() {
        TeamResultFragment fragment = new TeamResultFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_team_results, container, false);
        list_view=(ListView)view.findViewById(R.id.list_view);
        loadAllTeams();
        return view;
    }
    ProgressDialog pd;
    private void loadAllTeams(){
        pd = new ProgressDialog(getActivity());
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
                    list_view.setAdapter(new TeamResultAdapter(teams,getActivity()));
                }
            }
            @Override
            public void onFailure(Call<List<TeamResultModel>> call, Throwable t) {
                pd.dismiss();
            }
        });
    }
}