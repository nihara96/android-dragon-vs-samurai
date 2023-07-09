package com.dragon_vs_samurai.game_1;

import androidx.appcompat.app.AppCompatActivity;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;


import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private GameView gameView;

    private Handler handler = new Handler();
    private final static long interval = 30;
    MediaPlayer background_music;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        gameView = new GameView(this);

        background_music = MediaPlayer.create(MainActivity.this,R.raw.music);
        background_music.start();
        background_music.setLooping(true);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(gameView);




        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                    }
                });
            }
        },0,interval);
    }

    protected void onPause() {

        super.onPause();
        background_music.release();
        finish();
    }


}
