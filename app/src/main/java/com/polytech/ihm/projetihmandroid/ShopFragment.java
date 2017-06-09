package com.polytech.ihm.projetihmandroid;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.polytech.ihm.projetihmandroid.model.Shop;
import com.polytech.ihm.projetihmandroid.model.shopInfo.CategoryShop;
import com.polytech.ihm.projetihmandroid.view.shop.ShopAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */

public class ShopFragment extends Fragment {

    private List<Shop> shops;
    private RecyclerView recyclerView;
    private EditText shopName;
    private Spinner spnCategory;
    private Dialog dialog;

    public ShopFragment() {
        shops = new ArrayList<>();
        List<String> images = new ArrayList<>();
        images.add("https://www.subtil-diamant.com/userfiles/www.subtil-diamant.com/images/robe-mariage.jpg");
        images.add("http://www.justacote.com/photos_entreprises/galeries-lafayette---mode-rennes-1363950681.jpg");
        shops.add(new Shop("Galeries Lafayette","Parce qu'aux Galeries Lafayette la Mode vit plus fort ! Votre magasin du centre commercial Cap Sophia à Valbone vous attend","Le groupe Galeries Lafayette est un groupe français et familial spécialiste de la mode. Leader du commerce de centre-ville, il opère dans le secteur des grands magasins et du commerce à travers ses marques Galeries Lafayette, BHV MARAIS, Royal Quartz, Louis Pion, Guérin Joaillerie, InstantLuxe.com et BazarChic","http://blog.atairbnb.com/wp-content/uploads/1_NightAt_cr%C3%A9dit-photo_pour_Yann_Kersale.jpg",images,CategoryShop.MODE_HOMME,CategoryShop.MODE_FEMME,CategoryShop.MODE_ENFANT));
        images = new ArrayList<>();
        images.add("http://www.sunlux.fr/wp-content/uploads/FNAC-Ajaccio4-487x487.jpg");
        images.add("http://img.aws.la-croix.com/2016/01/20/1200732629/Fnac-Halles-Paris_0_1400_933.jpg");
        shops.add(new Shop("Fnac","Découvrez nos univers: Livres, Papeterie, Kids, maison & design, objets connectés, smatphones, high tech, apple, son, musique, vidéo, TV, jeux vidéo, billetterie,..","La Fnac (appelée à l'origine « Fédération nationale d'achats », puis « Fédération nationale d'achats des cadres ») est une chaîne de magasins française spécialisée dans la distribution de produits culturels (musique, littérature, cinéma, jeu vidéo) et électroniques (Hi-fi, informatique, télévision), à destination du grand public, dont la gamme s'est élargie en 2012 au petit électroménager, déjà présent à la création de l'enseigne et abandonné dans les années 1970, et aux jeux et jouets pour enfants jusqu'en 2010 avec la vente de Fnac Éveil et jeux.", "http://www.altaviawatch.com/wp-content/uploads/2015/01/Alexandre-Bompard-La-Fnac-Nous-avons-r%C3%A9concili%C3%A9-le-digital-et-la-vente-physique.png",images,CategoryShop.CULTURE));
        images = new ArrayList<>();
        images.add("https://media-cdn.tripadvisor.com/media/photo-s/0b/95/c8/f7/ben-burger.jpg");
        images.add("http://www.sophieben.hu/images/fb_photos/p480x480-10711134_1491151064483304_6980003921846043886_n.jpg");
        shops.add(new Shop("BEN BURGER","Ben burger est un restaurant/fast food qui vous fera les meilleurs burgers de la région","Découvrez d'authentiques burgers gourmands à la française: deux généreuses tranches de pain, une viande savoureuse, la fraîcheur des tomates et le croquant d'une salade... Il n'en faut pas plus pour faire de bons burgers ! ","https://s3-media2.fl.yelpcdn.com/bphoto/tdIbeOQ1SuBlrEmWd7XnsA/258s.jpg",images,CategoryShop.RESTAURATION));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_recycler, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewShop);
        FloatingActionButton searchButton = (FloatingActionButton) rootView.findViewById(R.id.floatingSearchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialogBox(v);
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(),2));

        recyclerView.setAdapter(new ShopAdapter(shops,this.getFragmentManager()));
        return rootView;
    }

    private void createDialogBox(View v){
        dialog = new Dialog(v.getContext());
        dialog.setContentView(R.layout.dialog_search);
        TextView shop = (TextView) dialog.findViewById(R.id.nameTextViewDialog);
        shopName = (EditText) dialog.findViewById(R.id.nameDialog);
        spnCategory = (Spinner) dialog.findViewById(R.id.categorySpinner);
        Button btnApply = (Button) dialog.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_reset);

        shop.setText(R.string.shop_name);

        List<String> shopCategories = new ArrayList<>();
        for (CategoryShop categorie : CategoryShop.values()) {
            shopCategories.add(getString(categorie.getName()));
        }

        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(v.getContext(),android.R.layout.simple_dropdown_item_1line, shopCategories);
        spnCategory.setAdapter(spnAdapter);

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyFilter();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFilter();
            }
        });
        dialog.show();
    }

    private void applyFilter(){
        String name = shopName.getText().toString().toLowerCase().trim();
        CategoryShop category = CategoryShop.getCategoryShop(spnCategory.getSelectedItem().toString().trim(),getContext());
        List<Shop> sortShop = new ArrayList<>();
        for (Shop shop: shops) {
            if (CategoryShop.TOUT.equals(category) && shop.getName().toLowerCase().contains(name)){
                sortShop.add(shop);
            }
            else if (shop.getName().toLowerCase().contains(name) && shop.getCategoryShop().contains(category)){
                sortShop.add(shop);
            }
        }
        recyclerView.swapAdapter(new ShopAdapter(sortShop,getFragmentManager()),true);
        dialog.dismiss();
    }

    private void resetFilter(){
        recyclerView.swapAdapter(new ShopAdapter(shops,getFragmentManager()),true);
        dialog.dismiss();
    }

}
