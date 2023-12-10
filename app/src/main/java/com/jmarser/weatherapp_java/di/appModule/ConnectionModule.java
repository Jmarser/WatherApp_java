package com.jmarser.weatherapp_java.di.appModule;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jmarser.weatherapp_java.api.wsApi.WsApi;
import com.jmarser.weatherapp_java.utils.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * módulo con el que obtenemos una instancia única en toda la app con la que podremos hacer peticiones
 * al api por medio de retrofit.
 * Podemos usarlo con una dirección por defecto o pasandole la dirección por parámetro.
 * */
@Module
public class ConnectionModule {


    private String urlBase;

    public ConnectionModule() {
        this.urlBase = Constants.SERVER_URL_BASE;
    }

    public ConnectionModule(String urlBase) {
        if(!urlBase.isEmpty()) {
            this.urlBase = urlBase;
        }else{
            this.urlBase = Constants.SERVER_URL_BASE;
        }
    }

    @Provides
    @Singleton
    public WsApi providesApi(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.baseUrl(Constantes.SERVER_URL)
                .baseUrl(this.urlBase)
                .client(okHttpClient)
                .build();

        return retrofit.create(WsApi.class);
    }
}
