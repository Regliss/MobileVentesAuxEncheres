package com.example.mobileventesauxencheres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mobileventesauxencheres.activities.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //déclaration
    private Timer myTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                //TODO: lancement de HomeActivity
                Intent myIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(myIntent);
                finish();
            }
        }, 2000);

    }
}