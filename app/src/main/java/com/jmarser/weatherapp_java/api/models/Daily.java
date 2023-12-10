package com.jmarser.weatherapp_java.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Daily implements Parcelable {

    @SerializedName("dt") // Hora de los datos
    private float dt;

    @SerializedName("sunrise") // Hora de salida del sol
    private float sunrise;

    @SerializedName("sunset") // Hora de la puesta del sol
    private float sunset;

    @SerializedName("moonrise") // Hora de la salida de la luna
    private float moonrise;

    @SerializedName("moonset") // Hora de la puesta de la luna
    private float moonset;

    @SerializedName("moon_phase") // fase lunar
    private float moonPhase;

    @SerializedName("summary") // Descripción del clima
    private String summary;

    @SerializedName("temp")
    private Temp temp;

    @SerializedName("feels_like")
    private FeelsLike feelsLike;

    @SerializedName("pressure") // Presión atmosférica
    private float pressure;

    @SerializedName("humidity") // Humedad
    private float humidity;

    @SerializedName("dew_point")
    private float dewPoint;

    @SerializedName("wind_speed") // Velocidad del viento
    private float windSpeed;

    @SerializedName("wind_deg") // dirección del viento
    private float windDeg;

    @SerializedName("wind_gust") // Rafagás de viento
    private float windGust;

    @SerializedName("weather")
    private ArrayList<Weather> weather = new ArrayList<>();

    @SerializedName("clouds") // nubosidad
    private float clouds;

    @SerializedName("pop")
    private float pop;

    @SerializedName("rain") // Volumen de precipitación
    private float rain;

    @SerializedName("uvi") // Índice UV
    private float uvi;


    /** Getters and setters **/
    public float getDt() {
        return dt;
    }

    public void setDt(float dt) {
        this.dt = dt;
    }

    public float getSunrise() {
        return sunrise;
    }

    public void setSunrise(float sunrise) {
        this.sunrise = sunrise;
    }

    public float getSunset() {
        return sunset;
    }

    public void setSunset(float sunset) {
        this.sunset = sunset;
    }

    public float getMoonrise() {
        return moonrise;
    }

    public void setMoonrise(float moonrise) {
        this.moonrise = moonrise;
    }

    public float getMoonset() {
        return moonset;
    }

    public void setMoonset(float moonset) {
        this.moonset = moonset;
    }

    public float getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(float moonPhase) {
        this.moonPhase = moonPhase;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public FeelsLike getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(FeelsLike feelsLike) {
        this.feelsLike = feelsLike;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(float dewPoint) {
        this.dewPoint = dewPoint;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public float getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(float windDeg) {
        this.windDeg = windDeg;
    }

    public float getWindGust() {
        return windGust;
    }

    public void setWindGust(float windGust) {
        this.windGust = windGust;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public float getClouds() {
        return clouds;
    }

    public void setClouds(float clouds) {
        this.clouds = clouds;
    }

    public float getPop() {
        return pop;
    }

    public void setPop(float pop) {
        this.pop = pop;
    }

    public float getRain() {
        return rain;
    }

    public void setRain(float rain) {
        this.rain = rain;
    }

    public float getUvi() {
        return uvi;
    }

    public void setUvi(float uvi) {
        this.uvi = uvi;
    }

    /** Implementación Parcelable **/

    @Override
    public int describeContents() {
        return 0;
    }

    protected Daily(Parcel in) {
        dt = in.readFloat();
        sunrise = in.readFloat();
        sunset = in.readFloat();
        moonrise = in.readFloat();
        moonset = in.readFloat();
        moonPhase = in.readFloat();
        summary = in.readString();
        temp = in.readParcelable(Temp.class.getClassLoader());
        feelsLike = in.readParcelable(FeelsLike.class.getClassLoader());
        pressure = in.readFloat();
        humidity = in.readFloat();
        dewPoint = in.readFloat();
        windSpeed = in.readFloat();
        windDeg = in.readFloat();
        windGust = in.readFloat();
        weather = in.createTypedArrayList(Weather.CREATOR);
        clouds = in.readFloat();
        pop = in.readFloat();
        rain = in.readFloat();
        uvi = in.readFloat();
    }
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeFloat(dt);
        parcel.writeFloat(sunrise);
        parcel.writeFloat(sunset);
        parcel.writeFloat(moonrise);
        parcel.writeFloat(moonset);
        parcel.writeFloat(moonPhase);
        parcel.writeString(summary);
        parcel.writeParcelable(temp, i);
        parcel.writeParcelable(feelsLike, i);
        parcel.writeFloat(pressure);
        parcel.writeFloat(humidity);
        parcel.writeFloat(dewPoint);
        parcel.writeFloat(windSpeed);
        parcel.writeFloat(windDeg);
        parcel.writeFloat(windGust);
        parcel.writeTypedList(weather);
        parcel.writeFloat(clouds);
        parcel.writeFloat(pop);
        parcel.writeFloat(rain);
        parcel.writeFloat(uvi);
    }

    public static final Creator<Daily> CREATOR = new Creator<Daily>() {
        @Override
        public Daily createFromParcel(Parcel parcel) {
            return new Daily(parcel);
        }

        @Override
        public Daily[] newArray(int i) {
            return new Daily[i];
        }
    };
}
