package org.example.exam24hours.controller;

import org.example.exam24hours.dto.SensorLocationDTO;
import org.example.exam24hours.dto.SensorReadingDTO;
import org.example.exam24hours.service.SensorDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensor-data")
public class SensorDataController {

    private final SensorDataService sensorDataService;

    public SensorDataController(SensorDataService sensorDataService){
        this.sensorDataService = sensorDataService;
    }

    @PostMapping
    public ResponseEntity<Void> saveSensorReading(
            @RequestBody List<SensorReadingDTO> readings) {

        if (readings == null || readings.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        sensorDataService.saveReadings(readings);

        return ResponseEntity.ok().build();
    }
}

