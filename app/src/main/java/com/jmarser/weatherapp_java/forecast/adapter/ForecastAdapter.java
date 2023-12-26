package com.jmarser.weatherapp_java.forecast.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jmarser.weatherapp_java.api.models.Daily;
import com.jmarser.weatherapp_java.databinding.ItemDiasRowBinding;
import com.jmarser.weatherapp_java.utils.ConversionMethods;

import java.util.ArrayList;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>{

    private List<Daily> dailyList;

    public ForecastAdapter(List<Daily> dailyList) {
        this.dailyList = dailyList;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemDiasRowBinding binding = ItemDiasRowBinding.inflate(inflater, parent, false);
        return new ForecastViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        holder.renderRow(dailyList.get(position));
    }

    @Override
    public int getItemCount() {
        return dailyList != null ? dailyList.size() : 0;
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder{
        private ItemDiasRowBinding binding;


        public ForecastViewHolder(@NonNull ItemDiasRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void renderRow(Daily daily){
            String day = binding.getRoot().getContext().getString(ConversionMethods.getDayName((long) daily.getDt()));
            binding.tvDiaPronostico.setText(day +", " + ConversionMethods.getHora(daily.getDt()));
            binding.tvSummaryPronostico.setText(daily.getSummary());
            binding.tvTempMin.setText(ConversionMethods.getTemperature(daily.getTemp().getMin()));
            binding.tvTempMax.setText(ConversionMethods.getTemperature(daily.getTemp().getMax()));
            binding.tvTempDescriptionForecast.setText(daily.getWeather().get(0).getDescription());
            Glide.with(binding.getRoot().getContext()).load(ConversionMethods.getIcon(daily.getWeather().get(0).getIcon())).into(binding.imgIconForecast);
        }
    }
}
