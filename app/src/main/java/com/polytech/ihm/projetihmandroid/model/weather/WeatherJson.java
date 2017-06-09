package com.polytech.ihm.projetihmandroid.model.weather;

/**
 * @author Gaetan Vialon
 *         Created the 05/06/2017.
 */

public enum WeatherJson {
    COD("cod"),
    NAME("name"),
    SYS("sys"),
    WEATHER("weather"),
    COUNTRY("country"),
    MAIN("main"),
    HUMIDITY("humidity"),
    PRESSURE("pressure"),
    TEMP("temp"),
    DT("dt"),
    SUNRISE("sunrise"),
    SUNSET("sunset"),
    ID("id"),
    DESCRIPTION("description");


    String string;
    WeatherJson(String string) {
        this.string=string;
    }

    @Override
    public String toString() {
        return string;
    }
}
