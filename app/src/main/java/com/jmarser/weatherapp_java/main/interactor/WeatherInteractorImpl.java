package com.jmarser.weatherapp_java.main.interactor;

import android.util.Log;

import com.jmarser.weatherapp_java.api.models.Zona;
import com.jmarser.weatherapp_java.api.wsApi.WsApi;
import com.jmarser.weatherapp_java.utils.Constants;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherInteractorImpl implements WeatherInteractor{

    @Inject
    WsApi wsApi;

    @Inject
    public WeatherInteractorImpl() {
    }


    @Override
    public void getWeatherFullForLocation(double latitude, double longitude, OnGetWeatherCallBack callBack) {
        wsApi.getWeatherFullForLocation(latitude, longitude, "metric", "es", Constants.API_KEY_FULL).enqueue(new Callback<Zona>() {
            @Override
            public void onResponse(Call<Zona> call, Response<Zona> response) {
                if (response.isSuccessful()){
                    callBack.onSuccessGetWeaterFull(response.body());
                }else{
                    callBack.onErrorGetWeaterFull(response.message());
                }
            }

            @Override
            public void onFailure(Call<Zona> call, Throwable t) {
                callBack.errorServer();
            }
        });
    }
}
