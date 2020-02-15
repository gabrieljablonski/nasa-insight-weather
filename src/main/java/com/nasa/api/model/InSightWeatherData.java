package com.nasa.api.model;

import java.util.List;
import java.util.Map;

import javax.naming.NameNotFoundException;

import com.nasa.api.model.validity.ValidityChecks;

public class InSightWeatherData {

    private final Map<Integer, SolData> solData;
    private final List<Integer> solKeys;
    private final ValidityChecks validityChecks;

    public InSightWeatherData(Map<Integer, SolData> solData, List<Integer> solKeys,
                              ValidityChecks validityChecks) {
        this.solData = solData;
        this.solKeys = solKeys;
        this.validityChecks = validityChecks;
    }

    public Map<Integer, SolData> getSolData() {
        return solData;
    }

    public List<Integer> getSolKeys() {
        return solKeys;
    }

    public ValidityChecks getValidityChecks() {
        return validityChecks;
    }

    public Double getAverageTemperature(Integer sol) throws NameNotFoundException {
        if (sol != null) {
            if (!this.getSolKeys().contains(sol)) {
                throw new NameNotFoundException("Sol key not found. Available sol keys: " + this.getSolKeys().toString());
            }
            SensorData at = this.getSolData().get(sol).getAtmosphericTemperature();
            return at == null? null : at.getSampleAverage();
        }

        Double temperatureSum = 0.;
        int sampleCount = 0;
        for (SolData solData : this.getSolData().values()) {
            if (solData.getAtmosphericTemperature() == null) continue;
            temperatureSum += solData.getAtmosphericTemperature().getSampleAverage();
            sampleCount++;
        }
        return temperatureSum/sampleCount;
    }

    public Double getAverageTemperature() throws Exception {
        return getAverageTemperature(null);
    }

}
