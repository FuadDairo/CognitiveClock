package com.example.reactionspeed;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    boolean flag = false;
    long startTime = 0;
    long finishTime;
    long result;
    long timeLimiter;
    Random rand = new Random();
    long finishTime2;

    TextView txt;
    TextView milliTxt;
    TextView gradeTxt;
    RelativeLayout reactionRL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.idTxt);
        milliTxt = findViewById(R.id.milliText);
        reactionRL = findViewById(R.id.reactionRL);
        gradeTxt = findViewById(R.id.gradeTxt);
    }
    @SuppressLint("SetTextI18n")
    public void onClick(View view) {
        if (!flag) {
            startTime = System.currentTimeMillis();
            reactionRL.setBackgroundColor(Color.parseColor("#FF6200EE")); //purple
            milliTxt.setVisibility(View.GONE);
            gradeTxt.setVisibility(View.GONE);
            timeLimiter = rand.nextInt(6);
            timeLimiter += 2;
            txt.setText("Tap when "+ timeLimiter +" seconds have passed");
            txt.setTextColor(Color.parseColor("#FFFFFF"));
            flag = true;
        } else {
            finishTime2 = System.currentTimeMillis();
            if (finishTime2 - startTime < timeLimiter * 1000) {
                Snackbar.make(reactionRL, "Too early by "+ ((timeLimiter*1000) - (finishTime2 - startTime)) +"ms!", Snackbar.LENGTH_LONG).show();
                reactionRL.setBackgroundColor(Color.parseColor("#FFFFFF")); //white
                txt.setText("Tap screen when ready");
                flag = false;
                milliTxt.setVisibility(View.GONE);
                gradeTxt.setVisibility(View.GONE);
                txt.setTextColor(Color.parseColor("#FF3700B3"));
            } else {
                finishTime = System.currentTimeMillis();
                result = finishTime - startTime;
                milliTxt.setText("Score: "+ (result - (timeLimiter * 1000)) +"ms");
                gradeTxt.setVisibility(View.VISIBLE);
                milliTxt.setVisibility(View.VISIBLE);
                if (result - (timeLimiter * 1000) <= 100) {
                    gradeTxt.setText("ABNORMAL!!!");
                    gradeTxt.setTextColor(Color.parseColor("#03dffc")); //light blue
                } else if (result - (timeLimiter * 1000) <= 200) {
                    gradeTxt.setText("Excellent!");
                    gradeTxt.setTextColor(Color.parseColor("#00ff22")); //green
                } else if (result - (timeLimiter * 1000) <= 400) {
                    gradeTxt.setText("Average");
                    gradeTxt.setTextColor(Color.parseColor("#ffc400")); //amber
                } else {
                    gradeTxt.setText("Below Average");
                    gradeTxt.setTextColor(Color.parseColor("#ff0000")); //red
                }
                txt.setText("Tap screen when ready");
                flag = false;
                txt.setTextColor(Color.parseColor("#FF3700B3")); //purple
                reactionRL.setBackgroundColor(Color.parseColor("#FFFFFF")); //white
            }
        }
    }
}
