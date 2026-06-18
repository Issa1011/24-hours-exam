package org.example.exam24hours.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class UserReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int intensity;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "alert_id")
    private EarthquakeAlert earthquakeAlert;

    public UserReport(){
    }

    public UserReport(int intensity) {
        this.intensity = intensity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public EarthquakeAlert getEarthquakeAlert() {
        return earthquakeAlert;
    }

    public void setEarthquakeAlert(EarthquakeAlert earthquakeAlert) {
        this.earthquakeAlert = earthquakeAlert;
    }
}
