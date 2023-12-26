package com.jmarser.weatherapp_java.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Objects;

public class DatosCiudad implements Parcelable {

    /** Propiedades **/

    private String nombre;
    private float latitud;
    private float longitud;


    /** Constructor **/

    public DatosCiudad(String nombre, float latitud, float longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }


    /** Getters and setters **/

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }


    /** Implementación Parcelable **/

    protected DatosCiudad(Parcel in) {
        this.nombre = in.readString();
        this.longitud = in.readFloat();
        this.latitud = in.readFloat();
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeFloat(longitud);
        parcel.writeFloat(latitud);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DatosCiudad> CREATOR = new Creator<DatosCiudad>() {
        @Override
        public DatosCiudad createFromParcel(Parcel parcel) {
            return new DatosCiudad(parcel);
        }

        @Override
        public DatosCiudad[] newArray(int i) {
            return new DatosCiudad[i];
        }
    };

    /** Métodos para comparar objetos de esta clase **/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DatosCiudad otraCiudad = (DatosCiudad) obj;
        return Objects.equals(nombre, otraCiudad.nombre) &&
                Objects.equals(latitud, otraCiudad.latitud) &&
                Objects.equals(longitud, otraCiudad.longitud);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, latitud, longitud);
    }
}
