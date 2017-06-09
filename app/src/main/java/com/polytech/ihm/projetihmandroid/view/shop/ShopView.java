package com.polytech.ihm.projetihmandroid.view.shop;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.polytech.ihm.projetihmandroid.R;
import com.polytech.ihm.projetihmandroid.model.Shop;
import com.squareup.picasso.Picasso;

/**
 * Created by Vialon Gaetan
 * on 22/04/2017.
 */

public class ShopView extends RecyclerView.ViewHolder{

    private TextView name;
    private TextView category;
    private TextView descrp;
    private ImageView mainShopView;
    private Shop shop;

    public ShopView(View itemView) {
        super(itemView);
        mainShopView = (ImageView) itemView.findViewById(R.id.mainShopView);
        descrp = (TextView) itemView.findViewById(R.id.descrpShop);
        category = (TextView) itemView.findViewById(R.id.categoryShop);
        name = (TextView) itemView.findViewById(R.id.nameShop);
    }

    public void bind(Shop myShop) {
        shop = myShop;
        name.setText(myShop.getName());
        category.setText(myShop.getCategoryShop().get(0).getName());
        descrp.setText(myShop.getDescription());
        Picasso.with(mainShopView.getContext()).load(myShop.getImage()).centerCrop().fit().into(mainShopView);
    }
}
