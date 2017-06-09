package com.polytech.ihm.projetihmandroid.view.event;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.polytech.ihm.projetihmandroid.R;
import com.polytech.ihm.projetihmandroid.model.Event;

import java.util.List;

/**
 * Created by Vialon Gaetan
 * on 20/04/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventView> {

    private List<Event> list;
    //ajouter un constructeur prenant en entrée une liste
    public EventAdapter(List<Event> list) {
        this.list = list;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public EventView onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_event,viewGroup,false);
        return new EventView(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(EventView myViewHolder, int position) {
        Event myEvent = list.get(position);
        myViewHolder.bind(myEvent);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
