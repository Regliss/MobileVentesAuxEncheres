package com.example.mobileventesauxencheres;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileventesauxencheres.models.ApiProducts;

import java.util.List;

public class ProductsAdapter extends ArrayAdapter<ApiProducts> {


    public ProductsAdapter(Context context, int resource, ApiProducts api) {
        super(context,resource, (List<ApiProducts>) api);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // déclaration du ViewHolder
        ViewHolder myViewHolder;

        if(convertView == null) {
            myViewHolder = new ViewHolder(); // instance

            // 2) récupération des vues (élements graphiques)
            myViewHolder.imageViewProduct = convertView.findViewById(R.id.imageViewProduct);
            myViewHolder.textViewTitle = convertView.findViewById(R.id.textViewTitle);
            myViewHolder.textViewDescription = convertView.findViewById(R.id.textViewDescription);
            myViewHolder.textViewPrice = convertView.findViewById(R.id.textViewPrice);


            convertView.setTag(myViewHolder); // enregistrement du ViewHolder (qui contient le titre et la catégorie)
        } else {
            myViewHolder = (ViewHolder) convertView.getTag();
        }

        // 3) données (Bibliothèques)
        ApiProducts item = getItem(position);

        // 4) affichage (setText)
        myViewHolder.textViewTitle.setText(item.getTitle());
        myViewHolder.textViewDescription.setText(item.getDescription());
        myViewHolder.textViewPrice.setText(item.getPrice());


        return convertView;
    }

    class ViewHolder {
        ImageView imageViewProduct;
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewPrice;
    }
}
