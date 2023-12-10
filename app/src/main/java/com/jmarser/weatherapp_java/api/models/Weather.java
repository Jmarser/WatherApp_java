package com.jmarser.weatherapp_java.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Weather implements Parcelable {

    @SerializedName("icon") // Identificador del icono del clima
    private String icon;

    @SerializedName("description") // Descripción de las condiciones climáticas
    private String description;

    @SerializedName("main") // estado del clima
    private String main;

    @SerializedName("id") // Identificador del clima
    private String id;

    /** Getters and setters **/

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    /** Implementación Parcelable **/

    protected Weather(Parcel in) {
        icon = in.readString();
        description = in.readString();
        main = in.readString();
        id = in.readString();
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(icon);
        parcel.writeString(description);
        parcel.writeString(main);
        parcel.writeString(id);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel parcel) {
            return new Weather(parcel);
        }

        @Override
        public Weather[] newArray(int i) {
            return new Weather[i];
        }
    };

}
