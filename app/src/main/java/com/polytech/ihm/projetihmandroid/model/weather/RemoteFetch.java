package com.polytech.ihm.projetihmandroid.model.weather;

import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Gaetan Vialon
 *         Created the 05/06/2017.
 */

public class RemoteFetch{
    private String city;
    private static final String OPEN_WEATHER_MAP_API =
            "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=04bfbd20a24643a78fd6aa5fda951b77";

    public RemoteFetch(String city) {
        this.city=city;
    }

    public JSONObject getJSON(){
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(String.format(OPEN_WEATHER_MAP_API, city)).openConnection();

            Scanner reader = new Scanner(
                    new InputStreamReader(connection.getInputStream()));

            StringBuilder json = new StringBuilder(1024);
            while(reader.hasNext())
                json.append(reader.nextLine()).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            if(data.getInt(WeatherJson.COD.toString()) != 200)
                return null;
            return data;
        }catch(Exception e){
            return null;
        }
    }
}