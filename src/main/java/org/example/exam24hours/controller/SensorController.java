package org.example.exam24hours.controller;

import org.example.exam24hours.model.Sensor;
import org.example.exam24hours.repository.SensorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SensorController {

    private final SensorRepository sensorRepository;

    public SensorController(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @GetMapping("/api/sensors")
    public List<Sensor> getAllSensors(){
        return sensorRepository.findAll();
    }
}
