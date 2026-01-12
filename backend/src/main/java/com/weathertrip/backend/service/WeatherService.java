package com.weathertrip.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    private static final String API_KEY = "024fcc47ae52d508d4c937e5db37a645";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public Map<String, Object> getWeatherByCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City name cannot be empty!");
        }

        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("q", city)
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric")
                .toUriString();

        System.out.println("Fetching weather from: " + url);

        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            if (response == null || !response.containsKey("main")) {
                throw new RuntimeException("No weather data found for city: " + city);
            }

            Map<String, Object> main = (Map<String, Object>) response.get("main");
            List<Map<String, Object>> weatherList = (List<Map<String, Object>>) response.get("weather");

            Map<String, Object> result = new HashMap<>();
            result.put("city", response.get("name"));
            result.put("temperature", main.get("temp"));
            result.put("description", weatherList.get(0).get("description"));
            return result;

        } catch (HttpClientErrorException e) {
            System.err.println("Error fetching weather: " + e.getMessage());
            throw new RuntimeException("City not found or invalid API key");
        }
    }
}

