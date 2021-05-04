package com.example.mobileventesauxencheres.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobileventesauxencheres.AppActivity;
import com.example.mobileventesauxencheres.HomeActivity;
import com.example.mobileventesauxencheres.R;
import com.example.mobileventesauxencheres.models.ApiLogin;
import com.example.mobileventesauxencheres.models.ApiRecords;
import com.example.mobileventesauxencheres.utils.Constant;
import com.example.mobileventesauxencheres.utils.FastDialog;
import com.example.mobileventesauxencheres.utils.Network;
import com.example.mobileventesauxencheres.utils.Preference;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class DetailActivity extends AppActivity {

    private ImageView imageProduct;
    private TextView textViewTitleProduct;
    private TextView textViewDescriptionProduct;
    private TextView textViewPriceProduct;
    private TextView textViewDateDebut;
    private TextView textViewDateFin;
    private TextView textViewCategory;


    private ApiRecords item;



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageProduct = findViewById(R.id.image_product);
        textViewTitleProduct = findViewById(R.id.textViewTitleProduct);
        textViewDescriptionProduct = findViewById(R.id.textViewDescriptionProduct);
        textViewPriceProduct = findViewById(R.id.textViewPriceProduct);
        textViewDateDebut = findViewById(R.id.textViewDateDebut);
        textViewDateFin = findViewById(R.id.textViewDateFin);
        textViewCategory = findViewById(R.id.textViewCategory);

        if (!Network.isNetworkAvailable(DetailActivity.this)) {
            FastDialog.showDialog(
                    DetailActivity.this,
                    FastDialog.SIMPLE_DIALOG,
                    "Vous devez être connecté"
            );
            return;
        }

        //Récupère les données pour le transfert de data
        if (getIntent().getExtras() != null) {
            item = (ApiRecords) getIntent().getExtras().get("objet");

            Picasso.get().load(item.getFields().getImage()).into(imageProduct);
            textViewTitleProduct.setText(item.getFields().getTitle());
            textViewCategory.setText(item.getFields().getCategory().getTitle());
            textViewDescriptionProduct.setText(item.getFields().getDescription());
            textViewPriceProduct.setText(item.getFields().getPrice() + "€");
            textViewDateDebut.setText(item.getFields().getDateStart());
            textViewDateFin.setText(item.getFields().getDateEnd());
        }


    }

    public void submit(View view) {
        //Ajout des données dans les favoris
        Preference.addToMyProducts(DetailActivity.this, item);

        Toast.makeText(DetailActivity.this, "Ajouté à mes Produits", Toast.LENGTH_SHORT).show();
    }

    public void updateBid(View view) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.bid_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.editText_bid);

        dialogBuilder.setTitle("Prix de l'enchère");
        dialogBuilder.setMessage("Entrer un prix");
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                if (getIntent().getExtras() != null) {
                    item = (ApiRecords) getIntent().getExtras().get("objet");
                    try {
                        String editBid = edt.getText().toString().trim();
                        String title = textViewTitleProduct.getText().toString().trim();
                        String description = textViewDescriptionProduct.getText().toString().trim();
                        String image = item.getFields().getImage().trim();
                        String category = item.getFields().getCategory().get_id().trim() ;
                        String dateStart = textViewDateDebut.getText().toString().trim();
                        String dateEnd = textViewDateFin.getText().toString().trim();

                        RequestQueue requestQueue = Volley.newRequestQueue(DetailActivity.this);
                        JSONObject jsonBody = new JSONObject();
                        jsonBody.put("price", editBid);
                        jsonBody.put("title", title);
                        jsonBody.put("image", image);
                        jsonBody.put("description", description);
                        jsonBody.put("category", category);
                        jsonBody.put("dateStart", dateStart);
                        jsonBody.put("dateEnd", dateEnd);
                        final String mRequestBody = jsonBody.toString();

                        if(editBid.isEmpty()) {
                            Toast.makeText(DetailActivity.this, "Please enter a Bid.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.URL_BID_PRODUCT+item.getFields().get_id(), new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//                                ApiLogin apiLogin = new Gson().fromJson(response, ApiLogin.class);
//                                if (apiLogin.isAuth()) {
//                                    Preference.setToken(LoginActivity.this, apiLogin.getToken());
//                                    apiLogin.setAuth(true);
//                                    Preference.setToken(LoginActivity.this, String.valueOf(apiLogin.isAuth()));
//                                    Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
//                                    startActivity(homeIntent);
//                                    //TODO
//                                } else {
//                                    FastDialog.showDialog(LoginActivity.this, FastDialog.SIMPLE_DIALOG, apiLogin.getMessage());
//                                }
//                                Log.i("LOG_RESPONSE", response);
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
            }
        });
        dialogBuilder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
                dialog.cancel();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}