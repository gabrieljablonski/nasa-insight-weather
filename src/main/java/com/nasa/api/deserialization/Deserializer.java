package com.nasa.api.deserialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nasa.api.model.InSightWeatherData;
import com.nasa.api.model.SensorData;
import com.nasa.api.model.compass.CompassData;
import com.nasa.api.model.compass.CompassDataPoint;
import com.nasa.api.model.validity.Check;
import com.nasa.api.model.validity.ValidityCheck;
import com.nasa.api.model.validity.ValidityChecks;
import com.nasa.api.model.SolData;
import com.nasa.api.deserialization.SensorDataDeserializer;
import com.nasa.api.deserialization.compass.CompassDataDeserializer;
import com.nasa.api.deserialization.compass.CompassDataPointDeserializer;
import com.nasa.api.deserialization.validity.CheckDeserializer;
import com.nasa.api.deserialization.validity.ValidityCheckDeserializer;
import com.nasa.api.deserialization.validity.ValidityChecksDeserializer;

public class Deserializer {

    public static Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder();
        
        builder.registerTypeAdapter(SensorData.class, new SensorDataDeserializer());
        builder.registerTypeAdapter(CompassDataPoint.class, new CompassDataPointDeserializer());
        builder.registerTypeAdapter(CompassData.class, new CompassDataDeserializer());
        builder.registerTypeAdapter(Check.class, new CheckDeserializer());
        builder.registerTypeAdapter(ValidityCheck.class, new ValidityCheckDeserializer());
        builder.registerTypeAdapter(ValidityChecks.class, new ValidityChecksDeserializer());
        builder.registerTypeAdapter(SolData.class, new SolDataDeserializer());
        builder.registerTypeAdapter(InSightWeatherData.class, new InSightWeatherDataDeserializer());

        gson = builder.create();
    }

}