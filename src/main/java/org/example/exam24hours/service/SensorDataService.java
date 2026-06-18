package org.example.exam24hours.service;

import org.example.exam24hours.dto.SensorReadingDTO;
import org.example.exam24hours.model.Sensor;
import org.example.exam24hours.model.SensorReading;
import org.example.exam24hours.repository.SensorReadingRepository;
import org.example.exam24hours.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SensorDataService {
    private final SensorRepository sensorRepository;
    private final SensorReadingRepository sensorReadingRepository;
    private final EarthquakeDetectionService earthquakeDetectionService;

    public SensorDataService(SensorRepository sensorRepository,
                             SensorReadingRepository sensorReadingRepository,
                             EarthquakeDetectionService earthquakeDetectionService) {
        this.sensorRepository = sensorRepository;
        this.sensorReadingRepository = sensorReadingRepository;
        this.earthquakeDetectionService = earthquakeDetectionService;
    }

    public void saveReadings(List<SensorReadingDTO> readings) {
        List<SensorReading> currentRequestReading = new ArrayList<>();

        for (SensorReadingDTO dto : readings) {
            Sensor sensor = sensorRepository
                    .findBySensorId(dto.getSensorId())
                    .orElse(null);

            if (sensor == null) {

                sensor = new Sensor();
                sensor.setSensorId(dto.getSensorId());
                sensor.setLatitude(dto.getSensorLocation().getLatitude());
                sensor.setLongitude(dto.getSensorLocation().getLongitude());

                System.out.println("Gemmer sensor: " + dto.getSensorId());
                sensor = sensorRepository.save(sensor);
            }

            SensorReading sensorReading = new SensorReading();
            sensorReading.setReadingId(dto.getReadingId());
            sensorReading.setEstimatedMagnitude(dto.getEstimatedMagnitude());
            sensorReading.setEstimatedDistanceToEpicenterKm(dto.getEstimatedDistanceToEpicenterKm());
            sensorReading.setRecordedAt(dto.getRecordedAt());

            sensorReading.setSensor(sensor);
            sensorReadingRepository.save(sensorReading);

            currentRequestReading.add(sensorReading);
        }
        if (currentRequestReading.size() == 3) {
            earthquakeDetectionService.detectEarthquake(currentRequestReading);
        }

    }
}



