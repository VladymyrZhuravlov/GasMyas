package io.itteam.gasmyas.json;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class JsonResalt{

	@SerializedName("data")
	private String data;

	public void setData(String data){
		this.data = data;
	}

	public String getData(){
		return data;
	}
}