package com.nasa.api.model;

import java.util.Map;

public class CompassData {

    // key is the ordinal indication for the 16-wind compass rose, from North clockwise (1..16)
    private final Map<Integer, CompassDataPoint> points;
    private final CompassDataPoint mostCommon;

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

class CompassDataPoint {

    private final int key;           // mirrors `CompassData.points.key` for utility
    private final int sampleCount;   // `ct`
    private final Double direction;  // `compass_degrees`
    private final String point;      // ex. "N" for North, "ESE" for East-South-East
    // the positive-right (positive-east), horizontal component of a unit vector indicating the direction of the compass point
    private final Double right;
    // the positive-up (positive-north), vertical component of a unit vector indicating the direction of the compass point
    private final Double up;         

    public CompassDataPoint(int key, int sampleCount, Double direction, String point, Double right, Double up) {
        this.key = key;
        this.sampleCount = sampleCount;
        this.direction = direction;
        this.point = point;
        this.right = right;
        this.up = up;
    }

    public int getKey() {
        return key;
    }

    public int getSampleCount() {
        return sampleCount;
    }

    public Double getDirection() {
        return direction;
    }

    public String getPoint() {
        return point;
    }

    public Double getRight() {
        return right;
    }

    public Double getUp() {
        return up;
    }
    
}
