package com.dragon_vs_samurai.game_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class GameOverActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    AdView adView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713"); // App id here

        adView1 = (AdView) findViewById(R.id.adView_1);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView1.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712"); // Ad unit id here

        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());

        mInterstitialAd.setAdListener(new AdListener()
        {
            @Override
            public void onAdClosed()
            {
                startActivity(new Intent(GameOverActivity.this,MainActivity.class));
                mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());

            }
        });


    }

    public void playAgain(View view) {
        if(mInterstitialAd.isLoaded())
        {
            mInterstitialAd.show();
        }else
            {
                startActivity(new Intent(GameOverActivity.this,MainActivity.class));

            }
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();

    }

}
