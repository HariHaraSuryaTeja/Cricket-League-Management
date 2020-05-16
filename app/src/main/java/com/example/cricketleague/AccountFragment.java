package com.example.cricketleague;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AccountFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view =inflater.inflate(R.layout.fragments_account,container,false);
  Button team=(Button)view.findViewById(R.id.team);
  team.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

          Intent i=new Intent(getActivity(),teamLogin.class);
          startActivity(i);
      }
  });
      Button league=(Button)view.findViewById(R.id.league);
      league.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i=new Intent(getActivity(),leagueLogin.class);
              startActivity(i);
          }
      });

        return view;
    }
}