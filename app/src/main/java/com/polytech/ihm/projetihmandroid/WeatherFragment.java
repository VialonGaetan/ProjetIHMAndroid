package com.polytech.ihm.projetihmandroid;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.polytech.ihm.projetihmandroid.model.weather.RemoteFetch;
import com.polytech.ihm.projetihmandroid.model.weather.WeatherJson;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Gaetan Vialon
 *         Created the 03/06/2017.
 */

public class WeatherFragment extends Fragment {
    private Typeface weatherFont;

    private TextView cityField;
    private TextView updatedField;
    private TextView detailsField;
    private TextView currentTemperatureField;
    private TextView weatherIcon;
    private Handler handler;

    public WeatherFragment(){
        handler = new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        cityField = (TextView)rootView.findViewById(R.id.city_field);
        updatedField = (TextView)rootView.findViewById(R.id.updated_field);
        detailsField = (TextView)rootView.findViewById(R.id.details_field);
        currentTemperatureField = (TextView)rootView.findViewById(R.id.current_temperature_field);
        weatherIcon = (TextView)rootView.findViewById(R.id.weather_icon);

        weatherIcon.setTypeface(weatherFont);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weather.ttf");
        updateWeatherData("Antibes, FR");
    }

    private void updateWeatherData(final String city){
        new Thread(){
            public void run(){
                final RemoteFetch rF = new RemoteFetch(city);
                final JSONObject json = rF.getJSON();
                if(json == null){
                    handler.post(new Runnable(){
                        public void run(){
                            Toast.makeText(getContext(),
                                    getString(R.string.place_not_found),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable(){
                        public void run(){
                            renderWeather(json);
                        }
                    });
                }
            }
        }.start();
    }

    private void renderWeather(JSONObject json){
        try {
            cityField.setText(json.getString(WeatherJson.NAME.toString()).toUpperCase(Locale.US) +
                    ", " +
                    json.getJSONObject(WeatherJson.SYS.toString()).getString(WeatherJson.COUNTRY.toString()));

            JSONObject details = json.getJSONArray(WeatherJson.WEATHER.toString()).getJSONObject(0);
            JSONObject main = json.getJSONObject(WeatherJson.MAIN.toString());
            detailsField.setText(
                    details.getString(WeatherJson.DESCRIPTION.toString()).toUpperCase(Locale.US) +
                            "\n" + "Humidity: " + main.getString(WeatherJson.HUMIDITY.toString()) + "%" +
                            "\n" + "Pressure: " + main.getString(WeatherJson.PRESSURE.toString()) + " hPa");

            currentTemperatureField.setText(
                    String.format("%.2f", main.getDouble(WeatherJson.TEMP.toString()))+ " ℃");

            DateFormat df = DateFormat.getDateTimeInstance();
            String updatedOn = df.format(new Date(json.getLong(WeatherJson.DT.toString())*1000));
            updatedField.setText("Last update: " + updatedOn);

            setWeatherIcon(details.getInt(WeatherJson.ID.toString()),
                    json.getJSONObject(WeatherJson.SYS.toString()).getLong(WeatherJson.SUNRISE.toString()) * 1000,
                    json.getJSONObject(WeatherJson.SYS.toString()).getLong(WeatherJson.SUNSET.toString()) * 1000);

        }catch(Exception e){
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }
    }

    private void setWeatherIcon(int actualId, long sunrise, long sunset){
        int id = actualId / 100;
        String icon = "";
        if(actualId == 800){
            long currentTime = new Date().getTime();
            if(currentTime>=sunrise && currentTime<sunset) {
                icon = getString(R.string.weather_sunny);
            } else {
                icon = getString(R.string.weather_clear_night);
            }
        } else {
            switch(id) {
                case 2 : icon = getString(R.string.weather_thunder);
                    break;
                case 3 : icon = getString(R.string.weather_drizzle);
                    break;
                case 7 : icon = getString(R.string.weather_foggy);
                    break;
                case 8 : icon = getString(R.string.weather_cloudy);
                    break;
                case 6 : icon = getString(R.string.weather_snowy);
                    break;
                case 5 : icon = getString(R.string.weather_rainy);
                    break;
            }
        }
        weatherIcon.setText(icon);
    }
}