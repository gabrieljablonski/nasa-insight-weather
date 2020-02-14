package com.nasa.api.model;

import java.time.LocalDate;

public class SolData {

    private final int key;
    private final Season season;
    private final LocalDate firstUTC;  // time of first datum of any sensor
    private final LocalDate lastUTC;   // time of last datum of any sensor
    
    private final SensorData atmosphericTemperature;  // `AT`
    private final SensorData atmosphericPressure;     // `PRE`
    private final SensorData horizontalWindSpeed;     // `HWS`
    private final CompassData windDirection;          // `WD`

    public SolData(int key, Season season, LocalDate firstUTC, LocalDate lastUTC, SensorData atmosphericTemperature,
                   SensorData atmosphericPressure, SensorData horizontalWindSpeed, CompassData windDirection) {
        this.key = key;
        this.season = season;
        this.firstUTC = firstUTC;
        this.lastUTC = lastUTC;
        this.atmosphericTemperature = atmosphericTemperature;
        this.atmosphericPressure = atmosphericPressure;
        this.horizontalWindSpeed = horizontalWindSpeed;
        this.windDirection = windDirection;
    }

    public int getKey() {
        return key;
    }

    public Season getSeason() {
        return season;
    }

    public LocalDate getFirstUTC() {
        return firstUTC;
    }

    public LocalDate getLastUTC() {
        return lastUTC;
    }

    public SensorData getAtmosphericTemperature() {
        return atmosphericTemperature;
    }

    public SensorData getAtmosphericPressure() {
        return atmosphericPressure;
    }

    public SensorData getHorizontalWindSpeed() {
        return horizontalWindSpeed;
    }

    public CompassData getWindDirection() {
        return windDirection;
    }
    
}
