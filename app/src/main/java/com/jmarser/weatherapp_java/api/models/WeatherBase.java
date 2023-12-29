package com.jmarser.weatherapp_java.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherBase implements Parcelable {

	@SerializedName("visibility")
	private int visibility;

	@SerializedName("timezone")
	private int timezone;

	@SerializedName("main")
	private Main main;

	@SerializedName("clouds")
	private Clouds clouds;

	@SerializedName("sys")
	private Sys sys;

	@SerializedName("dt")
	private int dt;

	@SerializedName("coord")
	private Coord coord;

	@SerializedName("weather")
	private List<Weather> weather;

	@SerializedName("name")
	private String name;

	@SerializedName("cod")
	private int cod;

	@SerializedName("id")
	private int id;

	@SerializedName("base")
	private String base;

	@SerializedName("wind")
	private Wind wind;

	public WeatherBase(int visibility, int timezone, Main main, Clouds clouds, Sys sys, int dt, Coord coord, List<Weather> weather, String name, int cod, int id, String base, Wind wind) {
		this.visibility = visibility;
		this.timezone = timezone;
		this.main = main;
		this.clouds = clouds;
		this.sys = sys;
		this.dt = dt;
		this.coord = coord;
		this.weather = weather;
		this.name = name;
		this.cod = cod;
		this.id = id;
		this.base = base;
		this.wind = wind;
	}

	/** Getters and setters **/

	public int getVisibility(){
		return visibility;
	}

	public int getTimezone(){
		return timezone;
	}

	public Main getMain(){
		return main;
	}

	public Clouds getClouds(){
		return clouds;
	}

	public Sys getSys(){
		return sys;
	}

	public int getDt(){
		return dt;
	}

	public Coord getCoord(){
		return coord;
	}

	public List<Weather> getWeather(){
		return weather;
	}

	public String getName(){
		return name;
	}

	public int getCod(){
		return cod;
	}

	public int getId(){
		return id;
	}

	public String getBase(){
		return base;
	}

	public Wind getWind(){
		return wind;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	public void setDt(int dt) {
		this.dt = dt;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	@Override
	public String toString() {
		return name + ", " + sys.getCountry();
	}

	/** Implementación Parcelable **/

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NonNull Parcel parcel, int i) {

	}

	protected WeatherBase(Parcel in) {

	}

	public static final Creator<WeatherBase> CREATOR = new Creator<WeatherBase>() {
		@Override
		public WeatherBase createFromParcel(Parcel parcel) {
			return new WeatherBase(parcel);
		}

		@Override
		public WeatherBase[] newArray(int i) {
			return new WeatherBase[i];
		}
	};
}