package com.example.cricketleague.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import com.example.cricketleague.R;
import com.example.cricketleague.activities.EditTeamActivity;
import com.example.cricketleague.activities.TeamDetails1Activity;
import com.example.cricketleague.activities.TeamDetailsActivity;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.ResModel;
import com.example.cricketleague.models.TeamModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Teams1Adapter extends BaseAdapter {

    List<TeamModel> ar;
    Context cnt;
    SharedPreferences pref;
    public Teams1Adapter(List<TeamModel> ar, Context cnt)
    {
        this.ar=ar;
        this.cnt=cnt;
        pref = cnt.getSharedPreferences("cpl", 0);
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
        View obj2=obj1.inflate(R.layout.row_teams,null);


        TextView tv_team_name=(TextView)obj2.findViewById(R.id.tv_team_name);
        tv_team_name.setText(ar.get(pos).getTeam_name());

        TextView tv_delete=(TextView)obj2.findViewById(R.id.tv_delete);

        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelete(ar.get(pos).getId(),pos);

            }
        });

        TextView tv_edit=(TextView)obj2.findViewById(R.id.tv_edit);

        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(cnt, EditTeamActivity.class);
                intent.putExtra("team_name",ar.get(pos).getTeam_name());
                intent.putExtra("id",ar.get(pos).getId());
                cnt.startActivity(intent);
            }
        });

        if(!pref.getString("team_access","-").equals("all")){
            tv_edit.setVisibility(View.GONE);
            tv_delete.setVisibility(View.GONE);
        }else{
            tv_edit.setVisibility(View.VISIBLE);
            tv_delete.setVisibility(View.VISIBLE);
        }

        CardView cv_team_name=(CardView)obj2.findViewById(R.id.cv_team_name);
        cv_team_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cnt, TeamDetails1Activity.class);
                intent.putExtra("team_name",ar.get(pos).getTeam_name());
                cnt.startActivity(intent);
            }
        });

        return obj2;
    }

    ProgressDialog pd;
    private void deleteTeam(String id,final int pos1){
        pd = new ProgressDialog(cnt);
        pd.setTitle("Please wait,Data is being loaded.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<ResModel> call = api.deleteTeam(id);
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

    AlertDialog.Builder builder;
    private void confirmDelete(final String id1,final int pos1){
        builder = new AlertDialog.Builder(cnt);
        builder.setMessage("Do you want to delete Team?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteTeam(id1,pos1);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle("CPL");
        alert.show();
    }

}