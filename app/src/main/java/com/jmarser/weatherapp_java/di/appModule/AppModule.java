package com.jmarser.weatherapp_java.di.appModule;

import android.content.Context;

import androidx.annotation.NonNull;

import com.jmarser.weatherapp_java.main.interactor.MainInteractor;
import com.jmarser.weatherapp_java.main.interactor.MainInteractorImpl;
import com.jmarser.weatherapp_java.main.interactor.WeatherInteractor;
import com.jmarser.weatherapp_java.main.interactor.WeatherInteractorImpl;
import com.jmarser.weatherapp_java.main.presenter.MainPresenter;
import com.jmarser.weatherapp_java.main.presenter.MainPresenterImpl;
import com.jmarser.weatherapp_java.main.presenter.WeatherPresenter;
import com.jmarser.weatherapp_java.main.presenter.WeatherPresenterImpl;
import com.jmarser.weatherapp_java.main.view.MainActivity;
import com.jmarser.weatherapp_java.main.view.MainView;
import com.jmarser.weatherapp_java.main.view.WeatherFragment;
import com.jmarser.weatherapp_java.main.view.WeatherView;
import com.jmarser.weatherapp_java.splash.SplashActivity;

import dagger.Module;
import dagger.Provides;

@Module(includes = {SharedPreferencesModule.class, ConnectionModule.class})
public class AppModule {

    /* Propiedades */
    private Context context;
    private SplashActivity splashActivity;
    private MainActivity mainActivity;
    private WeatherFragment weatherFragment;



    /* Constructores */

    public AppModule() {
    }

    public AppModule(Context context, SplashActivity splashActivity) {
        this.context = context;
        this.splashActivity = splashActivity;
    }

    public AppModule(Context context, MainActivity mainActivity) {
        this.context = context;
        this.mainActivity = mainActivity;
    }

    public AppModule(Context context, WeatherFragment weatherFragment) {
        this.context = context;
        this.weatherFragment = weatherFragment;
    }

    /* Views */
    @NonNull
    @Provides
    public SplashActivity splashActivity() {
        if (splashActivity != null){
            return splashActivity;
        }
        return null;
    }

    @NonNull
    @Provides
    public MainView mainActivity() {
        if (mainActivity != null){
            return mainActivity;
        }
        return null;
    }

    @NonNull
    @Provides
    public WeatherView weatherFragment() {
        if(weatherFragment != null){
            return weatherFragment;
        }
        return null;
    }


    /* Presenters */

    @Provides
    public MainPresenter providerMainPresenter(MainPresenterImpl presenter){
        return presenter;
    }

    @Provides
    public WeatherPresenter providerWeatherPresent(WeatherPresenterImpl presenter){
        return presenter;
    }



    /* Interactors */

    @Provides
    public MainInteractor providerMainInteractor(MainInteractorImpl interactor){
    return interactor;
    }
    @Provides
    public WeatherInteractor providerWeatherInteractor(WeatherInteractorImpl interactor){
        return interactor;
    }



}
