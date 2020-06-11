package com.example.cricketleague.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cricketleague.R;
import com.example.cricketleague.adapters.PlayersAdapter;
import com.example.cricketleague.adapters.ScheduleAdapter;
import com.example.cricketleague.api.ApiService;
import com.example.cricketleague.api.RetroClient;
import com.example.cricketleague.models.PlayerModel;
import com.example.cricketleague.models.ScheduleModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakeMatchesFragment  extends Fragment {
    View view;
    public static MakeMatchesFragment getMakeMatchesFragment() {
        MakeMatchesFragment fragment = new MakeMatchesFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_makematches, container, false);
        Button btnAddSchedule=(Button)view.findViewById(R.id.btnAddSchedule);
        list_view=(ListView)view.findViewById(R.id.list_view);
        btnAddSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),AddScheduleActivity.class));
            }
        });
        loadAllschedule();
        return view;
    }
    ProgressDialog pd;
    ListView list_view;
    private void loadAllschedule(){
        pd = new ProgressDialog(getActivity());
        pd.setTitle("Please wait,Data is being loaded.");
        pd.show();
        ApiService api = RetroClient.getApiService();
        Call<List<ScheduleModel>> call = api.getSchedule();
        call.enqueue(new Callback<List<ScheduleModel>>() {
            @Override
            public void onResponse(Call<List<ScheduleModel>> call, Response<List<ScheduleModel>> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    List<ScheduleModel> schedule=response.body();
                    //Toast.makeText(ManagerListActivity.this,""+managers.size(),Toast.LENGTH_SHORT).show();
                    list_view.setAdapter(new ScheduleAdapter(schedule,getActivity()));
                }
            }
            @Override
            public void onFailure(Call<List<ScheduleModel>> call, Throwable t) {
                pd.dismiss();
            }
        });
    }
}
