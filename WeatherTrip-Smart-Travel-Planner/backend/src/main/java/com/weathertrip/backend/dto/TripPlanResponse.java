package com.weathertrip.backend.dto;

import java.util.List;
import java.util.Map;

public class TripPlanResponse {

    private String city;
    private Map<String, Object> weather;
    private List<Map<String, Object>> attractions;
    private String bestTime;

    public TripPlanResponse(String city,
                            Map<String, Object> weather,
                            List<Map<String, Object>> attractions,
                            String bestTime) {
        this.city = city;
        this.weather = weather;
        this.attractions = attractions;
        this.bestTime = bestTime;
    }

    public String getCity() {
        return city;
    }

    public Map<String, Object> getWeather() {
        return weather;
    }

    public List<Map<String, Object>> getAttractions() {
        return attractions;
    }

    public String getBestTime() {
        return bestTime;
    }
}
