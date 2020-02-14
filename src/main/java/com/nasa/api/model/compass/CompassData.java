package com.nasa.api.model.compass;

import java.util.Map;

public class CompassData {

    // key is the ordinal indication for the 16-wind compass rose, from North clockwise (1..16)
    private final Map<Integer, CompassDataPoint> points;
    private final CompassDataPoint mostCommon;  // `most_common`

    public CompassData(Map<Integer, CompassDataPoint> points, CompassDataPoint mostCommon) {
        this.points = points;
        this.mostCommon = mostCommon;
    }

    public Map<Integer, CompassDataPoint> getPoints() {
        return points;
    }

    public CompassDataPoint getMostCommon() {
        return mostCommon;
    }
}
