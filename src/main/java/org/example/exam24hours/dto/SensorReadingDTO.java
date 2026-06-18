package org.example.exam24hours.dto;

import java.time.LocalDateTime;

public class SensorReadingDTO {

    private String readingId;
    private String sensorId;
    private SensorLocationDTO sensorLocation;

    private double estimatedDistanceToEpicenterKm;
    private double estimatedMagnitude;

    private LocalDateTime recordedAt;

    public SensorReadingDTO() {
    }

    public String getReadingId() {
        return readingId;
    }

    public void setReadingId(String readingId) {
        this.readingId = readingId;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public SensorLocationDTO getSensorLocation() {
        return sensorLocation;
    }

    public void setSensorLocation(SensorLocationDTO sensorLocation) {
        this.sensorLocation = sensorLocation;
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
}