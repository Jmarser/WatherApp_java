package com.jmarser.weatherapp_java.main.interactor;

public interface WeatherInteractor {

    void getWeatherForCity(String city, OnGetWeatherCallBack callBack);

    void getWeatherForLocation(Long latitude, Long longitude, OnGetWeatherCallBack callBack);

    interface OnGetWeatherCallBack{
        void onSuccessGetWeater();
        void onErrorGetWeater();
        void errorServer();
    }
}
