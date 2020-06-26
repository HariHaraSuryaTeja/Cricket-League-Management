package com.example.cricketleague.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.cricketleague.R;
import com.example.cricketleague.activities.EditPlayerActivity;
import com.example.cricketleague.activities.TeamDetailsActivity;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.PlayerModel;
import com.example.cricketleague.models.ResModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PlayersScoreAdapter extends BaseAdapter {
    List<PlayerModel> ar;
    Context cnt;
    String sid,team_name;
    public PlayersScoreAdapter(List<PlayerModel> ar, Context cnt,String sid,String team_name)
    {
        this.ar=ar;
        this.cnt=cnt;
        this.sid=sid;
        this.team_name=team_name;
    }
    @Override
    public int getCount() {
        return ar.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int pos, View view, ViewGroup viewGroup)
    {
        LayoutInflater obj1 = (LayoutInflater)cnt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View obj2=obj1.inflate(R.layout.row_player_score,null);

        TextView tv_player=(TextView)obj2.findViewById(R.id.tv_player);
        tv_player.setText(ar.get(pos).getName());

        final EditText etScore=(EditText)obj2.findViewById(R.id.etScore);
        Button btnAdd=(Button)obj2.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addScore(sid,ar.get(pos).getName(),etScore.getText().toString(),team_name);
            }
        });

        return obj2;
    }

    ProgressDialog pd;
    private void addScore(String sid1,String player_name,String score,String team_name){
        pd = new ProgressDialog(cnt);
        pd.setTitle("Please wait,Data is being loaded.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<ResModel> call = api.addPlayerScore(sid1,player_name,score,team_name);
        call.enqueue(new Callback<ResModel>() {
            @Override
            public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(cnt,"Score is updated successfully.",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResModel> call, Throwable t) {
                pd.dismiss();
            }
        });
    }



}