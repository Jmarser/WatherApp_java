package com.jmarser.weatherapp_java.main.presenter;

import com.jmarser.weatherapp_java.api.models.WeatherBase;
import com.jmarser.weatherapp_java.main.interactor.MainInteractor;
import com.jmarser.weatherapp_java.main.view.MainView;

import javax.inject.Inject;

public class MainPresenterImpl implements MainPresenter, MainInteractor.OnGetWeatherBaseCallBack {

    @Inject
    MainView mainView;

    @Inject
    MainInteractor mainInteractor;

    @Inject
    public MainPresenterImpl() {
    }


    @Override
    public void getWeatherBaseForCity(String city) {
        mainInteractor.getWeatherBaseForCity(city, this);
    }

    @Override
    public void getWeatherBaseForLocation(Long lat, Long lon) {
        mainInteractor.getWeatherBaseForLocation(lat, lon, this);
    }

    @Override
    public void onSuccessGetWeatherBase(WeatherBase weatherBase) {
        mainView.setWeatherBase(weatherBase);
    }

    @Override
    public void onErrorGetWeatherBase(String message) {
        mainView.onErrorMessageWeatherBase(message);
    }

    @Override
    public void ErrorServer() {
        mainView.ErrorServer();
    }

}
