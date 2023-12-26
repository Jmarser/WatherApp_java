package com.jmarser.weatherapp_java.main.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jmarser.weatherapp_java.R;
import com.jmarser.weatherapp_java.api.models.DatosCiudad;
import com.jmarser.weatherapp_java.api.models.WeatherBase;
import com.jmarser.weatherapp_java.databinding.ActivityMainBinding;
import com.jmarser.weatherapp_java.di.appComponent.AppComponent;
import com.jmarser.weatherapp_java.di.appComponent.DaggerAppComponent;
import com.jmarser.weatherapp_java.di.appModule.AppModule;
import com.jmarser.weatherapp_java.di.appModule.SharedPreferencesModule;
import com.jmarser.weatherapp_java.main.adapters.FragmentPagerAdapter;
import com.jmarser.weatherapp_java.main.presenter.MainPresenter;
import com.jmarser.weatherapp_java.utils.Constants;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, MainView {

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    MainPresenter presenter;

    private ActivityMainBinding binding;
    private List<Fragment> fragmentList;
    private FragmentPagerAdapter pagerAdapter;
    private static final int PERMISSION_LOCATION = 123;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Set<DatosCiudad> ciudades = new HashSet<DatosCiudad>();
    private Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initInjection();
        initListeners();

        //fragmentList = new ArrayList<Fragment>();
        pagerAdapter = new FragmentPagerAdapter(this, fragmentList);
        binding.vpFragment.setAdapter(pagerAdapter);

        // Recuperamos las ciudades que tengamos almacenadas
        String ciudadesRecuperadas = sharedPreferences.getString(Constants.CIUDADES_SHARED, null);

        if (ciudadesRecuperadas != null){
            // si tenemos datos los convertimos en un set
            Type type = new TypeToken<Set<DatosCiudad>>(){}.getType();
            ciudades = gson.fromJson(ciudadesRecuperadas, type);
            for (DatosCiudad ciudad : ciudades) {
                presenter.getWeatherBaseForCity(ciudad.getNombre());
            }
        }else{
            Toast.makeText(this, "No hay ciudades almacenadas", Toast.LENGTH_LONG).show();
        }
        renderView();

    }

    private void initInjection() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();

        appComponent.inject(this);
    }

    private void initListeners() {
        binding.btnLocation.setOnClickListener(view -> addNewFragment());
        binding.btnMenu.setOnClickListener(view -> deleteList());
        binding.svCity.setOnQueryTextListener(this);
    }

    private void showMessage(View view) {
        Toast.makeText(this, "Nuevo mensaje", Toast.LENGTH_SHORT).show();
    }

    private void addNewFragment() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        /* Comprobamos si la app tiene concedida los permisos para acceder al GPS del terminal*/
        if (checkLocationPermission()) {
            //Permiso concedido
            obtenerUbicacion();
        } else {
            // Permiso no concedido
            requestLocationPermission();
        }
    }

    private void obtenerUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null){
                            Log.i("LOCATION", "Latitud: " + location.getLatitude() + " / Longitud: " + location.getLongitude());
                            // Si tenemos la localización solicitamos la información al servidor
                            presenter.getWeatherBaseForLocation((long) location.getLatitude(), (long) location.getLongitude());
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("LOCATION", "No hay localización");
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.error_getlocation), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void deleteList(){
        fragmentList.clear();
        pagerAdapter.deleteList();
        renderView();
    }

    private void renderView(){
        if(pagerAdapter.getItemCount() == 0){
            binding.tvCityEmpty.setVisibility(View.VISIBLE);
        }else{
            binding.tvCityEmpty.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if(!TextUtils.isEmpty(query)){
            presenter.getWeatherBaseForCity(query);
            hideKeyboard();
            binding.svCity.setQuery("", false);
            binding.svCity.clearFocus();
        }else{
            Toast.makeText(this, "Debe indicar una ciudad.", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void setWeatherBase(WeatherBase weatherBase) {

        if(fragmentList == null){
            fragmentList = new ArrayList<>();
        }
        WeatherFragment weatherFragment = WeatherFragment.newInstance();
        fragmentList.add(weatherFragment);
        weatherFragment.setWeatherBase(weatherBase);
        pagerAdapter.updateList(fragmentList);
        pagerAdapter.notifyDataSetChanged();
        renderView();

        new TabLayoutMediator(binding.tabDots, binding.vpFragment, (((tab, position) -> {
            WeatherFragment weatherFragment1 = (WeatherFragment) fragmentList.get(position);
            if (weatherFragment1 != null && weatherFragment1.getWeatherBase() != null){
                String nameCity = weatherFragment1.getWeatherBase().getName();
                tab.setText(nameCity);
            }else{
                tab.setText("Ciudad " + (position + 1));
            }
        }))).attach();

    }

    @Override
    public void onErrorMessageWeatherBase(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorWeather() {
        // TODO: Gestionar el error al obtener los datos del servidor
    }

    @Override
    public void ErrorServer() {
        // TODO: Gestionar error con el servidor.
    }

    private boolean checkLocationPermission(){
        //Verificamos si tenemos el permiso de ubicación
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission(){
        // Solicitamos el permiso de ubicación al usuario
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Manejamos la respuesta del usuario sobre los permisos
        if (requestCode == PERMISSION_LOCATION){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // Permiso concedido por el usuario
                Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();
                obtenerUbicacion();
            }else{
                // Permiso denegado, mostrar un mensaje al usuario y proporcionar la opción de ir a la configuración del sistema para conceder los permisos manualmente
                showPermissionDeniedDialog();
            }
        }
    }

    private void showPermissionDeniedDialog(){
        // Dialogo para que el usuario acceda a los ajustes para conceder los permisos de acceso al GPS
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.permision_location_message))
                .setPositiveButton(getResources().getString(R.string.ajustes), (dialog, which) -> openAppSettings())
                .setNegativeButton(getResources().getString(R.string.cancel), (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void openAppSettings(){
        // Abrimos los ajustes del sistema para que el usuario pueda conceder los permisos manualmente
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(android.net.Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            View view = getCurrentFocus();
            if (view != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}