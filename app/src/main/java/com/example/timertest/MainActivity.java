package com.example.timertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView textViewTimer;
    private int seconds = 0;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTimer = findViewById(R.id.textViewTimer);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
        }
        runTimer();
        Log.d("MyLog", "create" + " " + isRunning + " " + seconds);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("isRunning", isRunning);
    }

    public void onClickPauseStart(View view) {
        isRunning = true;
        Log.d("MyLog", "start" + " " + isRunning + " " + seconds);
    }

    public void onClickPauseTimer(View view) {
        isRunning = false;
        Log.d("MyLog", "pause" + " " + isRunning + " " + seconds);
    }

    public void onClickPauseStop(View view) {
        isRunning = false;
        seconds = 0;
        Log.d("MyLog", "stop" + " " + isRunning + " " + seconds);
    }


    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, secs);
                textViewTimer.setText(time);
                Log.d("MyLog", " " + hours + " " + minutes + " " + secs);
                if (isRunning) {
                    seconds += 30;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}