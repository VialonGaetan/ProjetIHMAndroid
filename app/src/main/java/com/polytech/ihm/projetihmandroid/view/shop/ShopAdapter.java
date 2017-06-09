package com.polytech.ihm.projetihmandroid.view.shop;

import android.app.Notification;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.polytech.ihm.projetihmandroid.R;
import com.polytech.ihm.projetihmandroid.model.Shop;

import java.util.List;

/**
 * Created by Vialon Gaetan
 * on 20/04/2017.
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopView>{

    private List<Shop> list;
    private FragmentManager fm;

    public ShopAdapter(List<Shop> list,FragmentManager fm) {
        this.list = list;
        this.fm = fm;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public ShopView onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_shop,viewGroup,false);
        return new ShopView(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(final ShopView myViewHolder, int position) {
        final Shop myShop = list.get(position);
        myViewHolder.bind(myShop);
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction()
                        .replace(R.id.content_main,new ShopZoom(myShop))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
