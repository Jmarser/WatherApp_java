package com.jmarser.weatherapp_java.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;


public class Temp implements Parcelable {

    @SerializedName("day") // Tempratura de día
    private float day;

    @SerializedName("min") // Temperatura mínima
    private float min;

    @SerializedName("max") // Temperatura máxima
    private float max;

    @SerializedName("night") // Temperatura de noche
    private float night;

    @SerializedName("eve") // Temperatura de la tarde
    private float eve;

    @SerializedName("morn") // Temperatura en el alba
    private float morn;

    /** Getters and setters **/

    public float getDay() {
        return day;
    }

    public void setDay(float day) {
        this.day = day;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getNight() {
        return night;
    }

    public void setNight(float night) {
        this.night = night;
    }

    public float getEve() {
        return eve;
    }

    public void setEve(float eve) {
        this.eve = eve;
    }

    public float getMorn() {
        return morn;
    }

    public void setMorn(float morn) {
        this.morn = morn;
    }


    /** Implementación Parcelable **/

    protected Temp(Parcel in) {
        day = in.readFloat();
        min = in.readFloat();
        max = in.readFloat();
        night = in.readFloat();
        eve = in.readFloat();
        morn = in.readFloat();
    }
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Temp> CREATOR = new Creator<Temp>() {
        @Override
        public Temp createFromParcel(Parcel parcel) {
            return new Temp(parcel);
        }

        @Override
        public Temp[] newArray(int i) {
            return new Temp[i];
        }
    };

}
