package com.polytech.ihm.projetihmandroid.view.info;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.polytech.ihm.projetihmandroid.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Vialon Gaetan
 * on 17/05/2017.
 */

public class InfoFragment extends Fragment {

    public InfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_infos,container,false);
        TextView horaires = (TextView) rootView.findViewById(R.id.horaires);
        ImageView image = (ImageView) rootView.findViewById(R.id.infoImage);
        horaires.setText(getString(R.string.horaires));
        Picasso.with(getContext()).load("http://www.psmv-nantes.fr/images/_psmv_2_5_img_suite2.jpg").centerCrop().fit().into(image);
        return rootView;
    }
}
