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
import com.example.cricketleague.activities.EditPlayerActivity;
import com.example.cricketleague.activities.TeamDetails1Activity;
import com.example.cricketleague.activities.TeamDetailsActivity;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.PlayerModel;
import com.example.cricketleague.models.ResModel;
import com.example.cricketleague.models.ScheduleModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ScheduleAdapter extends BaseAdapter {
    List<ScheduleModel> ar;
    Context cnt;
    public ScheduleAdapter(List<ScheduleModel> ar, Context cnt)
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
        View obj2=obj1.inflate(R.layout.row_schedule,null);

        TextView team1=(TextView)obj2.findViewById(R.id.team1);
        team1.setText(ar.get(pos).getTeam1());

        TextView team2=(TextView)obj2.findViewById(R.id.team2);
        team2.setText(ar.get(pos).getTeam2());

        TextView tv_sdate=(TextView)obj2.findViewById(R.id.tv_sdate);
        tv_sdate.setText(ar.get(pos).getSchedule_date());

        return obj2;
    }

    ProgressDialog pd;
    private void deletePlayer(String id,final int pos1){
        pd = new ProgressDialog(cnt);
        pd.setTitle("Please wait,Data is being loaded.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<ResModel> call = api.deletePlayer(id);
        call.enqueue(new Callback<ResModel>() {
            @Override
            public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(cnt,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    ar.remove(pos1);
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResModel> call, Throwable t) {
                pd.dismiss();
            }
        });
    }



}