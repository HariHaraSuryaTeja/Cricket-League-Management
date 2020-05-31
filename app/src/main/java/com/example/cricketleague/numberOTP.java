package com.example.cricketleague;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class numberOTP extends AppCompatActivity {
    CardView NumberOTP;
    Button buttonOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_o_t_p);

        NumberOTP = findViewById(R.id.numberOTPCv);
        buttonOTP = findViewById(R.id.numberOTPBtn);

        buttonOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent notp = new Intent(numberOTP.this,OTPSent.class);
                startActivity(notp);
            }
        });

    }
}
