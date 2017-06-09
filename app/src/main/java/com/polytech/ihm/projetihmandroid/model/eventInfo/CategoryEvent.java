package com.polytech.ihm.projetihmandroid.model.eventInfo;

import android.content.Context;
import com.polytech.ihm.projetihmandroid.R;

/**
 * Created by Vialon Gaetan
 * on 22/04/2017.
 */

public enum CategoryEvent {
    TOUT(R.string.category_Tout),
    STAGE(R.string.category_event_Stage),
    LOISIR(R.string.category_event_Loisir),
    FORMATION(R.string.category_event_Formation),
    JEUX(R.string.category_event_Jeux),
    CONCOURS(R.string.category_event_Concours);

    int name;
    CategoryEvent(int name) {
        this.name=name;
    }

    public int getName() {
        return name;
    }

    public static CategoryEvent getCategoryEvent(String trim, Context context) {
        for (CategoryEvent category : CategoryEvent.values()) {
            if (trim.equals(context.getString(category.getName()))){
                return category;
            }
        }
        return null;
    }
}
