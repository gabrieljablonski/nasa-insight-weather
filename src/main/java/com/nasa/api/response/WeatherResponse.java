package com.nasa.api.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {

    @SerializedName("status_code")
    private final int statusCode;
    @SerializedName("sol_keys")
    private final List<Integer> solKeys;
    @SerializedName("average_temperature")
    private final int averageTemperature;
    private final String message;

    public WeatherResponse(int statusCode, List<Integer> solKeys, int averageTemperature, String message) {
        this.statusCode = statusCode;
        this.solKeys = solKeys;
        this.averageTemperature = averageTemperature;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public List<Integer> getSolKeys() {
        return solKeys;
    }

    public int getAverageTemperature() {
        return averageTemperature;
    }

    public String getMessage() {
        return message;
    }
    
}