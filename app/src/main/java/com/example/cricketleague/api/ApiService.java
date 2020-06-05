package com.example.cricketleague.api;

import com.example.cricketleague.models.ResModel;
import com.example.cricketleague.models.TeamModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("CPL/addTeam.php")
    Call<ResModel> addTeam(@Query("team_name") String uname);

    @GET("CPL/getTeams.php")
    Call<List<TeamModel>> getAllTeams();
}
