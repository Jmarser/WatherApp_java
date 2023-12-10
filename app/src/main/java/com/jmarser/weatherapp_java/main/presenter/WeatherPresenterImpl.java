package com.jmarser.weatherapp_java.main.presenter;

import com.jmarser.weatherapp_java.main.interactor.WeatherInteractor;

import javax.inject.Inject;

public class WeatherPresenterImpl implements WeatherPresenter, WeatherInteractor.OnGetWeatherCallBack {

    @Inject
    public WeatherPresenterImpl() {
    }

    @Override
    public void getWeatherForCity(String city) {

    }

    @Override
    public void getWeatherForLocation(Long latitude, Long longitude) {

    }

    @Override
    public void onSuccessGetWeater() {

    }

    @Override
    public void onErrorGetWeater() {

    }

    @Override
    public void errorServer() {

    }
}
