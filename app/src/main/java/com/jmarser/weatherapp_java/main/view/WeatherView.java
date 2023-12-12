package com.jmarser.weatherapp_java.main.view;

import com.jmarser.weatherapp_java.api.models.Zona;

public interface WeatherView {

    void setWeatherFull(Zona zona);

    void onErrorMessageWeatherFull(String message);

    void onErrorWeatherFull();

    void ErrorServer();
}
