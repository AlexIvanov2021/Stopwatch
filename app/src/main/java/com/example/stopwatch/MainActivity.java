package com.example.stopwatch;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mSeconds = 0;
    private boolean mIsRunning;
    private boolean mIsWasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState !=null){
          mSeconds=savedInstanceState.getInt("second");
          mIsRunning = savedInstanceState.getBoolean("isRunning");
          mIsWasRunning = savedInstanceState.getBoolean("isWasRunning");




        }
       runTimer();

    }
    public void onStartClick(View view){
        mIsRunning = true;

    }
    public void onStopClick(View view){
        mIsRunning = false;

    }
    public void onResetClick(View view){
        mIsRunning = false;
        mSeconds = 0;

    }
    public void  runTimer(){
      final TextView timeTextView = findViewById(R.id.textView);
      final  Handler handler = new Handler();
      handler.post(new Runnable() {
          @Override
          public void run() {
              int hours = mSeconds/3600;
              int minutes =( mSeconds % 3600)/60;
              int secs = mSeconds/60;
             String time = String.format("%d:%02d:%02d",hours,minutes,secs);
             timeTextView.setText(time);
             if(mIsRunning){
                 mSeconds++;

          }
             handler.postDelayed(this,1000);
      }


        });
    }

    @Override
    protected void onSaveInstanceState( Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("second",mSeconds);
        outState.putBoolean("isRunning",mIsRunning);
        outState.putBoolean("isWasRunning",mIsRunning);

    }
    protected void onPause(){
        super.onPause();
        mIsWasRunning = mIsRunning;
        mIsRunning = false;

    }
    protected void onResume(){
        super.onResume();
        if(mIsWasRunning){
            mIsWasRunning = true;
        }
    }
}
