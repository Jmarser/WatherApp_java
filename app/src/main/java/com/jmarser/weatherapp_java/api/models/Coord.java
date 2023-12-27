package com.jmarser.weatherapp_java.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Coord implements Parcelable {

	@SerializedName("lon")
	private float lon;

	@SerializedName("lat")
	private float lat;

	public Coord(float lon, float lat) {
		this.lon = lon;
		this.lat = lat;
	}

	public float getLon(){
		return lon;
	}

	public float getLat(){
		return lat;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NonNull Parcel parcel, int i) {
		parcel.writeFloat(lon);
		parcel.writeFloat(lat);
	}

	protected Coord(Parcel in) {
		lon = in.readFloat();
		lat = in.readFloat();
	}

	public static final Creator<Coord> CREATOR = new Creator<Coord>() {
		@Override
		public Coord createFromParcel(Parcel parcel) {
			return new Coord(parcel);
		}

		@Override
		public Coord[] newArray(int i) {
			return new Coord[i];
		}
	};
}