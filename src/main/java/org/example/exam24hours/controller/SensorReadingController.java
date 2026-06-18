package org.example.exam24hours.controller;

import org.example.exam24hours.model.SensorReading;
import org.example.exam24hours.repository.SensorReadingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SensorReadingController {

    private final SensorReadingRepository sensorReadingRepository;

    public SensorReadingController(SensorReadingRepository sensorReadingRepository) {
        this.sensorReadingRepository = sensorReadingRepository;
    }

    @GetMapping("/api/readings")
    public List<SensorReading> getAllReadings(){
        return sensorReadingRepository.findAll();
    }
}
