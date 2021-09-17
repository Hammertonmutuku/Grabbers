package com.example.grabbers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    //Variables
    private static int SPLASH_SCREEN = 2000;
    Animation topAnim, bottomAnim;
    ImageView rider;
    TextView welcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        rider = findViewById(R.id.rider1);
        welcome = findViewById(R.id.grabbers1);

        rider.setAnimation(topAnim);
        welcome.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =  new Intent(SplashScreen.this,MainActivity.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String> (rider, "logo_image");
                pairs[1] = new Pair<View, String> (welcome, "logo_text");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this,pairs);
                    startActivity(intent,options.toBundle());
                    finish();
                }
            }
        },SPLASH_SCREEN);
    }
}