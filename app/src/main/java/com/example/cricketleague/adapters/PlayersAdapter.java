package com.example.cricketleague.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.cricketleague.R;
//import com.example.cricketleague.activities.EditPlayerActivity;
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

public class PlayersAdapter extends BaseAdapter {
    List<PlayerModel> ar;
    Context cnt;
    public PlayersAdapter(List<PlayerModel> ar, Context cnt)
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
        View obj2=obj1.inflate(R.layout.row_player,null);

        TextView tv_player=(TextView)obj2.findViewById(R.id.tv_player);
        tv_player.setText(ar.get(pos).getName());

        TextView tv_edit=(TextView)obj2.findViewById(R.id.tv_edit);
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(cnt, EditPlayerActivity.class);
                //intent.putExtra("team_name",ar.get(pos).getTeam_name());
                intent.putExtra("id",ar.get(pos).getId());
                intent.putExtra("name",ar.get(pos).getName());
                intent.putExtra("phno",ar.get(pos).getPhno());
                intent.putExtra("email",ar.get(pos).getEmail());
                intent.putExtra("team",ar.get(pos).getTeam());
                cnt.startActivity(intent);
            }
        });

        CardView cv_team_name=(CardView)obj2.findViewById(R.id.cv_team_name);
        cv_team_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cnt, TeamDetailsActivity.class);
                //intent.putExtra("team_name",ar.get(pos).getTeam_name());
                cnt.startActivity(intent);
            }
        });

        return obj2;
    }
}