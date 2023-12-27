package com.jmarser.weatherapp_java.main.presenter;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jmarser.weatherapp_java.api.models.DatosCiudad;
import com.jmarser.weatherapp_java.api.models.WeatherBase;
import com.jmarser.weatherapp_java.main.interactor.MainInteractor;
import com.jmarser.weatherapp_java.main.view.MainView;
import com.jmarser.weatherapp_java.utils.Constants;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

public class MainPresenterImpl implements MainPresenter, MainInteractor.OnGetWeatherBaseCallBack {

    @Inject
    SharedPreferences sharedPreferences;

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
    public void getWeatherBaseForLocation(double lat, double lon) {
        mainInteractor.getWeatherBaseForLocation(lat, lon, this);
    }

    @Override
    public void onSuccessGetWeatherBase(WeatherBase weatherBase) {
        // Recuperamos las ciudades que tengamos almacenadas
        String ciudadesRecuperadas = sharedPreferences.getString(Constants.CIUDADES_SHARED, null);
        Gson gson = new Gson();
        Set<DatosCiudad> ciudades;

        if (ciudadesRecuperadas != null){
            // si tenemos datos los convertimos en un set
            Type type = new TypeToken<Set<DatosCiudad>>(){}.getType();
            ciudades = gson.fromJson(ciudadesRecuperadas, type);
        }else{
            ciudades = new HashSet<>();;
        }

        String nombreCiudad = weatherBase.getName() + ", " + weatherBase.getSys().getCountry();
        ciudades.add(new DatosCiudad(nombreCiudad, weatherBase.getCoord().getLat(), weatherBase.getCoord().getLon()));
        String json = gson.toJson(ciudades);
        Log.i("CIUDADES", json);
        sharedPreferences.edit().putString(Constants.CIUDADES_SHARED, json).apply();

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
