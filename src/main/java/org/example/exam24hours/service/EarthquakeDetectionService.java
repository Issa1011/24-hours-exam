package org.example.exam24hours.service;

import org.example.exam24hours.model.AlertStatus;
import org.example.exam24hours.model.EarthquakeAlert;
import org.example.exam24hours.model.SensorReading;
import org.example.exam24hours.repository.EarthquakeAlertRepository;
import org.example.exam24hours.repository.SensorReadingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class EarthquakeDetectionService {

    private final EarthquakeAlertRepository earthquakeAlertRepository;
    private final SensorReadingRepository sensorReadingRepository;
    private final EpicenterEstimator epicenterEstimator;
    private final GeocodingService geocodingService;

    public EarthquakeDetectionService(
            EarthquakeAlertRepository earthquakeAlertRepository,
            SensorReadingRepository sensorReadingRepository,
            EpicenterEstimator epicenterEstimator,
            GeocodingService geocodingService) {

        this.earthquakeAlertRepository = earthquakeAlertRepository;
        this.sensorReadingRepository = sensorReadingRepository;
        this.epicenterEstimator = epicenterEstimator;
        this.geocodingService = geocodingService;
    }

    public void detectEarthquake(List<SensorReading> readings) {

        if (readings == null || readings.size() != 3) {
            return;
        }

        try {
            double[] epicenter = epicenterEstimator.estimateEpicenter(readings);

            double totalMagnitude = 0;

            for (SensorReading r : readings) {
                totalMagnitude += r.getEstimatedMagnitude();
            }

            double averageMagnitude = totalMagnitude / readings.size();

            String areaName = geocodingService.getAreaName(epicenter[0],epicenter[1]);

            EarthquakeAlert alert = new EarthquakeAlert();
            alert.setEpicenterLatitude(epicenter[0]);
            alert.setEpicenterLongitude(epicenter[1]);
            alert.setEstimatedMagnitude(averageMagnitude);
            alert.setAreaName(areaName);
            alert.setCreatedAt(LocalDateTime.now());
            alert.setStatus(AlertStatus.UNDER_REVIEW);

            alert = earthquakeAlertRepository.save(alert);

            for (SensorReading r : readings) {
                r.setEarthquakeAlert(alert);
                sensorReadingRepository.save(r);
            }

            System.out.println("Alert oprettet");

        }catch (Exception e){
            System.out.println("Epicenter beregning fejlede");

        }
    }
}