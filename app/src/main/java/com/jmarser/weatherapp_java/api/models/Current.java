package com.jmarser.weatherapp_java.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Current implements Parcelable {

    @SerializedName("dt") // Hora de los datos
    private float dt;

    @SerializedName("sunrise") // Hora de salida del sol
    private float sunrise;

    @SerializedName("sunset") // Hora en la que se oculta el sol
    private float sunset;

    @SerializedName("temp") // Temperatura (Según lenguaje, defecto Kelvin)
    private float temp;

    @SerializedName("feels_like") // Sensación térmica (Según lenguaje, defecto Kelvin)
    private float feelsLike;

    @SerializedName("pressure") // Presión atmosférica (hPa)
    private float pressure;

    @SerializedName("humidity") // Humedad
    private float humidity;

    @SerializedName("dew_point") // Punto de rocio (Según lenguaje, defecto Kelvin)
    private float dewPoint;

    @SerializedName("uvi") // Índice UV
    private float uvi;

    @SerializedName("clouds") // Nubosidad (%)
    private float clouds;

    @SerializedName("visibility") // Visibilidad media, (m)
    private float visibility;

    @SerializedName("wind_speed") // velocidad del viento m/s
    private float windSpeed;

    @SerializedName("wind_deg") // Dirección del viento (º)
    private float windDeg;

    @SerializedName("wind_gust") // Ráfaga de viento (m/s)
    private float windGust;

    @SerializedName("weather")
    private ArrayList< Weather > weather = new ArrayList < Weather > ();

    public Current(float dt, float sunrise, float sunset, float temp, float feelsLike, float pressure, float humidity, float dew_point, float uvi, float clouds, float visibility, float windSpeed, float windDeg, float windGust, ArrayList<Weather> weather) {
        this.dt = dt;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.pressure = pressure;
        this.humidity = humidity;
        this.dewPoint = dew_point;
        this.uvi = uvi;
        this.clouds = clouds;
        this.visibility = visibility;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
        this.windGust = windGust;
        this.weather = weather;
    }

    /** Getters and setters **/

    public float getDt() {
        return dt;
    }

    public float getSunrise() {
        return sunrise;
    }

    public float getSunset() {
        return sunset;
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

    // Setter Methods

    public void setDt(float dt) {
        this.dt = dt;
    }

    public void setSunrise(float sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(float sunset) {
        this.sunset = sunset;
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

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    /** Implementación Parcelable **/

    protected Current(Parcel in) {
        dt = in.readFloat();
        sunrise = in.readFloat();
        sunset = in.readFloat();
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
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(dt);
        dest.writeFloat(sunrise);
        dest.writeFloat(sunset);
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
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Current> CREATOR = new Creator<Current>() {
        @Override
        public Current createFromParcel(Parcel in) {
            return new Current(in);
        }

        @Override
        public Current[] newArray(int size) {
            return new Current[size];
        }
    };
}
