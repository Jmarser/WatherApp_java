package com.jmarser.weatherapp_java.di.appModule;

import android.content.Context;

import androidx.annotation.NonNull;

import com.jmarser.weatherapp_java.main.MainActivity;
import com.jmarser.weatherapp_java.splash.SplashActivity;

import dagger.Module;
import dagger.Provides;

@Module(includes = {SharedPreferencesModule.class, ConnectionModule.class})
public class AppModule {

    /* Propiedades */
    private Context context;
    private SplashActivity splashActivity;
    private MainActivity mainActivity;



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

    /* Views */
    @NonNull
    @Provides
    public SplashActivity splashActivity() {
        if (splashActivity != null){
            return splashActivity;
        }
        return null;
    }




    /* Presenters */




    /* Interactors */




}
