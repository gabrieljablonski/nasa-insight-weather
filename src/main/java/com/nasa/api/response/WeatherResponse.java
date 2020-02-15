package com.nasa.api.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import org.decimal4j.util.DoubleRounder;

public class WeatherResponse extends Response {

    @SerializedName("sol_keys")
    private final List<Integer> solKeys;
    @SerializedName("average_temperature")
    private final Double averageTemperature;

    public WeatherResponse(List<Integer> solKeys, Double averageTemperature, String message) {
        super(ResponseType.OK.getStatusCode(), message);
        this.solKeys = solKeys;
        this.averageTemperature = DoubleRounder.round(averageTemperature, 3);
    }

    public WeatherResponse(List<Integer> solKeys, Double averageTemperature) {
        this(solKeys, averageTemperature, "Request successful.");
    }

    public List<Integer> getSolKeys() {
        return solKeys;
    }

    public Double getAverageTemperature() {
        return averageTemperature;
    }
    
}