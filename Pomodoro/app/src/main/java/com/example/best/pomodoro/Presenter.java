package com.example.best.pomodoro;

import android.os.CountDownTimer;

/**
 * Created by Best on 4/20/2017.
 */

public class Presenter {
    private MainActivity view;
    CountDownTimer countDownTimer;
    int duration = 25 * 60000;
    String elapsed;
    String time;
    int progress;

    public Presenter(){
        countDownTimer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time = convertSeconds(millisUntilFinished/1000);
                elapsed = convertSeconds((duration - millisUntilFinished)/1000);
                progress = (int)(millisUntilFinished/(double)duration * 100);
                publish();
            }

            @Override
            public void onFinish() {

            }
        };
    }

    public void startTimer(){
        countDownTimer.cancel();
        countDownTimer.start();
    }

    public void stopTimer(){
        countDownTimer.cancel();
    }

    public static String convertSeconds(long seconds){
        long s = seconds % 60;
        long m = (seconds / 60) % 60;
        return String.format("%02d:%02d", m, s);
    }

    public void onTakeView(MainActivity view) {
        this.view = view;
    }

    public void publish() {
        view.setData(time, progress);
    }
}
