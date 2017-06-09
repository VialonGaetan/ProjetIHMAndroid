package com.polytech.ihm.projetihmandroid.view.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.polytech.ihm.projetihmandroid.R;
import com.polytech.ihm.projetihmandroid.model.Shop;
import com.squareup.picasso.Picasso;

/**
 * Created by Vialon Gaetan
 * on 24/04/2017.
 */

public class ShopZoom extends Fragment {

    private Shop shop;
    private TextView fulldescrp;
    private TextView name;
    private TextView category;
    private ImageView mainImage;
    private ImageView productPhare1,productPhare2,productPhare3;


    public ShopZoom(Shop shop){
        this.shop=shop;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_shop_zoom, container, false);


        fulldescrp = (TextView) rootView.findViewById(R.id.fullDescrpShop);
        fulldescrp.setText(shop.getFullDescription());
        name = (TextView) rootView.findViewById(R.id.nameShop2);
        category = (TextView) rootView.findViewById(R.id.categoryShop2);
        name.setText(shop.getName());
        category.setText(shop.getCategoryShop().get(0).getName());
        mainImage = (ImageView) rootView.findViewById(R.id.mainShopView2);
        Picasso.with(mainImage.getContext()).load(shop.getImage()).centerCrop().fit().into(mainImage);
        productPhare1 = (ImageView) rootView.findViewById(R.id.productPhare1);
        Picasso.with(productPhare1.getContext()).load(shop.getImages().get(0)).centerCrop().fit().into(productPhare1);
        productPhare2 = (ImageView) rootView.findViewById(R.id.productPhare2);
        Picasso.with(productPhare2.getContext()).load(shop.getImages().get(1)).centerCrop().fit().into(productPhare2);
        //productPhare3 = (ImageView) rootView.findViewById(R.id.productPhare3);
        //Picasso.with(productPhare3.getContext()).load(shop.getImages().get(2)).centerCrop().fit().into(productPhare3);


        return rootView;
    }


}
