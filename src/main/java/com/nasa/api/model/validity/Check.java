package com.nasa.api.model.validity;

import java.util.List;

public class Check {

    private final List<Integer> hoursWithData;  // `sol_hours_with_data`
    private final boolean valid;                // `valid`

    public Check(List<Integer> hoursWithData, boolean valid) {
        this.hoursWithData = hoursWithData;
        this.valid = valid;
    }

    public List<Integer> getHoursWithData() {
        return hoursWithData;
    }

    public boolean getValid() {
        return valid;
    }    

}
