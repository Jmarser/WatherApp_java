package com.jmarser.weatherapp_java.main.presenter;

import com.jmarser.weatherapp_java.api.models.Zona;
import com.jmarser.weatherapp_java.main.interactor.WeatherInteractor;
import com.jmarser.weatherapp_java.main.view.WeatherView;

import javax.inject.Inject;

public class WeatherPresenterImpl implements WeatherPresenter, WeatherInteractor.OnGetWeatherCallBack {

    @Inject
    WeatherView weatherView;

    @Inject
    WeatherInteractor weatherInteractor;

    @Inject
    public WeatherPresenterImpl() {
    }

    @Override
    public void getWeatherForLocation(long latitude, long longitude) {
        weatherInteractor.getWeatherFullForLocation(latitude, longitude, this);
    }

    @Override
    public void onSuccessGetWeaterFull(Zona zona) {
        weatherView.setWeatherFull(zona);
    }

    @Override
    public void onErrorGetWeaterFull(String message) {
        weatherView.onErrorMessageWeatherFull(message);
    }

    @Override
    public void errorServer() {
        weatherView.ErrorServer();
    }
}
