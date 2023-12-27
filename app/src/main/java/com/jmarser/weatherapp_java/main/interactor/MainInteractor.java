package com.jmarser.weatherapp_java.main.interactor;

import com.jmarser.weatherapp_java.api.models.WeatherBase;

public interface MainInteractor {

    void getWeatherBaseForCity(String city, OnGetWeatherBaseCallBack callBack);

    void getWeatherBaseForLocation(double latitude, double longitude, OnGetWeatherBaseCallBack callBack);

    interface OnGetWeatherBaseCallBack{
        void onSuccessGetWeatherBase(WeatherBase weatherBase);
        void onErrorGetWeatherBase(String message);
        void ErrorServer();
    }
}
