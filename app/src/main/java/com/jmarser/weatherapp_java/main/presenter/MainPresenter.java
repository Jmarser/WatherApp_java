package com.jmarser.weatherapp_java.main.presenter;

public interface MainPresenter {

    void getWeatherBaseForCity(String city);

    void getWeatherBaseForLocation(float lat, float lon);
}
