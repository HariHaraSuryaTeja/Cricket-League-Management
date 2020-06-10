package com.example.cricketleague.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cricketleague.R;
import com.example.cricketleague.adapters.TeamsAdapter;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.TeamModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamsFragment extends Fragment {
    ListView list_view;
    ProgressDialog progressDialog;
    List<TeamModel> al;
    View view;
    public static TeamsFragment getTeamsFragment() {
        TeamsFragment fragment = new TeamsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_teams, container, false);
        list_view=(ListView)view.findViewById(R.id.list_view);
        Button btnAddTeam=(Button) view.findViewById(R.id.btnAddTeam);
        btnAddTeam.setVisibility(View.GONE);
        btnAddTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),AddTeamActivity.class));
                getActivity().finish();
            }
        });
        loadAllTeams();
        return view;
    }
    ProgressDialog pd;
    private void loadAllTeams(){
        pd = new ProgressDialog(getActivity());
        pd.setTitle("Please wait,Data is being loaded.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<List<TeamModel>> call = api.getAllTeams();
        call.enqueue(new Callback<List<TeamModel>>() {
            @Override
            public void onResponse(Call<List<TeamModel>> call, Response<List<TeamModel>> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    List<TeamModel> teams=response.body();
                    list_view.setAdapter(new TeamsAdapter(teams,getActivity()));
                }
            }
            @Override
            public void onFailure(Call<List<TeamModel>> call, Throwable t) {
                pd.dismiss();
            }
        });
    }
}
