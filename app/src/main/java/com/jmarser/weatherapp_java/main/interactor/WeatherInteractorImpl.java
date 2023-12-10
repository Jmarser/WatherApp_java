package com.jmarser.weatherapp_java.main.interactor;

import com.jmarser.weatherapp_java.api.wsApi.WsApi;

import javax.inject.Inject;

public class WeatherInteractorImpl implements WeatherInteractor{

    @Inject
    WsApi wsApi;

    @Inject
    public WeatherInteractorImpl() {
    }

    @Override
    public void getWeatherForCity(String city, OnGetWeatherCallBack callBack) {

    }

    @Override
    public void getWeatherForLocation(Long latitude, Long longitude, OnGetWeatherCallBack callBack) {

    }
}
