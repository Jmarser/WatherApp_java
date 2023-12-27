package com.jmarser.weatherapp_java.main.interactor;

import com.jmarser.weatherapp_java.api.models.Zona;

public interface WeatherInteractor {

    void getWeatherFullForLocation(double latitude, double longitude, OnGetWeatherCallBack callBack);

    interface OnGetWeatherCallBack{
        void onSuccessGetWeaterFull(Zona zona);
        void onErrorGetWeaterFull(String message);
        void errorServer();
    }
}
