package com.example.mobileventesauxencheres.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobileventesauxencheres.AppActivity;
import com.example.mobileventesauxencheres.R;
import com.example.mobileventesauxencheres.models.ApiRecords;
import com.example.mobileventesauxencheres.utils.FastDialog;
import com.example.mobileventesauxencheres.utils.Network;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppActivity {

    private ImageView imageProduct;
    private TextView textViewTitleProduct;
    private TextView textViewDescriptionProduct;
    private TextView textViewPriceProduct;
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
            textViewDescriptionProduct.setText(item.getFields().getDescription());
            textViewPriceProduct.setText(item.getFields().getPrice());
        }

    }
}