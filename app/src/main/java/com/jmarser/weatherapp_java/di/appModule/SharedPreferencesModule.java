package com.jmarser.weatherapp_java.di.appModule;

import android.content.Context;
import android.content.SharedPreferences;

import com.jmarser.weatherapp_java.utils.Constants;

import javax.inject.Singleton;

import at.favre.lib.armadillo.Armadillo;
import at.favre.lib.armadillo.PBKDF2KeyStretcher;
import dagger.Module;
import dagger.Provides;

/**
 * Módulo con el que obtenemos una instancia única de sharedpreferences en toda la app y los datos
 * se almacenan encriptados.
 * */
@Module
public class SharedPreferencesModule {

    private Context context;

    public SharedPreferencesModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(){

        SharedPreferences preferences = Armadillo.create(context, Constants.PREF_NAME)
                .encryptionFingerprint(context)
                .keyStretchingFunction(new PBKDF2KeyStretcher())
                .build();

        return preferences;
    }
}
