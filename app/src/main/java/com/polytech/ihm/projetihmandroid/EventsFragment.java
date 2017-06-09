package com.polytech.ihm.projetihmandroid;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.polytech.ihm.projetihmandroid.model.Event;
import com.polytech.ihm.projetihmandroid.model.eventInfo.CategoryEvent;
import com.polytech.ihm.projetihmandroid.model.shopInfo.CategoryShop;
import com.polytech.ihm.projetihmandroid.view.event.EventAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {


    private List<Event> events;
    private ViewSwitcher viewSwitcher;
    private RecyclerView recyclerView;

    public EventsFragment() {
        events = new ArrayList<>();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017,06,30);
        date.setTime(calendar.getTimeInMillis());
        events.add(new Event("On se detend"," Journée détente : sea, sun & spa\n" +
                "\n" +
                "C’est votre journée. Pendant quelques heures, vous allez faire provision de douceur, de fraîcheur, d’énergie et de sérénité. Vous allez faire l’expérience, voluptueuse et enrichissante, d’un soin « égoïste » sans égoïsme : vous occuper de vous en vous reliant à ce qui vous entoure. L’idée : faire d’une simple journée à la mer une pause beauté et bien-être. Un spa dans les dunes, sur les roches et dans les vagues…\n", CategoryEvent.LOISIR,"http://harmonie-detente.fr/wp-content/uploads/2014/10/bougie-soin-detente.jpg", date,"30/06/2017"));
        calendar = Calendar.getInstance();
        calendar.set(2017,07,21);
        date = new Date();
        date.setTime(calendar.getTimeInMillis());

        events.add(new Event("Recrutement Emploi saisonnier","Cette journée est-elle faite pour moi ?\n" +
                "LCL Banque Privée est l'une des toutes premières Banques privées en France, par le nombre de ses clients et le montant de leurs en-cours. Acteur de la gestion de patrimoine depuis 1952, l'établissement est particulièrement renommé pour la compétence de ses collaborateurs et la qualité de son offre de produits et services. LCL Banque privée a pour priorité affirmée la satisfaction de ses clients et s'engage résolument dans un modèle de banque privée digital et relationnel.\n" , CategoryEvent.STAGE,"http://idsalon.info/home/wp-content/uploads/2015/03/Forum-Emploi-Saisonnier-2015.jpg",date,"21/07/2017"));

        calendar = Calendar.getInstance();
        calendar.set(2017,06,18);
        date = new Date();
        date.setTime(calendar.getTimeInMillis());
        events.add(new Event("Evenement Pokemon Go","De nouveaux PokéStops et arènes seront ajoutés dans 58 centres commerciaux en Europe, dont Polygone Riviera, ce samedi 18 juin. Un concours de chasse aux Pokémon est notamment organisé. \n Pour inaugurer leur partenariat avec Unibail-Rodamco, groupe coté de l'immobilier commercial en Europe, l'équipe de Pokémon Go met à jour son jeu. Chaque centre commercial du groupe, dont Cap Sophia, bénéficiera de 10 à 15 nouveaux PokéStops et arènes, situés dans les espaces publics, lieux de vie et œuvres d'art publiques.\n" , CategoryEvent.JEUX,"https://cdn.static01.nicematin.com/media/npo/1440w/2016/08/pokemon.jpg",date,"18/06/2017"));
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_recycler, container, false);
        //GridView gridView = (GridView) rootView.findViewById(R.id.gridView);
        //gridView.setAdapter(new EventAdapter(this.getContext(),shops));
        FloatingActionButton searchButton = (FloatingActionButton) rootView.findViewById(R.id.floatingSearchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialogBox(v);
            }
        });

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewShop);

        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        recyclerView.setAdapter(new EventAdapter(events));
        return rootView;
    }

    private void createDialogBox(View v){
        final Dialog dialog = new Dialog(v.getContext());
        dialog.setContentView(R.layout.dialog_search);
        TextView event = (TextView) dialog.findViewById(R.id.nameTextViewDialog);
        final EditText eventName = (EditText) dialog.findViewById(R.id.nameDialog);
        final Spinner spnCategory = (Spinner) dialog.findViewById(R.id.categorySpinner);
        Button btnApply = (Button) dialog.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_reset);

        event.setText(R.string.shop_name);

        List<String> eventCategories = new ArrayList<>();
        for (CategoryEvent categorie : CategoryEvent.values()) {
            eventCategories.add(getString(categorie.getName()));
        }
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(v.getContext(),android.R.layout.simple_dropdown_item_1line, eventCategories);
        spnCategory.setAdapter(spnAdapter);

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = eventName.getText().toString().toLowerCase().trim();
                CategoryEvent category = CategoryEvent.getCategoryEvent(spnCategory.getSelectedItem().toString().trim(),getContext());
                List<Event> sortEvent = new ArrayList<Event>();
                for (Event event: events) {
                    if (CategoryEvent.TOUT.equals(category) && event.getName().toLowerCase().contains(name)){
                        sortEvent.add(event);
                    }
                    else if (event.getName().toLowerCase().contains(name)&& event.getCategoryEvent().equals(category)){
                        sortEvent.add(event);
                    }
                }
                recyclerView.swapAdapter(new EventAdapter(sortEvent),true);
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.swapAdapter(new EventAdapter(events),true);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
