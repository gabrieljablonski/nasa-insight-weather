package com.nasa.api.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {

    @SerializedName("status_code")
    private final int statusCode;
    @SerializedName("sol_keys")
    private final List<Integer> solKeys;
    @SerializedName("average_temperature")
    private final Double averageTemperature;
    private final String message;

    public WeatherResponse(List<Integer> solKeys, Double averageTemperature, String message) {
        this.statusCode = ResponseType.OK.getStatusCode();
        this.solKeys = solKeys;
        this.averageTemperature = averageTemperature;
        this.message = message;
    }

    public WeatherResponse(List<Integer> solKeys, Double averageTemperature) {
        this.statusCode = ResponseType.OK.getStatusCode();
        this.solKeys = solKeys;
        this.averageTemperature = averageTemperature;
        this.message = "Request successful.";
    }

    public int getStatusCode() {
        return statusCode;
    }

    public List<Integer> getSolKeys() {
        return solKeys;
    }

    public Double getAverageTemperature() {
        return averageTemperature;
    }

    public String getMessage() {
        return message;
    }
    
}