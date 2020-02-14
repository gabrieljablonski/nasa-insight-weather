package com.nasa.api.model;

enum Season {
    WINTER, SPRING, SUMMER, FALL
}

enum MeasurementUnit {

    TEMPERATURE ("°F"),
    SPEED ("m/s"),
    PRESSURE ("Pa");

    private final String unit;

    private MeasurementUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return this.unit;
    }

}
