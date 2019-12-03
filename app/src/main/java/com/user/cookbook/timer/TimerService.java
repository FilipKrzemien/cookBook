package com.user.cookbook.timer;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.widget.Toast;

import com.user.cookbook.R;

import java.util.Timer;

public class TimerService extends Service {
    private static Timer timer = new Timer();
    private String timerValue;

    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    public void onCreate()
    {
        super.onCreate();
    }

    private void startService()
    {

        new CountDownTimer(Integer.parseInt(this.timerValue) * 1000 * 60, 1000) {

            public void onTick(long millisUntilFinished) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.time_remaining) + " " + millisUntilFinished / 1000 / 60 + "." + Math.round((millisUntilFinished / 1000) % 60),
                        Toast.LENGTH_SHORT).show();
            }

            public void onFinish() {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.countdown_finished), Toast.LENGTH_SHORT).show();
            }

        }.start();

    }

    public void onDestroy()
    {
        timer.cancel();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        timerValue = intent.getStringExtra("TIME");
        startService();
        return super.onStartCommand(intent, flags, startId);
    }
}
