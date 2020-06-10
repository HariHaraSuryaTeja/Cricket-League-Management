package com.example.cricketleague.activities;

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
        ListView list_view=(ListView)view.findViewById(R.id.list_view);
        btnAddSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),AddScheduleActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }
}
