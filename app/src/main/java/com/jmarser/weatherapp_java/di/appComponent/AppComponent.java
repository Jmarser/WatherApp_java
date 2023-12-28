package com.jmarser.weatherapp_java.di.appComponent;

import com.jmarser.weatherapp_java.cityManagement.view.CityManagementActivity;
import com.jmarser.weatherapp_java.di.appModule.AppModule;
import com.jmarser.weatherapp_java.di.appModule.SharedPreferencesModule;
import com.jmarser.weatherapp_java.forecast.view.ForecastActivity;
import com.jmarser.weatherapp_java.main.view.MainActivity;
import com.jmarser.weatherapp_java.main.view.WeatherFragment;
import com.jmarser.weatherapp_java.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, SharedPreferencesModule.class})
public interface AppComponent {

    void inject(SplashActivity splashActivity);

    void inject(MainActivity mainActivity);

    void inject(WeatherFragment weatherFragment);

    void inject(ForecastActivity forecastActivity);

    void inject(CityManagementActivity cityManagementActivity);
}
