package com.jmarser.weatherapp_java.forecast.view;

import com.jmarser.weatherapp_java.api.models.Daily;

import java.util.List;

public interface ForecastView {

    void successGetForecast(List<Daily> forecast);

    void errorGetForecast(String message);

    void errorServer(String message);
}
