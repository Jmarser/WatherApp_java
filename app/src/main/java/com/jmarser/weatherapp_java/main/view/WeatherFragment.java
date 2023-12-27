package com.jmarser.weatherapp_java.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.jmarser.weatherapp_java.api.models.Hourly;
import com.jmarser.weatherapp_java.api.models.WeatherBase;
import com.jmarser.weatherapp_java.api.models.Zona;
import com.jmarser.weatherapp_java.databinding.FragmentWeatherBinding;
import com.jmarser.weatherapp_java.di.appComponent.AppComponent;
import com.jmarser.weatherapp_java.di.appComponent.DaggerAppComponent;
import com.jmarser.weatherapp_java.di.appModule.AppModule;
import com.jmarser.weatherapp_java.di.appModule.SharedPreferencesModule;
import com.jmarser.weatherapp_java.forecast.view.ForecastActivity;
import com.jmarser.weatherapp_java.main.adapters.HourlyForecastAdapter;
import com.jmarser.weatherapp_java.main.presenter.WeatherPresenter;
import com.jmarser.weatherapp_java.utils.Constants;
import com.jmarser.weatherapp_java.utils.ConversionMethods;

import java.util.ArrayList;

import javax.inject.Inject;

public class WeatherFragment extends Fragment implements WeatherView {

    @Inject
    WeatherPresenter weatherPresenter;

    private FragmentWeatherBinding binding;

    private WeatherBase weatherBase;
    private Zona zona;
    private ArrayList<Hourly> forecastList;
    private HourlyForecastAdapter forecastAdapter;


    public WeatherFragment() {
        // Required empty public constructor
    }

    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWeatherBinding.inflate(inflater, container, false);

        initInjection();
        initListener();

        if (weatherBase != null) {
            weatherPresenter.getWeatherForLocation(weatherBase.getCoord().getLat(), weatherBase.getCoord().getLon());
            binding.pbPrevisiones.setVisibility(View.VISIBLE);
        }

        return binding.getRoot();
    }

    private void initInjection() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getContext(), this))
                .sharedPreferencesModule(new SharedPreferencesModule(getContext()))
                .build();

        appComponent.inject(this);
    }

    private void initListener() {
        binding.btnPronosticoDias.setOnClickListener(view -> goToForecast());
    }

    private void goToForecast() {
        Intent intent = new Intent(getContext(), ForecastActivity.class);
        intent.putExtra(Constants.LONGITUD, zona.getLongitud());
        intent.putExtra(Constants.LATITUD, zona.getLatitud());
        startActivity(intent);
    }

    private void showMessage() {
        Toast.makeText(getContext(), "Nuevo mensaje desde el fragment", Toast.LENGTH_SHORT).show();
    }

    /**
     * Métodos para establecer y obtener el objeto WeatherBase
     */
    public void setWeatherBase(WeatherBase weatherBase) {
        this.weatherBase = weatherBase;
    }

    public WeatherBase getWeatherBase() {
        return weatherBase;
    }

    @Override
    public void setWeatherFull(Zona zona) {
        if (zona != null && weatherBase != null) {
            this.zona = zona;
            renderUI();
        }
    }

    @Override
    public void onErrorMessageWeatherFull(String message) {
        // TODO: Gestionar el mensaje de error al obtener los datos del servidor
    }

    @Override
    public void onErrorWeatherFull() {
        // TODO: Gestionar el error al obtener los datos del servidor
    }

    @Override
    public void ErrorServer() {
        // TODO: Gestionar error con el servidor
    }

    private void renderUI() {

        binding.tvDateCurrent.setText(ConversionMethods.getCurrentDayAndTime(binding.getRoot().getContext()));

        // Parametros que vienen del objeto WeatherBase
        Glide.with(binding.getRoot().getContext()).load(ConversionMethods.getIcon(weatherBase.getWeather().get(0).getIcon())).into(binding.imgTempActual);
        binding.tvCiudad.setText(weatherBase.getName() + ", " + weatherBase.getSys().getCountry());
        binding.tvTempMinActual.setText(ConversionMethods.getTemperature(weatherBase.getMain().getTempMin()));
        binding.tvTempMaxActual.setText(ConversionMethods.getTemperature(weatherBase.getMain().getTempMax()));

        // Parámetros que vienen del objeto Zona
        binding.tvTempActual.setText(ConversionMethods.getTemperature(zona.getCurrently().getTemp()));
        binding.tvDescriptionTempActual.setText(zona.getCurrently().getWeather().get(0).getDescription());
        binding.tvHoraAmanecer.setText(ConversionMethods.getHora(zona.getCurrently().getSunrise()));
        binding.tvHoraAnochecer.setText(ConversionMethods.getHora(zona.getCurrently().getSunset()));
        binding.tvVelViento.setText(ConversionMethods.getWindVelocity(zona.getCurrently().getWindSpeed()));
        binding.tvDircViento.setText(ConversionMethods.getDegToCompass(zona.getCurrently().getWindDeg()).getDirection());
        binding.brujula.setImageResource(ConversionMethods.getDegToCompass(zona.getCurrently().getWindDeg()).getImgRessource());
        binding.tvSensacionTermica.setText(ConversionMethods.getTemperature(zona.getCurrently().getTemp()));
        binding.tvPresion.setText(ConversionMethods.getPressure(zona.getCurrently().getPressure()));
        binding.tvHumedad.setText(ConversionMethods.getHumidity(zona.getCurrently().getHumidity()));
        binding.tvUvi.setText(ConversionMethods.getUvi(zona.getCurrently().getUvi()));
        binding.tvVisibilidad.setText(ConversionMethods.getVisibility(zona.getCurrently().getVisibility()));

        forecastList = zona.getHourlyZona();
        renderRecyclerView();
    }

    private void renderRecyclerView() {
        binding.pbPrevisiones.setVisibility(View.INVISIBLE);
        if (forecastList != null && forecastList.size() > 0) {
            binding.rvPronosticoHoras.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            forecastAdapter = new HourlyForecastAdapter(forecastList);
            binding.rvPronosticoHoras.setAdapter(forecastAdapter);
            binding.pbPrevisiones.setVisibility(View.INVISIBLE);
            binding.rvPronosticoHoras.setVisibility(View.VISIBLE);
        } else {
            binding.pbPrevisiones.setVisibility(View.VISIBLE);
            binding.rvPronosticoHoras.setVisibility(View.INVISIBLE);
        }
    }
}