package com.example.sunshinerecyclerview.data;

import android.content.Context;

/**
 * Created by Dell1 on 28/04/2017.
 */

public class Sunshinepreferences {


    public static final String PREF_CITY_NAME = "city_name";
    public static final String PREF_COORD_LAT = "coord_lat";
    public static final String PREF_COORD_LONG = "coord_long";
    private static final String DEFAULT_WEATHER_LOCATION = "94043,USA";
    private static final double[] DEFAULT_WEATHER_COORDINATES = {37.4284, 122.0724};

    private static final String DEFAULT_MAP_LOCATION =
            "1600 Amphitheatre Parkway, Mountain View, CA 94043";

    static public void setLocationDetails(Context c, String cityName, double lat, double lon) {
        /** This will be implemented in a future lesson **/
    }

    static public void setLocation(Context c, String locationSetting, double lat, double lon) {
        /** This will be implemented in a future lesson **/
    }
    static public void resetLocationCoordinates(Context c) {
        /** This will be implemented in a future lesson **/
    }

    public static String getPreferredWeatherLocation(Context context) {
        /** This will be implemented in a future lesson **/
        return getDefaultWeatherLocation();
    }

    public static boolean isMetric(Context context) {
        /** This will be implemented in a future lesson **/
        return true;
    }
    public static double[] getLocationCoordinates(Context context) {
        return getDefaultWeatherCoordinates();
    }

    public static boolean isLocationLatLonAvailable(Context context) {
        /** This will be implemented in a future lesson **/
        return false;
    }
    private static String getDefaultWeatherLocation() {
        /** This will be implemented in a future lesson **/
        return DEFAULT_WEATHER_LOCATION;
    }
    public static double[] getDefaultWeatherCoordinates() {
        /** This will be implemented in a future lesson **/
        return DEFAULT_WEATHER_COORDINATES;
    }

}