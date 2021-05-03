package com.example.mobileventesauxencheres;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mobileventesauxencheres.models.ApiUser;

import java.util.Collections;
import java.util.List;

public class UserAdapter extends ArrayAdapter<ApiUser> {
    private int resId;


    public UserAdapter(Context context, int resource, @NonNull ApiUser object) {
        super(context,resource, Collections.singletonList(object));

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
            myViewHolder.textViewLastName = convertView.findViewById(R.id.textView_lastname);
            myViewHolder.textViewFirstName = convertView.findViewById(R.id.textView_firstname);
            myViewHolder.textViewAddress = convertView.findViewById(R.id.textView_address);
            myViewHolder.textViewPhone = convertView.findViewById(R.id.textView_phone);
            myViewHolder.textViewEmail = convertView.findViewById(R.id.textView_email);

            convertView.setTag(myViewHolder); // enregistrement du ViewHolder (qui contient le titre et la catégorie)
        } else {
            myViewHolder = (ViewHolder) convertView.getTag();
        }

        // 3) données (Bibliothèques)
        ApiUser account = getItem(position);

        // 4) affichage (setText)
        myViewHolder.textViewLastName.setText(account.getLastName());
        myViewHolder.textViewFirstName.setText(account.getFirstName());
        myViewHolder.textViewAddress.setText(account.getAddress());
        myViewHolder.textViewPhone.setText(account.getPhone());
        myViewHolder.textViewEmail.setText(account.getEmail());

        return convertView;
    }

    class ViewHolder {
        TextView textViewLastName;
        TextView textViewFirstName;
        TextView textViewAddress;
        TextView textViewPhone;
        TextView textViewEmail;
    }
}
