package org.example.exam24hours.service;

import org.example.exam24hours.dto.ReverseGeocodingResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OpenStreetMapGeocodingService implements GeocodingService{

    private final RestClient restClient;

    public OpenStreetMapGeocodingService(){
        this.restClient = RestClient.builder()
                .baseUrl("https://nominatim.openstreetmap.org")
                .build();
    }

    @Override
    public String getAreaName(double latitude, double longitude) {
        try {
            ReverseGeocodingResponse response =
                    restClient.get()
                            .uri(uriBuilder -> uriBuilder
                                    .path("/reverse")
                                    .queryParam("format", "json")
                                    .queryParam("lat", latitude)
                                    .queryParam("lon", longitude)
                                    .build())
                            .header("User-Agent", "SeismicMonitor")
                            .retrieve()
                            .body(ReverseGeocodingResponse.class);

            if (response != null && response.getDisplay_name() != null) {
                return response.getDisplay_name();
            }

        } catch (Exception e) {
            System.out.println("Geocoding failed");
        }

        return "Unknown Area";
    }
}
