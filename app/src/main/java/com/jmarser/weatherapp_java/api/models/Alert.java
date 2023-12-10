package com.jmarser.weatherapp_java.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Alert implements Parcelable {

	@SerializedName("start")
	private int start;

	@SerializedName("description")
	private String description;

	@SerializedName("sender_name")
	private String senderName;

	@SerializedName("end")
	private int end;

	@SerializedName("event")
	private String event;

	@SerializedName("tags")
	private List<String> tags;

	public Alert(int start, String description, String senderName, int end, String event, List<String> tags) {
		this.start = start;
		this.description = description;
		this.senderName = senderName;
		this.end = end;
		this.event = event;
		this.tags = tags;
	}

	/** Getters and setters **/
	public void setStart(int start){
		this.start = start;
	}

	public int getStart(){
		return start;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setSenderName(String senderName){
		this.senderName = senderName;
	}

	public String getSenderName(){
		return senderName;
	}

	public void setEnd(int end){
		this.end = end;
	}

	public int getEnd(){
		return end;
	}

	public void setEvent(String event){
		this.event = event;
	}

	public String getEvent(){
		return event;
	}

	public void setTags(List<String> tags){
		this.tags = tags;
	}

	public List<String> getTags(){
		return tags;
	}

	/** Implementación Parcelable **/

	protected Alert(Parcel in) {
		start = in.readInt();
		description = in.readString();
		senderName = in.readString();
		end = in.readInt();
		event = in.readString();
		tags = in.createStringArrayList();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(start);
		dest.writeString(description);
		dest.writeString(senderName);
		dest.writeInt(end);
		dest.writeString(event);
		dest.writeStringList(tags);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Alert> CREATOR = new Creator<Alert>() {
		@Override
		public Alert createFromParcel(Parcel in) {
			return new Alert(in);
		}

		@Override
		public Alert[] newArray(int size) {
			return new Alert[size];
		}
	};
}