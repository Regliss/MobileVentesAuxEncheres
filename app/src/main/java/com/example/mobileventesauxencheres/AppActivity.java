package com.example.mobileventesauxencheres;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AppActivity extends AppCompatActivity {
    protected void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed(); //fermeture activity
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getSupportActionBar() != null) {
            if (!(this instanceof HomeActivity)) {
                //bouton retour ->
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }
}
