package com.example.cricketleague.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.cricketleague.R;
import com.example.cricketleague.activities.TeamDetailsActivity;
import com.example.cricketleague.models.TeamModel;

import java.util.List;

public class HomeScreenAdapter extends BaseAdapter {

    List<TeamModel> ar;
    Context cnt;
    public HomeScreenAdapter(List<TeamModel> ar, Context cnt)
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
        View obj2=obj1.inflate(R.layout.list_adapter_home_screen,null);


        TextView tv_team_name=(TextView)obj2.findViewById(R.id.tv_team_name);
        tv_team_name.setText(ar.get(pos).getTeam_name());

        CardView cv_team_name=(CardView)obj2.findViewById(R.id.cv_team_name);
        cv_team_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cnt, TeamDetailsActivity.class);
                intent.putExtra("team_name",ar.get(pos).getTeam_name());
                cnt.startActivity(intent);
            }
        });

        return obj2;
    }



}