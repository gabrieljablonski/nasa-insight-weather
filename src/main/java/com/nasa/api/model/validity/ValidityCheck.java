package com.nasa.api.model.validity;

public class ValidityCheck {

    private final int key;  // mirrors `ValidityChecks.checks.key` for utility

    private final Check atmosphericTemperature;  // `AT`
    private final Check atmosphericPressure;     // `PRE`
    private final Check horizontalWindSpeed;     // `HWS`
    private final Check windDirection;           // `WD`

    public ValidityCheck(int key, Check atmosphericTemperature, Check atmosphericPressure, Check horizontalWindSpeed,
            Check windDirection) {
        this.key = key;
        this.atmosphericTemperature = atmosphericTemperature;
        this.atmosphericPressure = atmosphericPressure;
        this.horizontalWindSpeed = horizontalWindSpeed;
        this.windDirection = windDirection;
    }

    public int getKey() {
        return key;
    }

    public Check getAtmosphericTemperature() {
        return atmosphericTemperature;
    }

    public Check getAtmosphericPressure() {
        return atmosphericPressure;
    }

    public Check getHorizontalWindSpeed() {
        return horizontalWindSpeed;
    }

    public Check getWindDirection() {
        return windDirection;
    }

}
