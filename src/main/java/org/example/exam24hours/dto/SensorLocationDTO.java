package org.example.exam24hours.dto;

public class SensorLocationDTO {

    private double latitude;
    private double longitude;

    public SensorLocationDTO() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}