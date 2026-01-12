package com.weathertrip.backend.controller;

import com.weathertrip.backend.dto.TripPlanResponse;
import com.weathertrip.backend.service.TripService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trip")
@CrossOrigin(origins = "*")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/plan")
    public TripPlanResponse planTrip(@RequestParam String city) {

        // Trim city to avoid spaces causing API errors
        city = city.trim();

        return tripService.planTrip(city);
    }
}
