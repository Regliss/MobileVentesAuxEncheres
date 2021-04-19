package com.example.mobileventesauxencheres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobileventesauxencheres.models.ApiProducts;
import com.example.mobileventesauxencheres.utils.Constant;
import com.example.mobileventesauxencheres.utils.FastDialog;
import com.example.mobileventesauxencheres.utils.Network;
import com.google.gson.Gson;

public class HomeActivity extends AppActivity {

    private ListView listViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listViewData = findViewById(R.id.listViewData);

        if (!Network.isNetworkAvailable(HomeActivity.this)) {
            FastDialog.showDialog(
                    HomeActivity.this,
                    FastDialog.SIMPLE_DIALOG,
                    "Vous devez être connecté"
            );
            return;
        }

        //Requete HTTP
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = Constant.URL;

        //Request a string response from the provided URL
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("volley", "onResponse" + response);

                        parseJSON(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("volley", "onResponse" + error);
            }

        });

        queue.add(stringRequest);
    }

    private void parseJSON(String response) {
        ApiProducts[] api = new Gson().fromJson(response, ApiProducts[].class);

        listViewData.setAdapter(
                new ProductsAdapter(
                        HomeActivity.this,
                        R.layout.item_product,api)
        );

    }
}