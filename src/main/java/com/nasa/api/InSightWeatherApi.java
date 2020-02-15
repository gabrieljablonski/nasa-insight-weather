package com.nasa.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import com.nasa.api.deserialization.Deserializer;
import com.nasa.api.model.InSightWeatherData;

@Component
public class InSightWeatherApi {

    private final static Logger logger = Logger.getLogger(InSightWeatherApi.class.getName());

    private static Cache cachedInSightWeatherData = new Cache();

    private static String nasaApiPrefix;
    private static String inSightWeatherEndpoint;

    private static String apiKey;
    private static final String feedType = "json";
    private static final String version = "1.0";

    @Value("${nasa.api.prefix}")
    public void setNasaApiPrefix(String prefix) {
        nasaApiPrefix = prefix;
    }

    @Value("${nasa.api.insight-weather}")
    public void setInSightWeatherEndpoint(String endpoint) {
        inSightWeatherEndpoint = endpoint;
    }

    @Value("${nasa.api.key}")
    public void setApiKey(String key) {
        apiKey = key;
    }

    @Value("${nasa.api.cache.expiration}")
    public void setCacheExpiration(String expiration) {
        cachedInSightWeatherData.setExpiration(Integer.parseInt(expiration));
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

    private static InSightWeatherData makeRequest() throws Exception {
        String uri = String.format("%s%s/?api_key=%s&feedtype=%s&ver=%s", nasaApiPrefix, inSightWeatherEndpoint, apiKey, feedType, version);
        URL url = new URL(uri);
        
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String result = parseHTTPResponse(con);
            return Deserializer.gson.fromJson(result, InSightWeatherData.class);
        }
        throw new Exception("HTTP response code " + responseCode);
    }

    public static void forceCacheExpire() {
        logger.info("Force-expiring cache");
        cachedInSightWeatherData.forceExpire();
    }

    public static InSightWeatherData getInSightWeatherData() throws Exception {
        if (cachedInSightWeatherData.isExpired()) {
            logger.info("Cached data expired. Retrieving new data");
            try {
                cachedInSightWeatherData.cacheData(makeRequest());
            } catch (Exception e) {
                throw new Exception("Failed to retrieve inSight Weather data: " + e.getMessage());
            }
        } else {
            logger.info("Using cached data");
        }
        return (InSightWeatherData) cachedInSightWeatherData.getData();
    }

}
