package com.jmarser.weatherapp_java.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Wind implements Parcelable {

	@SerializedName("deg")
	private int deg;

	@SerializedName("speed")
	private double speed;

	@SerializedName("gust")
	private double gust;

	public Wind(int deg, double speed, double gust) {
		this.deg = deg;
		this.speed = speed;
		this.gust = gust;
	}

	public int getDeg(){
		return deg;
	}

	public double getSpeed(){
		return speed;
	}

	public double getGust(){
		return gust;
	}

	public void setDeg(int deg) {
		this.deg = deg;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setGust(double gust) {
		this.gust = gust;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NonNull Parcel parcel, int i) {
		parcel.writeInt(deg);
		parcel.writeDouble(speed);
		parcel.writeDouble(gust);
	}

	protected Wind(Parcel in) {
		deg = in.readInt();
		speed = in.readDouble();
		gust = in.readDouble();
	}

	public static final Creator<Wind> CREATOR = new Creator<Wind>() {
		@Override
		public Wind createFromParcel(Parcel parcel) {
			return new Wind(parcel);
		}

		@Override
		public Wind[] newArray(int i) {
			return new Wind[i];
		}
	};
}