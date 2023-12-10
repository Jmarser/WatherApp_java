package com.jmarser.weatherapp_java.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Sys implements Parcelable {

    @SerializedName("country")
    private String country;

    @SerializedName("sunrise")
    private int sunrise;

    @SerializedName("sunset")
    private int sunset;

    @SerializedName("id")
    private int id;

    @SerializedName("type")
    private int type;

    public Sys(String country, int sunrise, int sunset, int id, int type) {
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.id = id;
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public int getSunrise() {
        return sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(country);
        parcel.writeInt(sunrise);
        parcel.writeInt(sunset);
        parcel.writeInt(id);
        parcel.writeInt(type);
    }

    protected Sys(Parcel in) {
        country = in.readString();
        sunrise = in.readInt();
        sunset = in.readInt();
        id = in.readInt();
        type = in.readInt();
    }

    public static final Creator<Sys> CREATOR = new Creator<Sys>() {
        @Override
        public Sys createFromParcel(Parcel parcel) {
            return new Sys(parcel);
        }

        @Override
        public Sys[] newArray(int i) {
            return new Sys[i];
        }
    };
}