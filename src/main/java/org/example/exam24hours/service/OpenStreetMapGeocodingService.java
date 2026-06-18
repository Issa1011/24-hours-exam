package org.example.exam24hours.service;

import org.springframework.stereotype.Service;

@Service
public class OpenStreetMapGeocodingService implements GeocodingService{

    @Override
    public String getAreaName(double latitude, double longitude){
        return "Unknown Area";
    }
}
