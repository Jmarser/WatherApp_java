package com.jmarser.weatherapp_java.api.wsApi;

import com.jmarser.weatherapp_java.api.models.WeatherBase;
import com.jmarser.weatherapp_java.api.models.Zona;
import com.jmarser.weatherapp_java.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface que contiene todas las llamadas al api
 * */
public interface WsApi {

    @GET(Constants.CALL_WEATHER_BASE)
    Call<WeatherBase> getWeatherBaseForCity(@Query("q")String city, @Query("units") String units, @Query("lang") String lang, @Query("appid") String api_key);

    @GET(Constants.CALL_WEATHER_BASE)
    Call<WeatherBase> getWeatherBaseForLocation(@Query("lat")double lat, @Query("lon") double lon, @Query("units") String units, @Query("lang") String lang, @Query("appid") String api_key);

    @GET(Constants.CALL_WEATHER_FULL)
    Call<Zona> getWeatherFullForLocation(@Query("lat")double lat, @Query("lon") double lon, @Query("units") String units, @Query("lang") String lang, @Query("appid") String api_key);
}
