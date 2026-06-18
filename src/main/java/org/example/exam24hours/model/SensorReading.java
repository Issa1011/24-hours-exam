package org.example.exam24hours.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class SensorReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String readingId;
    private double estimatedDistanceToEpicenterKm;
    private double estimatedMagnitude;
    private LocalDateTime recordedAt;


    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;


    @ManyToOne
    @JoinColumn(name = "alert_id")
    @JsonBackReference
    private EarthquakeAlert earthquakeAlert;

    public SensorReading(){

    }

    public SensorReading(String readingId,
                         double estimatedDistanceToEpicenterKm,
                         double estimatedMagnitude, LocalDateTime recordedAt) {
        this.readingId = readingId;
        this.estimatedDistanceToEpicenterKm = estimatedDistanceToEpicenterKm;
        this.estimatedMagnitude = estimatedMagnitude;
        this.recordedAt = recordedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReadingId() {
        return readingId;
    }

    public void setReadingId(String readingId) {
        this.readingId = readingId;
    }

    public double getEstimatedDistanceToEpicenterKm() {
        return estimatedDistanceToEpicenterKm;
    }

    public void setEstimatedDistanceToEpicenterKm(double estimatedDistanceToEpicenterKm) {
        this.estimatedDistanceToEpicenterKm = estimatedDistanceToEpicenterKm;
    }

    public double getEstimatedMagnitude() {
        return estimatedMagnitude;
    }

    public void setEstimatedMagnitude(double estimatedMagnitude) {
        this.estimatedMagnitude = estimatedMagnitude;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public EarthquakeAlert getEarthquakeAlert() {
        return earthquakeAlert;
    }

    public void setEarthquakeAlert(EarthquakeAlert earthquakeAlert) {
        this.earthquakeAlert = earthquakeAlert;
    }
}
