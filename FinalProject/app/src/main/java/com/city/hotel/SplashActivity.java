package com.city.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

//***********************************************
public class SplashActivity
        extends AppCompatActivity
//***********************************************
{

    //***********************************************
    @Override
    protected void onCreate(Bundle savedInstanceState)
    //***********************************************
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        gotoHomeActivity();

    }

    //***********************************************
    private void gotoHomeActivity()
    //***********************************************
    {
        Runnable r = () -> {
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            finish();
        };

        Handler h = new Handler();
        h.postDelayed(r, 1500);
    }
}
