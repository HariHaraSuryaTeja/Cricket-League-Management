package com.example.cricketleague;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Forgotpassword extends AppCompatActivity {
    CardView numberCv,emailCv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        numberCv = findViewById(R.id.numberCardView);
        emailCv = findViewById(R.id.emailcardView);

        numberCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(Forgotpassword.this,numberOTP.class);
                startActivity(n);
            }
        });

        emailCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e = new Intent(Forgotpassword.this,emailOTP.class);
                startActivity(e);
            }
        });
    }
}
