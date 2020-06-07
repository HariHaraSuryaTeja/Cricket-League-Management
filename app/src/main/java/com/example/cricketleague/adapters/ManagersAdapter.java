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
import com.example.cricketleague.activities.EditManagerActivity;
import com.example.cricketleague.activities.EditTeamActivity;
import com.example.cricketleague.activities.TeamDetailsActivity;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.ManagerModel;
import com.example.cricketleague.models.ResModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ManagersAdapter extends BaseAdapter {

    List<ManagerModel> ar;
    Context cnt;
    public ManagersAdapter(List<ManagerModel> ar, Context cnt)
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
        View obj2=obj1.inflate(R.layout.row_managers,null);


        TextView tv_manager=(TextView)obj2.findViewById(R.id.tv_manager);
        tv_manager.setText(ar.get(pos).getName());

        TextView tv_delete=(TextView)obj2.findViewById(R.id.tv_delete);
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteManager(ar.get(pos).getId(),pos);

            }
        });

        TextView tv_edit=(TextView)obj2.findViewById(R.id.tv_edit);
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(cnt, EditManagerActivity.class);
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

    ProgressDialog pd;
    private void deleteManager(String id,final int pos1){
        pd = new ProgressDialog(cnt);
        pd.setTitle("Please wait,Data is being loaded.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<ResModel> call = api.deleteManager(id);
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