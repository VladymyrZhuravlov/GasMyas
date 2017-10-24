package io.itteam.gasmyas.json;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class PostCode{

	@SerializedName("smsCode")
	private String smsCode;

	@SerializedName("message")
	private String message;

	public void setSmsCode(String smsCode){
		this.smsCode = smsCode;
	}

	public String getSmsCode(){
		return smsCode;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}