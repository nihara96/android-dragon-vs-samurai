package com.dragon_vs_samurai.game_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainMenuActivity extends AppCompatActivity {

    private Button playButton;
    private Button exitButton;
    

    AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713"); // App id here

        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        playButton = (Button) findViewById(R.id.playButton);
        exitButton  =(Button) findViewById(R.id.exitButton);


        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent mainIntent = new Intent(MainMenuActivity.this, MainActivity.class);
                startActivity(mainIntent);

            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder altdial = new AlertDialog.Builder(MainMenuActivity.this);
                altdial.setMessage("Do you really want to exit ?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                        System.exit(0);

                    }
                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                

                AlertDialog alert = altdial.create();
                alert.setTitle("Exit !");
                alert.show();

            }
        });



    }



}
