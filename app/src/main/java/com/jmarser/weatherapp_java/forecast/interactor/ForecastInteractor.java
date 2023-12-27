package com.jmarser.weatherapp_java.forecast.interactor;

import com.jmarser.weatherapp_java.api.models.Daily;

import java.util.List;

public interface ForecastInteractor {

    void getForecast(double lat, double lon, OnGetForecast callBack);

    interface OnGetForecast{
        void onSuccessGetForecast(List<Daily> forecast);
        void onErrorGetForecast(String message);
        void onErrorServer(String message);
    }
}
