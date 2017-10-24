package io.itteam.gasmyas.json;

import com.google.gson.annotations.SerializedName;

public class PhoneNumber {
    @SerializedName("phone")
    private String phone;

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhone(){
        return phone;
    }

    public PhoneNumber(String phone){
        this.phone = phone;
    }
}
