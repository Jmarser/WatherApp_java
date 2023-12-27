package com.jmarser.weatherapp_java.forecast.presenter;

import com.jmarser.weatherapp_java.api.models.Daily;
import com.jmarser.weatherapp_java.forecast.interactor.ForecastInteractor;
import com.jmarser.weatherapp_java.forecast.view.ForecastActivity;
import com.jmarser.weatherapp_java.forecast.view.ForecastView;

import java.util.List;

import javax.inject.Inject;

public class ForecasrPresenterImpl implements ForecastPresenter, ForecastInteractor.OnGetForecast{

    @Inject
    ForecastView view;
    @Inject
    ForecastInteractor interactor;

    @Inject
    public ForecasrPresenterImpl() {
    }

    @Override
    public void getForecast(double lat, double lon) {
        interactor.getForecast(lat, lon, this);
    }

    @Override
    public void onSuccessGetForecast(List<Daily> forecast) {
        view.successGetForecast(forecast);
    }

    @Override
    public void onErrorGetForecast(String message) {
        view.errorGetForecast(message);
    }

    @Override
    public void onErrorServer(String message) {
        view.errorServer(message);
    }
}
