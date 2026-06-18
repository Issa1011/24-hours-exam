package org.example.exam24hours;
import org.example.exam24hours.model.SensorReading;
import org.example.exam24hours.repository.EarthquakeAlertRepository;
import org.example.exam24hours.repository.SensorReadingRepository;
import org.example.exam24hours.service.EarthquakeDetectionService;
import org.example.exam24hours.service.EpicenterEstimator;
import org.example.exam24hours.service.GeocodingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

public class EarthquakeDetectionServiceTest {
    private EarthquakeAlertRepository earthquakeAlertRepository;
    private SensorReadingRepository sensorReadingRepository;
    private EpicenterEstimator epicenterEstimator;
    private GeocodingService geocodingService;
    private EarthquakeDetectionService earthquakeDetectionService;

    @BeforeEach
    void setup() {
        earthquakeAlertRepository = mock(EarthquakeAlertRepository.class);
        sensorReadingRepository = mock(SensorReadingRepository.class);
        epicenterEstimator = mock(EpicenterEstimator.class);
        geocodingService = mock(GeocodingService.class);

        earthquakeDetectionService = new EarthquakeDetectionService(
                earthquakeAlertRepository,
                sensorReadingRepository,
                epicenterEstimator,
                geocodingService
        );
    }

    @Test
    void shouldCreateAlertWhenThreeReadingsExist() {
        SensorReading r1 = new SensorReading();
        r1.setEstimatedMagnitude(4.0);

        SensorReading r2 = new SensorReading();
        r2.setEstimatedMagnitude(4.2);

        SensorReading r3 = new SensorReading();
        r3.setEstimatedMagnitude(4.1);

        when(epicenterEstimator.estimateEpicenter(any()))
                .thenReturn(new double[] {55.6, 12.5});

        when(geocodingService.getAreaName(anyDouble(), anyDouble()))
                .thenReturn("Copenhagen");

        earthquakeDetectionService.detectEarthquake(
                List.of(r1, r2, r3)
        );

        verify(earthquakeAlertRepository, times(1))
                .save(any());

        verify(sensorReadingRepository, times(3))
                .save(any());
    }

    @Test
    void shouldNotCreateAlertWhenLessThanThreeReadings(){

        SensorReading r1 = new SensorReading();
        SensorReading r2 = new SensorReading();

        earthquakeDetectionService.detectEarthquake(
                List.of(r1, r2)
        );

        verify(earthquakeAlertRepository, never())
                .save(any());
    }

    @Test
    void shouldHandleEstimatorException(){

        SensorReading r1 = new SensorReading();
        SensorReading r2 = new SensorReading();
        SensorReading r3 = new SensorReading();

        when(epicenterEstimator.estimateEpicenter(any()))
                .thenThrow(new RuntimeException());

        earthquakeDetectionService.detectEarthquake(
                List.of(r1, r2, r3)
        );

        verify(earthquakeAlertRepository, never())
                .save(any());

    }
 }
