package com.example.springgooglemap.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class MapService {

    @Value("${GOOGLE_API_KEY}")
    private String googleApiKey;

    public Map<String, Object> getLatLong(String address) throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(googleApiKey)
                .build();

        GeocodingResult[] results =  GeocodingApi.geocode(context,
                address).await();

        // Invoke .shutdown() after your application is done making requests
        context.shutdown();

        return new HashMap<>() {{
            put("lat", results[0].geometry.location.lat);
            put("lng", results[0].geometry.location.lng);
        }};
    }

}
