package com.jmarser.weatherapp_java.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Coord implements Parcelable {

	@SerializedName("lon")
	private double lon;

	@SerializedName("lat")
	private double lat;

	public Coord(double lon, double lat) {
		this.lon = lon;
		this.lat = lat;
	}

	public double getLon(){
		return lon;
	}

	public double getLat(){
		return lat;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NonNull Parcel parcel, int i) {
		parcel.writeDouble(lon);
		parcel.writeDouble(lat);
	}

	protected Coord(Parcel in) {
		lon = in.readDouble();
		lat = in.readDouble();
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