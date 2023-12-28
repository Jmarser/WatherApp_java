package com.jmarser.weatherapp_java.cityManagement.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmarser.weatherapp_java.api.models.DatosCiudad;
import com.jmarser.weatherapp_java.databinding.ItemCityRowBinding;

import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesHolder> {

    private List<DatosCiudad> ciudades;
    OnClickCity onClickCity;

    public CitiesAdapter(List<DatosCiudad> ciudades, OnClickCity onClickCity) {
        this.ciudades = ciudades;
        this.onClickCity = onClickCity;
    }

    @NonNull
    @Override
    public CitiesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemCityRowBinding binding = ItemCityRowBinding.inflate(layoutInflater, parent, false);
        return new CitiesHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesHolder holder, int position) {
        holder.render(ciudades.get(position));
        holder.binding.btnDeleteCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCity.onSelectedCity(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ciudades != null ? ciudades.size() : 0;
    }

    static class CitiesHolder extends RecyclerView.ViewHolder{

        private ItemCityRowBinding binding;

        public CitiesHolder(@NonNull ItemCityRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void render(DatosCiudad ciudad){
            binding.tvCityRow.setText(ciudad.getNombre());
            binding.tvLatitudRow.setText(ciudad.getLatitud() + "º");
            binding.tvLongitudRow.setText(ciudad.getLongitud() + "º");
        }
    }

    public interface OnClickCity {
        void onSelectedCity(int position);
    }
}
