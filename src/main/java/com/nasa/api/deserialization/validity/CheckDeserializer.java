package com.nasa.api.deserialization.validity;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import com.nasa.api.model.validity.Check;

public class CheckDeserializer implements JsonDeserializer<Check> {

    @Override
    public Check deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        List<Integer> hoursWithData = new ArrayList<Integer>();
        for(JsonElement element : object.get("sol_hours_with_data").getAsJsonArray()) {
            hoursWithData.add(element.getAsInt());
        }

        boolean valid = object.get("valid").getAsBoolean();

        Check check = new Check(hoursWithData, valid);
        return check;
    }

}