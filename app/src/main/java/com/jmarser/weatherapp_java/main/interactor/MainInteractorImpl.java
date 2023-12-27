package com.jmarser.weatherapp_java.main.interactor;

import com.jmarser.weatherapp_java.api.models.WeatherBase;
import com.jmarser.weatherapp_java.api.models.Zona;
import com.jmarser.weatherapp_java.api.wsApi.WsApi;
import com.jmarser.weatherapp_java.utils.Constants;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInteractorImpl implements MainInteractor{

    @Inject
    WsApi wsApi;

    @Inject
    public MainInteractorImpl() {
    }


    @Override
    public void getWeatherBaseForCity(String city, OnGetWeatherBaseCallBack callBack) {
        wsApi.getWeatherBaseForCity(city, "metric", "es", Constants.API_KEY_BASE).enqueue(new Callback<WeatherBase>() {
            @Override
            public void onResponse(Call<WeatherBase> call, Response<WeatherBase> response) {
                if(response.isSuccessful()) {
                    callBack.onSuccessGetWeatherBase(response.body());
                }else{
                    callBack.onErrorGetWeatherBase(response.message());
                }
            }

            @Override
            public void onFailure(Call<WeatherBase> call, Throwable t) {
                callBack.ErrorServer();
            }
        });
    }

    @Override
    public void getWeatherBaseForLocation(double latitude, double longitude, OnGetWeatherBaseCallBack callBack) {
        wsApi.getWeatherBaseForLocation(latitude, longitude, "metric", "es", Constants.API_KEY_BASE).enqueue(new Callback<WeatherBase>() {
            @Override
            public void onResponse(Call<WeatherBase> call, Response<WeatherBase> response) {
                if(response.isSuccessful()){
                    callBack.onSuccessGetWeatherBase(response.body());
                }else{
                    callBack.onErrorGetWeatherBase(response.message());
                }
            }

            @Override
            public void onFailure(Call<WeatherBase> call, Throwable t) {
                callBack.ErrorServer();
            }
        });
    }
}
