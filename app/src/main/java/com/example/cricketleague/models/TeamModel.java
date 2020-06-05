package com.example.cricketleague.models;

import com.google.gson.annotations.SerializedName;

public class TeamModel {

    @SerializedName("id")
    private String id;

    @SerializedName("team_name")
    private String team_name;

    public TeamModel(String team_name) {
        this.setTeam_name(team_name);

    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
