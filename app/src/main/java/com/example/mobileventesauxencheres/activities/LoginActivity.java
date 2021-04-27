package com.example.mobileventesauxencheres.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobileventesauxencheres.R;
import com.example.mobileventesauxencheres.models.ApiLogin;
import com.example.mobileventesauxencheres.utils.Constant;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends AppCompatActivity {

    private TextView loginTx, signupTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signupTx = findViewById(R.id.signUp);
        loginTx = findViewById((R.id.logIn));

        loginTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("email", "thomas.pomart1@gmail.com");
                    jsonBody.put("password", "!Thomas1");
                    final String mRequestBody = jsonBody.toString();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.URL_LOGIN, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            ApiLogin apiLogin = new Gson().fromJson(response, ApiLogin.class);
                            if (apiLogin.isAuth()) {
                                //TODO
                            }
                            Log.i("LOG_RESPONSE", response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("LOG_RESPONSE", error.toString());
                        }
                    }) {
                        @Override
                        public String getBodyContentType() {
                            return "application/json; charset=utf-8";
                        }

                        @Override
                        public byte[] getBody() throws AuthFailureError {
                            try {
                                return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                            } catch (UnsupportedEncodingException uee) {
                                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                                return null;
                            }
                        }
                    };

                    requestQueue.add(stringRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        signupTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(homeIntent);
            }
        });
    }
}