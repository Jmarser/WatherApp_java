package com.jmarser.weatherapp_java.forecast.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jmarser.weatherapp_java.api.models.Daily;
import com.jmarser.weatherapp_java.databinding.ActivityForecastBinding;
import com.jmarser.weatherapp_java.di.appComponent.AppComponent;
import com.jmarser.weatherapp_java.di.appComponent.DaggerAppComponent;
import com.jmarser.weatherapp_java.di.appModule.AppModule;
import com.jmarser.weatherapp_java.di.appModule.SharedPreferencesModule;
import com.jmarser.weatherapp_java.forecast.adapter.ForecastAdapter;
import com.jmarser.weatherapp_java.forecast.presenter.ForecastPresenter;
import com.jmarser.weatherapp_java.utils.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class ForecastActivity extends AppCompatActivity implements ForecastView{

    @Inject
    ForecastPresenter presenter;

    private ActivityForecastBinding binding;

    private List<Daily> forecast;

    private ForecastAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForecastBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initInjection();
        initListeners();

        Long longitud = getIntent().getLongExtra(Constants.LONGITUD, 0);
        Long latitud = getIntent().getLongExtra(Constants.LATITUD, 0);
        presenter.getForecast(latitud, longitud);

        renderRecyclerForecast();

    }

    private void initInjection(){
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();

        appComponent.inject(this);
    }

    private void initListeners(){
        binding.imgBack.setOnClickListener(view -> onBackPressed());
    }

    private void renderRecyclerForecast() {
        binding.rvForecast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new ForecastAdapter(forecast);
        binding.rvForecast.setAdapter(adapter);
    }

    @Override
    public void successGetForecast(List<Daily> forecast) {
        if(forecast != null){
            this.forecast = forecast;
            renderRecyclerForecast();
        }
    }

    @Override
    public void errorGetForecast(String message) {
        showMessage(message);
    }

    @Override
    public void errorServer(String message) {
        showMessage(message);
    }

    private void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}