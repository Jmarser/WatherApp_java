package com.jmarser.weatherapp_java.main.view;

import com.jmarser.weatherapp_java.api.models.WeatherBase;

public interface MainView {

    void setWeatherBase(WeatherBase weatherBase);

    void onErrorMessageWeatherBase(String message);

    void onErrorWeather();

    void ErrorServer();
}
