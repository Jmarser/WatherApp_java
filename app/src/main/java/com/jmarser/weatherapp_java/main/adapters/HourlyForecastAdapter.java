package com.jmarser.weatherapp_java.main.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jmarser.weatherapp_java.api.models.Hourly;
import com.jmarser.weatherapp_java.databinding.ItemHorasRowBinding;
import com.jmarser.weatherapp_java.utils.ConversionMethods;

import java.util.List;

public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.HourlyForecastHolder>{

    private List<Hourly> forecastList;

    public HourlyForecastAdapter(List<Hourly> forecastList) {
        this.forecastList = forecastList;
    }

    @NonNull
    @Override
    public HourlyForecastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemHorasRowBinding binding = ItemHorasRowBinding.inflate(layoutInflater, parent, false);
        return new HourlyForecastHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyForecastHolder holder, int position) {
        holder.render(forecastList.get(position));
    }

    @Override
    public int getItemCount() {
        return forecastList != null ? forecastList.size() : 0;
    }


    static class HourlyForecastHolder extends RecyclerView.ViewHolder{

        private ItemHorasRowBinding binding;

        public HourlyForecastHolder(@NonNull ItemHorasRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void render(Hourly hourly){
            binding.tvHoraPronostic.setText(ConversionMethods.getHora(hourly.getDt()));
            binding.tvTempPronostic.setText(ConversionMethods.getTemperature(hourly.getTemp()));
            Glide.with(binding.getRoot().getContext()).load(ConversionMethods.getIcon(hourly.getWeather().get(0).getIcon())).into(binding.imgHoraPronostic);
        }
    }
}
