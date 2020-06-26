package com.example.cricketleague.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerScoreModel {
    @SerializedName("player_name")
    @Expose
    private String player_name;

    @SerializedName("score")
    @Expose
    private String score;

    public PlayerScoreModel(String player_name,String score){
        this.player_name=player_name;
        this.score=score;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
