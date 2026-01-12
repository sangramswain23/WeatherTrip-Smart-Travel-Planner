package com.weathertrip.backend.service;

import com.weathertrip.backend.dto.TripPlanResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TripService {

    private final WeatherService weatherService;
    private final AttractionService attractionService;

    public TripService(WeatherService weatherService, AttractionService attractionService) {
        this.weatherService = weatherService;
        this.attractionService = attractionService;
    }

    public TripPlanResponse planTrip(String city) {

        // ----- WEATHER -----
        Map<String, Object> weather = weatherService.getWeatherByCity(city);

        // ----- ATTRACTIONS -----
        Map<String, Object> attractionResult = attractionService.getAttractionsByCity(city);

        // Extract features safely
        List<Map<String, Object>> attractions;

        Object featuresObj = attractionResult.get("features");

        if (featuresObj instanceof List) {
            attractions = (List<Map<String, Object>>) featuresObj;
        } else {
            attractions = new ArrayList<>();  // avoid NullPointerException
        }

        // ----- BEST TIME -----
        String bestTime = calculateBestTime(weather);

        // RETURN FINAL RESPONSE DTO
        return new TripPlanResponse(city, weather, attractions, bestTime);
    }

    private String calculateBestTime(Map<String, Object> weather) {

        if (weather == null || weather.get("description") == null) {
            return "Weather data unavailable";
        }

        String condition = weather.get("description").toString().toLowerCase();

        if (condition.contains("rain") || condition.contains("storm")) {
            return "Avoid this week due to rain";
        }

        return "Perfect time to travel!";
    }

}
