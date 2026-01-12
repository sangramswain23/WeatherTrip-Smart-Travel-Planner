package com.weathertrip.backend.controller;

import com.weathertrip.backend.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/attractions")
public class AttractionController {

    @Autowired
    private AttractionService attractionService;

    @GetMapping("/{city}")
    public Map<String, Object> getAttractions(@PathVariable String city) {
        return attractionService.getAttractionsByCity(city);
    }
}
