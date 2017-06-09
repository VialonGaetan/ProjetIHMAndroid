package com.polytech.ihm.projetihmandroid.view.info;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.polytech.ihm.projetihmandroid.R;

import java.util.Random;

/**
 * Created by Vialon Gaetan
 * on 17/05/2017.
 */

public class ParkingFragment extends Fragment {

    private final static int placeParking1 = 1500 ;
    private final static int placeParking2 = 2300 ;
    private final static int placeParking3 = 4000 ;
    private ProgressBar pbParking1,pbParking2,pbParking3;
    private TextView placeAvaibleParking1,placeAvaibleParking2,placeAvaibleParking3;

    public ParkingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        int placeUseParking1 = new Random().nextInt(1500);
        int placeUseParking2 = new Random().nextInt(2300);
        int placeUseParking3 = new Random().nextInt(4000);
        int placeDispo;
        View rootView = inflater.inflate(R.layout.fragment_parking,container,false);
        TextView title = (TextView) rootView.findViewById(R.id.ParkingTitle);
        placeAvaibleParking1 = (TextView) rootView.findViewById(R.id.placeAvailibleparking1);
        placeAvaibleParking2 = (TextView) rootView.findViewById(R.id.placeAvailibleparking2);
        placeAvaibleParking3 = (TextView) rootView.findViewById(R.id.placeAvailibleparking3);
        pbParking1 = (ProgressBar) rootView.findViewById(R.id.progressBar_parking1);
        pbParking2 = (ProgressBar) rootView.findViewById(R.id.progressBar_parking2);
        pbParking3 = (ProgressBar) rootView.findViewById(R.id.progressBar_parking3);
        pbParking1.setMax(placeParking1);
        pbParking2.setMax(placeParking2);
        pbParking3.setMax(placeParking3);
        pbParking1.setProgress(placeUseParking1);
        pbParking2.setProgress(placeUseParking2);
        pbParking3.setProgress(placeUseParking3);
        placeDispo = placeParking1-placeUseParking1;
        placeAvaibleParking1.setText(getString(R.string.parkingPlace) + placeDispo + "\t\tTotal place : " + placeParking1);
        placeDispo = placeParking2-placeUseParking2;
        placeAvaibleParking2.setText(getString(R.string.parkingPlace) + placeDispo + "\t\tTotal place : " + placeParking2);
        placeDispo = placeParking3-placeUseParking3;
        placeAvaibleParking3.setText(getString(R.string.parkingPlace) + placeDispo + "\t\tTotal place : " + placeParking3);
        return rootView;
    }
}
