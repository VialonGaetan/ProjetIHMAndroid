package com.polytech.ihm.projetihmandroid.view.info;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.polytech.ihm.projetihmandroid.R;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Vialon Gaetan
 * on 17/05/2017.
 */

public class BusFragment extends Fragment {


    public BusFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_bus,container,false);
        TextView context = (TextView) rootView.findViewById(R.id.busContext);
        TextView listLigne = (TextView) rootView.findViewById(R.id.listeLigneBus);
        ImageView image = (ImageView) rootView.findViewById(R.id.busImage);
        context.setText(getString(R.string.bus_context));
        listLigne.setText(R.string.listeligne);
        listLigne.setMovementMethod(LinkMovementMethod.getInstance());
        Picasso.with(getContext()).load("http://www.psmv-nantes.fr/images/_psmv_2_5_img_suite2.jpg").centerCrop().fit().into(image);

        return rootView;
    }
}
