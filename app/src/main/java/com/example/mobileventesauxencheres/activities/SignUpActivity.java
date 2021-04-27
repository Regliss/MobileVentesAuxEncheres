package com.example.mobileventesauxencheres.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
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

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextLastname;
    private EditText editTextFirstname;
    private EditText editTextAddress;
    private EditText editTextPhone;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView register;
    private ProgressBar progressBarSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextLastname = findViewById(R.id.editText_lastname);
        editTextFirstname = findViewById(R.id.editText_firstname);
        editTextAddress = findViewById(R.id.editText_address);
        editTextPhone = findViewById(R.id.editText_phone);
        editTextEmail = findViewById(R.id.editText_email);
        editTextPassword = findViewById(R.id.editText_password);
        register = findViewById(R.id.register);
        progressBarSignUp = findViewById(R.id.progress_bar_sign_up);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    RequestQueue requestQueue = Volley.newRequestQueue(SignUpActivity.this);
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("lastName", editTextLastname.getText());
                    jsonBody.put("firstName", editTextFirstname.getText());
                    jsonBody.put("address", editTextAddress.getText());
                    jsonBody.put("phone", editTextPhone.getText());
                    jsonBody.put("email", editTextEmail.getText());
                    jsonBody.put("password", editTextPassword.getText());

                    final String mRequestBody = jsonBody.toString();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.URL_SIGNUP, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            ApiLogin apiLogin = new Gson().fromJson(response, ApiLogin.class);
                            if (apiLogin.isAuth()) {
                                Preference.setToken(SignUpActivity.this, apiLogin.getToken());
                                Intent homeIntent = new Intent(SignUpActivity.this, HomeActivity.class);
                                startActivity(homeIntent);
                                //TODO
                            } else {
                                FastDialog.showDialog(SignUpActivity.this, FastDialog.SIMPLE_DIALOG, apiLogin.getMessage());
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

    }
}