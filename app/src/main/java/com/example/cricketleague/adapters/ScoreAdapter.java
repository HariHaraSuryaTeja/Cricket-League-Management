package com.example.cricketleague.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cricketleague.R;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.PlayerScoreModel;
import com.example.cricketleague.models.ResModel;
import com.example.cricketleague.models.ScheduleModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScoreAdapter extends BaseAdapter {
    List<PlayerScoreModel> ar;
    Context cnt;
    public ScoreAdapter(List<PlayerScoreModel> ar, Context cnt)
    {
        this.ar=ar;
        this.cnt=cnt;
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
        View obj2=obj1.inflate(R.layout.row_score,null);

        TextView player_name=(TextView)obj2.findViewById(R.id.player_name);
        player_name.setText(ar.get(pos).getPlayer_name());

        TextView score=(TextView)obj2.findViewById(R.id.score);
        score.setText(ar.get(pos).getScore());


        return obj2;
    }
}