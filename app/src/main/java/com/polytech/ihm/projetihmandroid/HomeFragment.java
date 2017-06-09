package com.polytech.ihm.projetihmandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

/**
 * @author Gaetan Vialon
 *         Created the 26/05/2017.
 */

public class HomeFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        TextView title = (TextView) rootView.findViewById(R.id.homeTitle);
        ImageView image = (ImageView) rootView.findViewById(R.id.homeImage);
        title.setText(R.string.welcome);
        Picasso.with(getContext()).load("http://www.l35.com/img/img_prj/CENTRE%20JOUR%20POLYGONE%20RIVIERA-75.jpg").centerCrop().fit().into(image);


        return rootView;
    }
}
