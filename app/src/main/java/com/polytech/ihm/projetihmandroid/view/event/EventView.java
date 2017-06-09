package com.polytech.ihm.projetihmandroid.view.event;

import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import com.polytech.ihm.projetihmandroid.R;
import com.polytech.ihm.projetihmandroid.model.Event;
import com.squareup.picasso.Picasso;

/**
 * Created by Vialon Gaetan
 * on 22/04/2017.
 */

public class EventView extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView name;
    private TextView category;
    private TextView descrp;
    private TextView completeDescrp;
    private TextView date;
    private ImageView mainShopView;
    private ImageView overflow;
    private ViewSwitcher viewSwitcher;
    private Event event;


    public EventView(final View itemView) {
        super(itemView);
        mainShopView = (ImageView) itemView.findViewById(R.id.mainEventView);
        category = (TextView) itemView.findViewById(R.id.categoryEvent);
        name = (TextView) itemView.findViewById(R.id.nameEvent);
        viewSwitcher = (ViewSwitcher) itemView.findViewById(R.id.eventViewSwitch);
        overflow = (ImageView) itemView.findViewById(R.id.overflow);
        completeDescrp = (TextView) itemView.findViewById(R.id.fullDescrpEvent);
        date = (TextView) itemView.findViewById(R.id.dateEvent);
        mainShopView.setOnClickListener(this);
        completeDescrp.setOnClickListener(this);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(itemView);
            }
        });
        overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(itemView);
            }
        });

    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(Event myEvent){
        event = myEvent;
        name.setText(myEvent.getName());
        category.setText(myEvent.getCategoryEvent().getName());
        completeDescrp.setText(myEvent.getDescription());
        date.setText(myEvent.getDateString());
        Picasso.with(mainShopView.getContext()).load(myEvent.getImage()).centerCrop().fit().into(mainShopView);
    }

    @Override
    public void onClick(View v) {
        viewSwitcher.showNext();
    }

    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(view.getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_event, popup.getMenu());
        popup.setOnMenuItemClickListener(new EventMenu(view.getContext(),event));
        popup.setGravity(Gravity.END);
        popup.show();
    }
}
