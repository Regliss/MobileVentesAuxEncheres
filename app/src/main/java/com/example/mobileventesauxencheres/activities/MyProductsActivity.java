package com.example.mobileventesauxencheres.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mobileventesauxencheres.AppActivity;
import com.example.mobileventesauxencheres.HomeActivity;
import com.example.mobileventesauxencheres.ProductsAdapter;
import com.example.mobileventesauxencheres.R;
import com.example.mobileventesauxencheres.models.ApiRecords;
import com.example.mobileventesauxencheres.utils.Preference;

import java.util.ArrayList;
import java.util.List;

public class MyProductsActivity extends AppActivity {

    private ListView listViewData;
    private ApiRecords item;
    private List<ApiRecords> records = new ArrayList<>();

    private ArrayAdapter<ApiRecords> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products);

        listViewData = findViewById(R.id.listViewData);

        records = Preference.getMyProducts(MyProductsActivity.this);

        adapter = (new ProductsAdapter(
           MyProductsActivity.this,
           R.layout.item_product,
           records
        ));

        listViewData.setAdapter(adapter);

        listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ApiRecords item = records.get(position);

                Intent intentDetails = new Intent(MyProductsActivity.this, DetailActivity.class);

                intentDetails.putExtra("objet", item);

                startActivity(intentDetails);
                adapter.notifyDataSetChanged();
            }
        });

        listViewData.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = (ApiRecords) getIntent().getExtras().get("objet");
                Preference.removeToMyProducts(MyProductsActivity.this, item);
                records.remove(position);
                //ListViewData.stringList.remove(position);
                adapter.notifyDataSetChanged(); //demande de rafraichissement
                return false;
            }
        });
    }

    public void reset(View view) {
        Preference.resetMyProducts(MyProductsActivity.this);

        Toast.makeText(MyProductsActivity.this, "Reset My Products List", Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();//rafraîchissement
        Intent intentRetour = new Intent(MyProductsActivity.this, HomeActivity.class);
        startActivity(intentRetour);
    }
}