package org.example.exam24hours.controller;

import org.example.exam24hours.model.EarthquakeAlert;
import org.example.exam24hours.repository.EarthquakeAlertRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EarthquakeAlertController {

    private final EarthquakeAlertRepository earthquakeAlertRepository;

    public EarthquakeAlertController(EarthquakeAlertRepository earthquakeAlertRepository) {
        this.earthquakeAlertRepository = earthquakeAlertRepository;
    }

    @GetMapping("/api/alerts")
    public List<EarthquakeAlert> getAllAlerts(){
        return earthquakeAlertRepository.findAll();
    }
}
