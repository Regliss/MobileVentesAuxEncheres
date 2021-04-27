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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed(); // fermeture de l'activity
                break;
            case R.id.ActionShowHome:

                Intent myIntentFavoris2 = new Intent(this, HomeActivity.class);
                startActivity(myIntentFavoris2);
                return true;

            case R.id.ActionShowProfil:

                //Intent myIntentMaps = new Intent(this, MapsActivity.class);
                //startActivity(myIntentMaps);
                //return true;
            case R.id.ActionShowProduits:

                //Intent myIntentFavoris = new Intent(this, FavorisActivity.class);
                //startActivity(myIntentFavoris);
                //return true;
            case R.id.ActionShowLogout:

                //Intent myIntentInformation = new Intent(this, InformationActivity.class);
                //startActivity(myIntentInformation);
                //return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //affichage du menu

        //chargement du menu
        getMenuInflater().inflate(R.menu.menu_default, menu);

        return super.onCreateOptionsMenu(menu);
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
