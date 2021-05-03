package com.example.mobileventesauxencheres.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileventesauxencheres.AppActivity;
import com.example.mobileventesauxencheres.R;
import com.example.mobileventesauxencheres.models.ApiRecords;
import com.example.mobileventesauxencheres.utils.FastDialog;
import com.example.mobileventesauxencheres.utils.Network;
import com.example.mobileventesauxencheres.utils.Preference;
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
            textViewPriceProduct.setText(item.getFields().getPrice() + "€");
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

        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);

        dialogBuilder.setTitle("Custom dialog");
        dialogBuilder.setMessage("Enter text below");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}