package com.jmarser.weatherapp_java.forecast.interactor;

import com.jmarser.weatherapp_java.api.models.Daily;
import com.jmarser.weatherapp_java.api.models.Zona;
import com.jmarser.weatherapp_java.api.wsApi.WsApi;
import com.jmarser.weatherapp_java.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastInteractorImpl implements ForecastInteractor {

    @Inject
    WsApi wsApi;

    @Inject
    public ForecastInteractorImpl() {
    }

    @Override
    public void getForecast(Long lat, Long lon, OnGetForecast callBack) {
        wsApi.getForecast(lat, lon, "metric", "es", Constants.API_KEY_FULL).enqueue(new Callback<Zona>() {
            @Override
            public void onResponse(Call<Zona> call, Response<Zona> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccessGetForecast(response.body().getDailyZona());
                }else{
                    callBack.onErrorGetForecast(response.message());
                }
            }

            @Override
            public void onFailure(Call<Zona> call, Throwable t) {
                callBack.onErrorServer(t.getMessage() + t.getStackTrace()[0]);
            }
        });
    }
}
