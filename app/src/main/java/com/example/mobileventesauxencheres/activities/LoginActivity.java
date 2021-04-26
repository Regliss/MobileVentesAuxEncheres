package com.example.mobileventesauxencheres.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mobileventesauxencheres.R;

public class LoginActivity extends AppCompatActivity {

    private TextView loginTx, signupTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signupTx = findViewById(R.id.signUp);

        signupTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(homeIntent);
            }
        });
    }
}