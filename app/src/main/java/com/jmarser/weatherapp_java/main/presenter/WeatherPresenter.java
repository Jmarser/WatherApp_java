package com.jmarser.weatherapp_java.main.presenter;

public interface WeatherPresenter {

    void getWeatherForCity(String city);

    void getWeatherForLocation(Long latitude, Long longitude);
}
