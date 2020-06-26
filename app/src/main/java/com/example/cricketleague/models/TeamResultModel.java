package com.example.cricketleague.models;

import com.google.gson.annotations.SerializedName;

public class TeamResultModel {
    @SerializedName("id")
    private String id;

    @SerializedName("team1")
    private String team1;

    @SerializedName("team2")
    private String team2;

    @SerializedName("team1_score")
    private String team1_score;

    @SerializedName("team2_score")
    private String team2_score;

    @SerializedName("result")
    private String result;

    @SerializedName("sid")
    private String sid;

    @SerializedName("schedule_date")
    private String schedule_date;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeam1_score() {
        return team1_score;
    }

    public void setTeam1_score(String team1_score) {
        this.team1_score = team1_score;
    }

    public String getTeam2_score() {
        return team2_score;
    }

    public void setTeam2_score(String team2_score) {
        this.team2_score = team2_score;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSchedule_date() {
        return schedule_date;
    }

    public void setSchedule_date(String schedule_date) {
        this.schedule_date = schedule_date;
    }
}
