package com.example.mobileventesauxencheres;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mobileventesauxencheres.models.ApiRecords;
import com.squareup.picasso.Picasso;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class ProductsAdapter extends ArrayAdapter<ApiRecords> {
    private int resId;


    public ProductsAdapter(Context context, int resource,@NonNull List<ApiRecords> objects) {
        super(context,resource,objects);

        resId = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // déclaration du ViewHolder
        ViewHolder myViewHolder;

        if(convertView == null) {
            myViewHolder = new ViewHolder(); // instance
            // 1) chargement du layout R.layout.item_restaurant
            convertView = LayoutInflater.from(getContext()).inflate(resId, null);

            // 2) récupération des vues (élements graphiques)
            myViewHolder.imageViewProduct = convertView.findViewById(R.id.imageViewProduct);
            myViewHolder.textViewTitle = convertView.findViewById(R.id.textViewTitle);
            myViewHolder.textViewDescription = convertView.findViewById(R.id.textViewDescription);
            myViewHolder.textViewPrice = convertView.findViewById(R.id.textViewPrice);
            myViewHolder.textViewDateDebut = convertView.findViewById(R.id.textViewDateDebut);
            myViewHolder.textViewDateFin = convertView.findViewById(R.id.textViewDateFin);

            convertView.setTag(myViewHolder); // enregistrement du ViewHolder (qui contient le titre et la catégorie)
        } else {
            myViewHolder = (ViewHolder) convertView.getTag();
        }

        // 3) données (Bibliothèques)
        ApiRecords item = getItem(position);

        // 4) affichage (setText)
        Picasso.get().load(item.getFields().getImage()).into(myViewHolder.imageViewProduct);
        myViewHolder.textViewTitle.setText(item.getFields().getTitle());
        myViewHolder.textViewDescription.setText(item.getFields().getDescription());
        myViewHolder.textViewPrice.setText(item.getFields().getPrice() + "€");
        myViewHolder.textViewDateDebut.setText(item.getFields().getDateStart());
        myViewHolder.textViewDateFin.setText(item.getFields().getDateEnd());




        return convertView;
    }

    class ViewHolder {
        ImageView imageViewProduct;
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewPrice;
        TextView textViewDateDebut;
        TextView textViewDateFin;
    }
}
