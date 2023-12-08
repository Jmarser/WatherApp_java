package com.jmarser.weatherapp_java.main.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jmarser.weatherapp_java.R;
import com.jmarser.weatherapp_java.databinding.FragmentWeatherBinding;

public class WeatherFragment extends Fragment {

    private FragmentWeatherBinding binding;

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

        initListener();

        return binding.getRoot();

    }

    private void initListener() {
        binding.btnPronosticoDias.setOnClickListener(view -> showMessage());
    }

    private void showMessage() {
        Toast.makeText(getContext(), "Nuevo mensaje desde el fragment", Toast.LENGTH_SHORT).show();
    }
}