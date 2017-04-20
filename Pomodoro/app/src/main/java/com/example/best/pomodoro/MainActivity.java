package com.example.best.pomodoro;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class MainActivity extends AppCompatActivity {
    CircularProgressBar circularProgressBar;
    TextView timeTextView;
    boolean started = false;
    FloatingActionButton fab;
    Presenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (!started){
                    fab.setImageResource(R.drawable.ic_pause);
                    started = true;
                    mainPresenter.startTimer();
                }else {
                    started = false;
                    fab.setImageResource(R.drawable.ic_play);
                    mainPresenter.stopTimer();
                    timeTextView.setText("25:00");
                    circularProgressBar.setProgress(100);
                }
            }
        });

    }

    public void initWidgets() {
        circularProgressBar = (CircularProgressBar) findViewById(R.id.view);
        timeTextView = (TextView) findViewById(R.id.timeTextView);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        mainPresenter = new Presenter();
        mainPresenter.onTakeView(this);
        circularProgressBar.setProgress(100);
    }

    public void setData(String time, int progress) {
        timeTextView.setText(time);
        circularProgressBar.setProgress(progress);
    }
}
