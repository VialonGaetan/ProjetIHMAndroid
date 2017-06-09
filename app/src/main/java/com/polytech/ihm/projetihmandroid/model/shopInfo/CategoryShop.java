package com.polytech.ihm.projetihmandroid.model.shopInfo;

import android.content.Context;
import com.polytech.ihm.projetihmandroid.R;

/**
 * Created by Vialon Gaetan
 * on 18/04/2017.
 */

public enum CategoryShop {
    TOUT(R.string.category_Tout),
    JOUET(R.string.category_shop_Jouet),
    MODE_FEMME(R.string.category_shop_ModeF),
    MODE_ENFANT(R.string.category_shop_ModeE),
    MODE_HOMME(R.string.category_shop_ModeH),
    SPORT(R.string.category_shop_Sport),
    BEAUTE(R.string.category_shop_Beauty),
    CULTURE(R.string.category_shop_Culture),
    RESTAURATION(R.string.category_shop_Restauration),
    MAISON(R.string.category_shop_Maison);

    int name;
    CategoryShop(int name) {
        this.name=name;
    }

    public int getName() {
        return name;
    }

    public static CategoryShop getCategoryShop(String trim, Context context) {
        for (CategoryShop category : CategoryShop.values()) {
            if (trim.equals(context.getString(category.getName()))){
                return category;
            }
        }
        return null;
    }
}
