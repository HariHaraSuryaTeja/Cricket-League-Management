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
import com.example.cricketleague.activities.EditTeamActivity;
import com.example.cricketleague.activities.TeamDetailsActivity;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.ResModel;
import com.example.cricketleague.models.TeamModel;
import com.example.cricketleague.models.TeamResultModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class TeamResultAdapter extends BaseAdapter {

    List<TeamResultModel> ar;
    Context cnt;
    public TeamResultAdapter(List<TeamResultModel> ar, Context cnt)
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
        View obj2=obj1.inflate(R.layout.row_team_result,null);


        TextView team1=(TextView)obj2.findViewById(R.id.team1);
        team1.setText(ar.get(pos).getTeam1());

        TextView team1_score=(TextView)obj2.findViewById(R.id.team1_score);
        team1_score.setText(ar.get(pos).getTeam1_score());

        TextView team2=(TextView)obj2.findViewById(R.id.team2);
        team2.setText(ar.get(pos).getTeam2());

        TextView team2_score=(TextView)obj2.findViewById(R.id.team2_score);
        team2_score.setText(ar.get(pos).getTeam2_score());

        TextView tv_result=(TextView)obj2.findViewById(R.id.tv_result);
        tv_result.setText(ar.get(pos).getResult());

        CardView cv_team_name=(CardView)obj2.findViewById(R.id.cv_team_name);

        return obj2;
    }
}