package com.example.cricketleague.api;

import com.example.cricketleague.models.ManagerModel;
import com.example.cricketleague.models.PlayerModel;
import com.example.cricketleague.models.ResModel;
import com.example.cricketleague.models.ScheduleModel;
import com.example.cricketleague.models.TeamModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("CPL/deleteTeam.php")
    Call<ResModel> deleteTeam(@Query("id") String id);

    @GET("CPL/deleteManager.php")
    Call<ResModel> deleteManager(@Query("id") String id);

    @GET("CPL/addTeam.php")
    Call<ResModel> addTeam(@Query("team_name") String team_name);

    @GET("CPL/addManager.php")
    Call<ResModel> addManager(@Query("name") String name,@Query("phno") String phno,@Query("email") String email,@Query("team") String team);

    @GET("CPL/addPlayer.php")
    Call<ResModel> addPlayer(@Query("name") String name,@Query("phno") String phno,@Query("email") String email,@Query("team") String team);


    @GET("CPL/editManager.php")
    Call<ResModel> editManager(@Query("id") String id,@Query("name") String name,@Query("phno") String phno,@Query("email") String email,@Query("team") String team);


    @GET("CPL/editTeam.php")
    Call<ResModel> editTeam(@Query("team_name") String team_name, @Query("id") String id);

    @GET("CPL/getTeams.php")
    Call<List<TeamModel>> getAllTeams();

    @GET("CPL/getManagers.php")
    Call<List<ManagerModel>> getAllMangers();

    @GET("CPL/getPlayers.php")
    Call<List<PlayerModel>> getAllPlayers();

    @GET("CPL/getPlayersByTeam.php")
    Call<List<PlayerModel>> getPlayersByTeam(@Query("team") String team);

    @GET("CPL/editPlayer.php")
    Call<ResModel> editPlayer(@Query("id") String id,@Query("name") String name,@Query("phno") String phno,@Query("email") String email,@Query("team") String team);

    @GET("CPL/deletePlayer.php")
    Call<ResModel> deletePlayer(@Query("id") String id);

    @GET("CPL/addSchedule.php")
    Call<ResModel> addSchedule(@Query("team1") String team1,@Query("team2") String team2,@Query("schedule_date") String sdate);

    @GET("CPL/getManagerByTeam.php")
    Call<List<ManagerModel>> getManagerByTeam(@Query("team") String team1);

    @GET("CPL/getSchedule.php")
    Call<List<ScheduleModel>> getSchedule();

}
