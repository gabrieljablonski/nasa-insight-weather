package com.nasa.api.model.validity;

import java.util.List;

public class Check {

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
