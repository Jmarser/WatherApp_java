package com.jmarser.weatherapp_java.utils;

public class Constants {

    public static final String PREF_NAME = "prefs_weather";
    public static final String SERVER_URL_FULL = "https://api.openweathermap.org/data/3.0/";
    public static final String SERVER_URL_BASE = "https://api.openweathermap.org/data/2.5/";
    public static final String API_KEY_FULL = "9a3b67683abce78c360c80fc53e863b7";
    public static final String API_KEY_BASE = "9a3b67683abce78c360c80fc53e863b7";
    public static final String WEATHER_ACTUAL_FULL = "onecall?";
    public static final String WEATHER_ACTUAL_BASE = "weather?";

    public static final String CALL_WEATHER_BASE = SERVER_URL_BASE + "" + WEATHER_ACTUAL_BASE;
    public static final String CALL_WEATHER_FULL = SERVER_URL_FULL + "" + WEATHER_ACTUAL_FULL;

}
