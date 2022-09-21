package com.example.springgooglemap.controller;

import com.example.springgooglemap.service.MapService;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MapController {

    @Autowired
    private MapService mapService;

    @GetMapping("/getLatLong")
    public Map<String, Object> getGeocoding(
            @RequestParam String address
    ) throws IOException, InterruptedException, ApiException {
        return mapService.getLatLong(address);
    }

}
