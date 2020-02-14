package com.nasa.api.model;

import java.util.Map;
import java.util.HashMap;

public class SensorData {

    public enum MeasurementUnit {

        TEMPERATURE("°F"),
        SPEED("m/s"),
        PRESSURE("Pa");

        private static final Map<String, MeasurementUnit> reverseMapping = new HashMap<String, MeasurementUnit>();
        private final String unit;
    
        private MeasurementUnit(String unit) {
            this.unit = unit;
        }
    
        public String getUnit() {
            return this.unit;
        }

        public static MeasurementUnit fromString(String string) {
            return reverseMapping.get(string);
        }

        static {
            for (MeasurementUnit unit : MeasurementUnit.values()) {
                reverseMapping.put(unit.name(), unit);
            }
        }
    
    }

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
