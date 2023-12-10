package com.jmarser.weatherapp_java.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Zona implements Parcelable {

    @SerializedName("lat")
    private double latitud;
    @SerializedName("lon")
    private double longitud;
    @SerializedName("timezone") // Zona horaria
    private String tiempo;
    @SerializedName("current")
    private Current currently;
    @SerializedName("hourly")
    private ArrayList<Hourly> hourlies = new ArrayList<>();

    @SerializedName("daily")
    private ArrayList<Daily> dailies = new ArrayList<>();

    @SerializedName("alerts")
    private ArrayList<Alert> alerts = new ArrayList<>();

    public Zona(double latitudZona, double longitudZona, String zonaTiempo, Current currentlyZona, ArrayList<Hourly> hourlyZona, ArrayList<Daily> dailyZona, ArrayList<Alert> alerts) {
        this.latitud = latitudZona;
        this.longitud = longitudZona;
        this.tiempo = zonaTiempo;
        this.currently = currentlyZona;
        this.hourlies = hourlyZona;
        this.dailies = dailyZona;
        this.alerts = alerts;
    }

    /** Getters and setters **/

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public Current getCurrently() {
        return currently;
    }

    public void setCurrently(Current currently) {
        this.currently = currently;
    }

    public ArrayList<Hourly> getHourlyZona() {
        return hourlies;
    }

    public void setHourlyZona(ArrayList<Hourly> hourlyZona) {
        this.hourlies = hourlyZona;
    }

    public ArrayList<Daily> getDailyZona() {
        return dailies;
    }

    public void setDailyZona(ArrayList<Daily> dailyZona) {
        this.dailies = dailyZona;
    }

    public ArrayList<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(ArrayList<Alert> alerts) {
        this.alerts = alerts;
    }

    /** Implementación Parcelable **/

    protected Zona(Parcel in) {
        latitud = in.readDouble();
        longitud = in.readDouble();
        tiempo = in.readString();
        currently = in.readParcelable(Current.class.getClassLoader());
        hourlies = in.createTypedArrayList(Hourly.CREATOR);
        dailies = in.createTypedArrayList(Daily.CREATOR);
        alerts = in.createTypedArrayList(Alert.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitud);
        dest.writeDouble(longitud);
        dest.writeString(tiempo);
        dest.writeParcelable(currently, flags);
        dest.writeList(hourlies);
        dest.writeList(dailies);
        dest.writeList(alerts);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Zona> CREATOR = new Creator<Zona>() {
        @Override
        public Zona createFromParcel(Parcel in) {
            return new Zona(in);
        }

        @Override
        public Zona[] newArray(int size) {
            return new Zona[size];
        }
    };
}
