package com.nasa.api.deserialization.compass;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import com.nasa.api.model.compass.CompassDataPoint;

public class CompassDataPointDeserializer implements JsonDeserializer<CompassDataPoint> {

    @Override
    public CompassDataPoint deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        int key = object.get("key").getAsInt();
        int sampleCount = object.get("ct").getAsInt();
        Double direction = object.get("compass_degrees").getAsDouble();
        String point = object.get("compass_point").getAsString();
        Double right = object.get("compass_right").getAsDouble();
        Double up = object.get("compass_up").getAsDouble();

        CompassDataPoint dataPoint = new CompassDataPoint(key, sampleCount, direction, point, right, up);
        return dataPoint;
    }

}
