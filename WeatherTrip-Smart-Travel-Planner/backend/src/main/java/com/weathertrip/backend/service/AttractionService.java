package com.weathertrip.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.*;

@Service
public class AttractionService {

    private static final String GEOAPIFY_KEY = "9de915e29df64810ace2190fded23273"; // put your actual key here

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> getAttractionsByCity(String city) {
        try {
            // Step 1: Get coordinates of the city
            String geocodeUrl = String.format(
                "https://api.geoapify.com/v1/geocode/search?text=%s&apiKey=%s",
                city, GEOAPIFY_KEY
            );
            Map<String, Object> geoResponse = restTemplate.getForObject(geocodeUrl, Map.class);
            List<Map<String, Object>> features = (List<Map<String, Object>>) geoResponse.get("features");

            if (features == null || features.isEmpty()) {
                return Map.of("error", "City not found");
            }

            Map<String, Object> geometry = (Map<String, Object>) features.get(0).get("geometry");
            List<Double> coordinates = (List<Double>) geometry.get("coordinates");
            double lon = coordinates.get(0);
            double lat = coordinates.get(1);

            // Step 2: Get nearby attractions
            String placesUrl = String.format(
                "https://api.geoapify.com/v2/places?categories=tourism.attraction&filter=circle:%f,%f,5000&apiKey=%s",
                lon, lat, GEOAPIFY_KEY
            );
            ResponseEntity<Map> response = restTemplate.getForEntity(placesUrl, Map.class);

            return response.getBody();
        } catch (Exception e) {
            return Map.of("error", e.getMessage());
        }
    }
}
