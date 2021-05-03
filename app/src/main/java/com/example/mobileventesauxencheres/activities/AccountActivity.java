package com.example.mobileventesauxencheres.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobileventesauxencheres.AppActivity;
import com.example.mobileventesauxencheres.R;
import com.example.mobileventesauxencheres.models.ApiRecords;
import com.example.mobileventesauxencheres.models.ApiUser;
import com.example.mobileventesauxencheres.utils.FastDialog;
import com.example.mobileventesauxencheres.utils.Network;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class AccountActivity extends AppActivity {


    private TextView textViewLastname;
    private TextView textViewFirstname;
    private TextView textViewAddress;
    private TextView textViewPhone;
    private TextView textViewEmail;
    private Button edit;
    private ApiUser account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        textViewLastname = findViewById(R.id.textView_lastname);
        textViewFirstname = findViewById(R.id.textView_firstname);
        textViewAddress = findViewById(R.id.textView_address);
        textViewPhone = findViewById(R.id.textView_phone);
        textViewEmail = findViewById(R.id.textView_email);
        edit = findViewById(R.id.edit);

        if (!Network.isNetworkAvailable(AccountActivity.this)) {
            FastDialog.showDialog(
                    AccountActivity.this,
                    FastDialog.SIMPLE_DIALOG,
                    "Vous devez être connecté"
            );
            return;
        }

        if (getIntent().getExtras() != null) {
            account = (ApiUser) getIntent().getExtras().get("objet");
            //account = new Gson().fromJson()

            textViewLastname.setText(account.getLastName());
            textViewFirstname.setText(account.getFirstName());
            textViewAddress.setText(account.getAddress());
            textViewPhone.setText(account.getPhone());
            textViewEmail.setText(account.getEmail());
        }
    }
}