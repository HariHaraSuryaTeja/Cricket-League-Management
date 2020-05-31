package com.example.cricketleague;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class emailOTP extends AppCompatActivity {
    CardView EmailOTP;
    Button buttonOTPmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_o_t_p);

        EmailOTP = findViewById(R.id.emailOTPCv);
        buttonOTPmail = findViewById(R.id.emailOTPBtn);

        buttonOTPmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eotp = new Intent(emailOTP.this,OTPSent.class);
                startActivity(eotp);
            }
        });
    }
}
