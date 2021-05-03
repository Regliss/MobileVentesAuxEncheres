package com.example.mobileventesauxencheres.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.mobileventesauxencheres.HomeActivity;
import com.example.mobileventesauxencheres.R;
import com.example.mobileventesauxencheres.models.ApiLogin;
import com.example.mobileventesauxencheres.utils.Constant;
import com.example.mobileventesauxencheres.utils.FastDialog;
import com.example.mobileventesauxencheres.utils.Preference;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends AppCompatActivity {

    private Button loginTx;
    private EditText edEmail;
    private EditText edPasskey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginTx = findViewById(R.id.logIn);
        edEmail = findViewById(R.id.email);
        edPasskey = findViewById(R.id.passkey);

        getSupportActionBar().hide();

        loginTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

    }

    private void userLogin() {
        try {
            String email = edEmail.getText().toString().trim();
            String passkey = edPasskey.getText().toString().trim();

            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("email", email);
            jsonBody.put("password", passkey);
            final String mRequestBody = jsonBody.toString();

            if(email.isEmpty() || passkey.isEmpty()) {
                Toast.makeText(this, "Please enter both Email and Password.", Toast.LENGTH_SHORT).show();
                return;
            }

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.URL_LOGIN, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    ApiLogin apiLogin = new Gson().fromJson(response, ApiLogin.class);
                    if (apiLogin.isAuth()) {
                        Preference.setToken(LoginActivity.this, apiLogin.getToken());
                        apiLogin.setAuth(true);
                        Preference.setToken(LoginActivity.this, String.valueOf(apiLogin.isAuth()));
                        Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(homeIntent);
                        //TODO
                    } else {
                        FastDialog.showDialog(LoginActivity.this, FastDialog.SIMPLE_DIALOG, apiLogin.getMessage());
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

    public void signUp(View view) {
        Intent homeIntent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(homeIntent);
    }
}