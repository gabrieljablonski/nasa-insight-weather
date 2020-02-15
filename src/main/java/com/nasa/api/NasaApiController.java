package com.nasa.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NameNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nasa.api.model.InSightWeatherData;
import com.nasa.api.response.ErrorResponse;
import com.nasa.api.response.Response;
import com.nasa.api.response.WeatherResponse;

@RestController
public class NasaApiController {

    private final static Logger logger = Logger.getLogger(NasaApiController.class.getName());

    @GetMapping("/nasa/temperature")
    public Response temperature(@RequestParam(value = "sol", required = false) Integer sol) {
        logger.log(Level.INFO, "Request received on /nasa/temperature");
        String loggingMessage = sol != null ? String.format("Data requested for sol %d", sol) : "No sol especified";
        logger.log(Level.INFO, loggingMessage);

        InSightWeatherData inSightWeatherData;
        try {
            inSightWeatherData = InSightWeatherApi.getInSightWeatherData();
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorResponse.internalError(e.getMessage());
        }

        List<Integer> solKeys;
        if (sol == null) {
            solKeys = inSightWeatherData.getSolKeys();
        } else {
            solKeys = new ArrayList<>();
            solKeys.add(sol);
        }

        Double temperature;
        try {
            temperature = inSightWeatherData.getAverageTemperature(sol);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return ErrorResponse.notFound(e.getMessage());
        }

        return new WeatherResponse(solKeys, temperature);
    }

}
