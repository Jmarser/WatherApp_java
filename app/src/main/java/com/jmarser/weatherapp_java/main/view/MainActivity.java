package com.jmarser.weatherapp_java.main.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayoutMediator;
import com.jmarser.weatherapp_java.R;
import com.jmarser.weatherapp_java.databinding.ActivityMainBinding;
import com.jmarser.weatherapp_java.di.appComponent.AppComponent;
import com.jmarser.weatherapp_java.di.appComponent.DaggerAppComponent;
import com.jmarser.weatherapp_java.di.appModule.AppModule;
import com.jmarser.weatherapp_java.di.appModule.SharedPreferencesModule;
import com.jmarser.weatherapp_java.main.adapters.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ActivityMainBinding binding;
    private List<Fragment> fragmentList;
    private FragmentPagerAdapter pagerAdapter;

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
        renderView();

        new TabLayoutMediator(binding.tabDots, binding.vpFragment, ((tab, position) -> tab.setText("Ciudad " + (position + 1)))).attach();

    }

    private void initInjection() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();

        appComponent.inject(this);
    }

    private void initListeners(){
        binding.btnLocation.setOnClickListener(view -> addNewFragment());
        binding.btnMenu.setOnClickListener(view -> deleteList());
        binding.svCity.setOnQueryTextListener(this);
    }

    private void showMessage(View view) {
        Toast.makeText(this, "Nuevo mensaje", Toast.LENGTH_SHORT).show();
    }

    private void addNewFragment(){
        if(fragmentList == null){
            fragmentList = new ArrayList<>();
        }
        WeatherFragment weatherFragment = new WeatherFragment().newInstance();
        fragmentList.add(weatherFragment);
        pagerAdapter.updateList(fragmentList);
        pagerAdapter.notifyDataSetChanged();
        renderView();
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
        // TODO Aquí buscamos la ciudad
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}