package com.jmarser.weatherapp_java.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Clouds implements Parcelable {

	@SerializedName("all")
	private int all;

	public Clouds(int all) {
		this.all = all;
	}

	public int getAll(){
		return all;
	}

	public void setAll(int all) {
		this.all = all;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NonNull Parcel parcel, int i) {
		parcel.writeInt(all);
	}

	protected Clouds(Parcel in) {
		all = in.readInt();
	}

	public static final Creator<Clouds> CREATOR = new Creator<Clouds>(){

		@Override
		public Clouds createFromParcel(Parcel parcel) {
			return new Clouds(parcel);
		}

		@Override
		public Clouds[] newArray(int i) {
			return new Clouds[i];
		}
	};
}