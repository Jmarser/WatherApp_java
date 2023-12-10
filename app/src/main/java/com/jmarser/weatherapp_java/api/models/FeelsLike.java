package com.jmarser.weatherapp_java.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class FeelsLike implements Parcelable {

    @SerializedName("day") // Sensación térmica de día
    private float day;

    @SerializedName("night") // Sensación térmica de noche
    private float night;

    @SerializedName("eve") // Sensación térmica de la tarde
    private float eve;

    @SerializedName("morn") // Sensación térmica del amanecer
    private float morn;

    /** Getters and setters **/

    public float getDay() {
        return day;
    }

    public void setDay(float day) {
        this.day = day;
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

    protected FeelsLike(Parcel in) {
        day = in.readFloat();
        night = in.readFloat();
        eve = in.readFloat();
        morn = in.readFloat();
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeFloat(day);
        parcel.writeFloat(night);
        parcel.writeFloat(eve);
        parcel.writeFloat(morn);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FeelsLike> CREATOR = new Creator<FeelsLike>() {
        @Override
        public FeelsLike createFromParcel(Parcel parcel) {
            return new FeelsLike(parcel);
        }

        @Override
        public FeelsLike[] newArray(int i) {
            return new FeelsLike[i];
        }
    };
}
