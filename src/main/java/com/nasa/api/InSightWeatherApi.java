package com.nasa.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.nasa.api.deserialization.Deserializer;
import com.nasa.api.model.InSightWeatherData;

@Component
public class InSightWeatherApi {

    private static String nasaApiPrefix;
    private static String inSightWeatherEndpoint;

    private static String apiKey;
    public static final String feedType = "json";
    public static final String version = "1.0";

    @Value("${nasa.api.prefix}")
    public void setNasaApiPrefix(String prefix) {
        nasaApiPrefix = prefix;
    }

    @Value("${nasa.api.insight-weather}")
    public void setInSightWeatherEndpoint(String endpoint) {
        inSightWeatherEndpoint = endpoint;
    }

    @Value("${nasa.api.default-key}")
    public void setApiKey(String key) {
        apiKey = key;
    }

    private static String parseHTTPResponse(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    private static InSightWeatherData makeRequest() throws IOException {
        String uri = String.format("%s%s/?api_key=%s&feedtype=%s&ver=%s", nasaApiPrefix, inSightWeatherEndpoint, apiKey, feedType, version);
        URL url = new URL(uri);
        
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String result = parseHTTPResponse(con);
            return Deserializer.gson.fromJson(result, InSightWeatherData.class);
		}
        return null;
    }

    public static Double getAverageTemperature(Integer sol) throws Exception {
        InSightWeatherData inSightWeatherData = makeRequest();

        if (inSightWeatherData == null) {
            throw new Exception("Failed to retrieve inSight Weather data");
        }

        return inSightWeatherData.getAverageTemperature(sol);
    }

}