package com.jmarser.weatherapp_java.cityManagement.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jmarser.weatherapp_java.api.models.DatosCiudad;
import com.jmarser.weatherapp_java.cityManagement.adapters.CitiesAdapter;
import com.jmarser.weatherapp_java.databinding.ActivityCityManagementBinding;
import com.jmarser.weatherapp_java.di.appComponent.AppComponent;
import com.jmarser.weatherapp_java.di.appComponent.DaggerAppComponent;
import com.jmarser.weatherapp_java.di.appModule.AppModule;
import com.jmarser.weatherapp_java.di.appModule.SharedPreferencesModule;
import com.jmarser.weatherapp_java.utils.Constants;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

public class CityManagementActivity extends AppCompatActivity implements CitiesAdapter.OnClickCity{

    @Inject
    SharedPreferences sharedPreferences;
    private ActivityCityManagementBinding binding;
    private Set<DatosCiudad> ciudades = new HashSet<DatosCiudad>();
    private List<DatosCiudad> listadoCiudades;
    private Gson gson = new Gson();

    private CitiesAdapter citiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCityManagementBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initInjection();
        initListeners();

        // Recuperamos las ciudades que tengamos almacenadas
        String ciudadesRecuperadas = sharedPreferences.getString(Constants.CIUDADES_SHARED, null);

        if (ciudadesRecuperadas != null){
            // si tenemos datos los convertimos en un set
            Type type = new TypeToken<Set<DatosCiudad>>(){}.getType();
            ciudades = gson.fromJson(ciudadesRecuperadas, type);
            renderRecyclerCities();
        }else{
            showMessageEmptyCities();
        }
    }

    private void initInjection() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();
        appComponent.inject(this);
    }

    private void initListeners(){
        binding.btnBackCities.setOnClickListener(view -> onBackPressed());
    }

    private void renderRecyclerCities(){
        binding.rvCities.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        hashSetToList();
        citiesAdapter = new CitiesAdapter(listadoCiudades, this);
        if (citiesAdapter.getItemCount() > 0) {
            binding.rvCities.setAdapter(citiesAdapter);
            showRecyclerCities();
        }else{
            showMessageEmptyCities();
        }
    }

    private void showMessageEmptyCities() {
        binding.rvCities.setVisibility(View.GONE);
        binding.emptyCities.setVisibility(View.VISIBLE);
    }

    private void showRecyclerCities(){
        binding.rvCities.setVisibility(View.VISIBLE);
        binding.emptyCities.setVisibility(View.GONE);
    }

    private void hashSetToList(){
        listadoCiudades = new ArrayList<>(ciudades);
    }

    private void saveChangeOfCities(){
        Set<DatosCiudad> ciudadesSet = new HashSet<DatosCiudad>(listadoCiudades);
        String ciudadesJson = gson.toJson(ciudadesSet);
        sharedPreferences.edit().putString(Constants.CIUDADES_SHARED, ciudadesJson).apply();
    }

    @Override
    public void onSelectedCity(int position) {
        listadoCiudades.remove(position);
        citiesAdapter.notifyDataSetChanged();
        saveChangeOfCities();
    }
}