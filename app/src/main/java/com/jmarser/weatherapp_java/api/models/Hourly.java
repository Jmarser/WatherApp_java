package com.jmarser.weatherapp_java.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Hourly implements Parcelable{

    @SerializedName("dt") // Hora de los datos
    private float dt;

    @SerializedName("temp") // Temperatura en esa hora
    private float temp;

    @SerializedName("feels_like") // Sensación térmica
    private float feelsLike;

    @SerializedName("pressure") // Presión atmosférica
    private float pressure;

    @SerializedName("humidity") // Humedad
    private float humidity;

    @SerializedName("dew_point")
    private float dewPoint;

    @SerializedName("uvi") // Índice UV
    private float uvi;

    @SerializedName("clouds") // Nubosidad
    private float clouds;

    @SerializedName("visibility") // distacia de visibilidad
    private float visibility;

    @SerializedName("wind_speed") // Velocidad del viento
    private float windSpeed;

    @SerializedName("wind_deg") // Dirección del viento
    private float windDeg;

    @SerializedName("wind_gust") //Ráfagas de viento
    private float windGust;

    @SerializedName("weather")
    private ArrayList<Weather> weather = new ArrayList<Weather>();

    @SerializedName("pop")
    private float pop;


    /** Getters and setters **/

    public float getDt() {
        return dt;
    }

    public float getTemp() {
        return temp;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getDewPoint() {
        return dewPoint;
    }

    public float getUvi() {
        return uvi;
    }

    public float getClouds() {
        return clouds;
    }

    public float getVisibility() {
        return visibility;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public float getWindDeg() {
        return windDeg;
    }

    public float getWindGust() {
        return windGust;
    }

    public float getPop() {
        return pop;
    }

    public void setDt(float dt) {
        this.dt = dt;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public void setFeelsLike(float feelsLike) {
        this.feelsLike = feelsLike;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public void setDewPoint(float dewPoint) {
        this.dewPoint = dewPoint;
    }

    public void setUvi(float uvi) {
        this.uvi = uvi;
    }

    public void setClouds(float clouds) {
        this.clouds = clouds;
    }

    public void setVisibility(float visibility) {
        this.visibility = visibility;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDeg(float windDeg) {
        this.windDeg = windDeg;
    }

    public void setWindGust(float windGust) {
        this.windGust = windGust;
    }

    public void setPop(float pop) {
        this.pop = pop;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    /** Implementación Parcelable **/

    protected Hourly(Parcel in) {
        dt = in.readFloat();
        temp = in.readFloat();
        feelsLike = in.readFloat();
        pressure = in.readFloat();
        humidity = in.readFloat();
        dewPoint = in.readFloat();
        uvi = in.readFloat();
        clouds = in.readFloat();
        visibility = in.readFloat();
        windSpeed = in.readFloat();
        windDeg = in.readFloat();
        windGust = in.readFloat();
        weather = in.createTypedArrayList(Weather.CREATOR);
        pop = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(dt);
        dest.writeFloat(temp);
        dest.writeFloat(feelsLike);
        dest.writeFloat(pressure);
        dest.writeFloat(humidity);
        dest.writeFloat(dewPoint);
        dest.writeFloat(uvi);
        dest.writeFloat(clouds);
        dest.writeFloat(visibility);
        dest.writeFloat(windSpeed);
        dest.writeFloat(windDeg);
        dest.writeFloat(windGust);
        dest.writeTypedList(weather);
        dest.writeFloat(pop);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Hourly> CREATOR = new Creator<Hourly>() {
        @Override
        public Hourly createFromParcel(Parcel in) {
            return new Hourly(in);
        }

        @Override
        public Hourly[] newArray(int size) {
            return new Hourly[size];
        }
    };
}
