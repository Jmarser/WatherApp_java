package com.jmarser.weatherapp_java.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Main implements Parcelable {

	@SerializedName("temp")
	private double temp;

	@SerializedName("temp_min")
	private double tempMin;

	@SerializedName("grnd_level")
	private int grndLevel;

	@SerializedName("humidity")
	private int humidity;

	@SerializedName("pressure")
	private int pressure;

	@SerializedName("sea_level")
	private int seaLevel;

	@SerializedName("feels_like")
	private double feelsLike;

	@SerializedName("temp_max")
	private double tempMax;

	public Main(double temp, double tempMin, int grndLevel, int humidity, int pressure, int seaLevel, double feelsLike, double tempMax) {
		this.temp = temp;
		this.tempMin = tempMin;
		this.grndLevel = grndLevel;
		this.humidity = humidity;
		this.pressure = pressure;
		this.seaLevel = seaLevel;
		this.feelsLike = feelsLike;
		this.tempMax = tempMax;
	}

	/** Getters and setters **/

	public double getTemp(){
		return temp;
	}

	public double getTempMin(){
		return tempMin;
	}

	public int getGrndLevel(){
		return grndLevel;
	}

	public int getHumidity(){
		return humidity;
	}

	public int getPressure(){
		return pressure;
	}

	public int getSeaLevel(){
		return seaLevel;
	}

	public double getFeelsLike(){
		return feelsLike;
	}

	public double getTempMax(){
		return tempMax;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}

	public void setGrndLevel(int grndLevel) {
		this.grndLevel = grndLevel;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public void setSeaLevel(int seaLevel) {
		this.seaLevel = seaLevel;
	}

	public void setFeelsLike(double feelsLike) {
		this.feelsLike = feelsLike;
	}

	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}

	/** Implementación Parcelable **/

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NonNull Parcel parcel, int i) {
		parcel.writeDouble(temp);
		parcel.writeDouble(tempMin);
		parcel.writeDouble(tempMax);
		parcel.writeInt(grndLevel);
		parcel.writeInt(humidity);
		parcel.writeInt(pressure);
		parcel.writeInt(seaLevel);
		parcel.writeDouble(feelsLike);
	}

	protected Main(Parcel in) {
		temp = in.readDouble();
		tempMin = in.readDouble();
		grndLevel = in.readInt();
		humidity = in.readInt();
		pressure = in.readInt();
		seaLevel = in.readInt();
		feelsLike = in.readDouble();
		tempMax = in.readDouble();

	}

	public static final Creator<Main> CREATOR = new Creator<Main>() {
		@Override
		public Main createFromParcel(Parcel parcel) {
			return new Main(parcel);
		}

		@Override
		public Main[] newArray(int i) {
			return new Main[i];
		}
	};
}