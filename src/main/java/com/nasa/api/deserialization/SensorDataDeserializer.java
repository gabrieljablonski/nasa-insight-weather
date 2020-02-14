package com.nasa.api.deserialization;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import com.nasa.api.model.SensorData;
import com.nasa.api.model.SensorData.MeasurementUnit;

public class SensorDataDeserializer implements JsonDeserializer<SensorData> {

    @Override
    public SensorData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        MeasurementUnit unit = MeasurementUnit.fromString(object.get("unit").getAsString());
        int sampleCount = object.get("ct").getAsInt();
        Double sampleAverage = object.get("av").getAsDouble();
        Double sampleMinimum = object.get("mn").getAsDouble();
        Double sampleMaximum = object.get("mx").getAsDouble();
        
        SensorData sensorData = new SensorData(unit, sampleCount, sampleAverage, sampleMinimum, sampleMaximum);
        return sensorData;
    }

}
