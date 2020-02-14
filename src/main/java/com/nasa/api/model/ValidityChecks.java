package com.nasa.api.model;

import java.util.List;
import java.util.Map;

public class ValidityChecks {
    
    private final int hoursRequired;
    private final List<Integer> solsChecked;
    private final Map<Integer, ValidityCheck> checks;

    public ValidityChecks(int hoursRequired, List<Integer> solsChecked, Map<Integer, ValidityCheck> checks) {
        this.hoursRequired = hoursRequired;
        this.solsChecked = solsChecked;
        this.checks = checks;
    }

    public int getHoursRequired() {
        return hoursRequired;
    }

    public List<Integer> getSolsChecked() {
        return solsChecked;
    }

    public Map<Integer, ValidityCheck> getChecks() {
        return checks;
    }

}

class ValidityCheck {

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

class Check {

    private final List<Integer> hoursWithData;
    private final boolean valid;

    public Check(List<Integer> hoursWithData, boolean valid) {
        this.hoursWithData = hoursWithData;
        this.valid = valid;
    }

    public List<Integer> getHoursWithData() {
        return hoursWithData;
    }

    public boolean isValid() {
        return valid;
    }    

}
