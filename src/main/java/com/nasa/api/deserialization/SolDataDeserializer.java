package com.nasa.api.deserialization;

import java.time.Instant;
import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import com.nasa.api.model.SolData;
import com.nasa.api.model.SolData.Season;
import com.nasa.api.model.SensorData;
import com.nasa.api.model.compass.CompassData;

public class SolDataDeserializer implements JsonDeserializer<SolData> {

    @Override
    public SolData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        int key = object.get("key").getAsInt();
        Season season = Season.fromString(object.get("Season").getAsString().toUpperCase());

        Instant firstUTC = Instant.parse(object.get("First_UTC").getAsString());
        Instant lastUTC = Instant.parse(object.get("Last_UTC").getAsString());

        SensorData atmosphericPressure;
        SensorData atmosphericTemperature;
        SensorData horizontalWindSpeed;
        CompassData windDirection;
        
        try {
            object.get("AT").getAsJsonObject().addProperty("unit", "TEMPERATURE");
            atmosphericPressure = Deserializer.gson.fromJson(object.get("AT"), SensorData.class);
        } catch (Exception e) {
            atmosphericPressure = null;
        }
        try {
            object.get("PRE").getAsJsonObject().addProperty("unit", "PRESSURE");
            atmosphericTemperature = Deserializer.gson.fromJson(object.get("PRE"), SensorData.class);
        } catch (Exception e) {
            atmosphericTemperature = null;
        }
        try {
            object.get("HWS").getAsJsonObject().addProperty("unit", "SPEED");
            horizontalWindSpeed = Deserializer.gson.fromJson(object.get("HWS"), SensorData.class);
        } catch (Exception e) {
            horizontalWindSpeed = null;
        }
        try {
            windDirection = Deserializer.gson.fromJson(object.get("WD"), CompassData.class);
        } catch (Exception e) {
            windDirection = null;
        }

        SolData solData = new SolData(key, season, firstUTC, lastUTC, atmosphericTemperature, atmosphericPressure, horizontalWindSpeed, windDirection);
        return solData;
    }

}
