package com.jmarser.weatherapp_java.di.appComponent;

import com.jmarser.weatherapp_java.di.appModule.AppModule;
import com.jmarser.weatherapp_java.di.appModule.SharedPreferencesModule;
import com.jmarser.weatherapp_java.main.view.MainActivity;
import com.jmarser.weatherapp_java.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, SharedPreferencesModule.class})
public interface AppComponent {

    void inject(SplashActivity splashActivity);
    void inject(MainActivity mainActivity);
}
