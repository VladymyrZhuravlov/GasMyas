package io.itteam.gasmyas.json.activated;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Activator {

    @SerializedName("active")
    private boolean active;

    @SerializedName("connection")
    private boolean connection;

    @SerializedName("buffer")
    private boolean buffer;

    @SerializedName("statusCode")
    private int statusCode;

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setConnection(boolean connection) {
        this.connection = connection;
    }

    public boolean isConnection() {
        return connection;
    }

    public void setBuffer(boolean buffer) {
        this.buffer = buffer;
    }

    public boolean isBuffer() {
        return buffer;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}