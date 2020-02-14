package com.nasa.api.model;

import java.time.Instant;
import java.util.Map;
import java.util.HashMap;

import com.nasa.api.model.compass.CompassData;

public class SolData {

    public enum Season {
        WINTER, SPRING, SUMMER, FALL;

        private static final Map<String, Season> reverseMapping = new HashMap<String, Season>();

        public static Season fromString(String string) {
            return reverseMapping.get(string);
        }

        static {
            for (Season season : Season.values()) {
                reverseMapping.put(season.name(), season);
            }
        }
    }

    private final int key;
    private final Season season;       // `Season`
    private final Instant firstUTC;  // `First_UTC` time of first datum of any sensor
    private final Instant lastUTC;   // `Last_UTC`  time of last datum of any sensor
    
    private final SensorData atmosphericTemperature;  // `AT`
    private final SensorData atmosphericPressure;     // `PRE`
    private final SensorData horizontalWindSpeed;     // `HWS`
    private final CompassData windDirection;          // `WD`

    public SolData(int key, Season season, Instant firstUTC, Instant lastUTC, SensorData atmosphericTemperature,
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

    public Instant getFirstUTC() {
        return firstUTC;
    }

    public Instant getLastUTC() {
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
