package com.nasa.api.model;

import com.google.gson.Gson;

public class SensorData {

    private final MeasurementUnit unit;
    private final int sampleCount;       // `ct`
    private final Double sampleAverage;  // `av`
    private final Double sampleMinimum;  // `mn`
    private final Double sampleMaximum;  // `mx`

    public SensorData(MeasurementUnit unit, int sampleCount, Double sampleAverage, 
                      Double sampleMinimum, Double sampleMaximum) {
        this.unit = unit;
        this.sampleCount = sampleCount;
        this.sampleAverage = sampleAverage;
        this.sampleMinimum = sampleMinimum;
        this.sampleMaximum = sampleMaximum;
    }

    public MeasurementUnit getUnit() {
        return unit;
    }

    public int getSampleCount() {
        return sampleCount;
    }

    public Double getSampleAverage() {
        return sampleAverage;
    }

    public Double getSampleMinimum() {
        return sampleMinimum;
    }

    public Double getSampleMaximum() {
        return sampleMaximum;
    }

}
