package org.example.exam24hours.service;

import org.example.exam24hours.model.AlertStatus;
import org.example.exam24hours.model.EarthquakeAlert;
import org.example.exam24hours.repository.EarthquakeAlertRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EarthquakeAlertService {
    private final EarthquakeAlertRepository earthquakeAlertRepository;

    public EarthquakeAlertService(EarthquakeAlertRepository earthquakeAlertRepository) {
        this.earthquakeAlertRepository = earthquakeAlertRepository;
    }

    public List<EarthquakeAlert> getAllAlerts(){
        return earthquakeAlertRepository.findAll();
    }

    public EarthquakeAlert getAlert(Long id){
        return earthquakeAlertRepository.findById(id)
                .orElseThrow();
    }

    public List<EarthquakeAlert> getActiveAlerts(){
        return earthquakeAlertRepository.findByStatus(AlertStatus.ACTIVE);
    }


    public void updateStatus(Long alertId, AlertStatus newStatus) {
        EarthquakeAlert alert = earthquakeAlertRepository
                .findById(alertId)
                .orElseThrow();

        AlertStatus currentStatus = alert.getStatus();

        boolean validTransition = false;

        if (currentStatus == AlertStatus.UNDER_REVIEW &&
                (newStatus == AlertStatus.ACTIVE || newStatus == AlertStatus.FALSE_ALARM)) {

            validTransition = true;
        }
        if (currentStatus == AlertStatus.ACTIVE && newStatus == AlertStatus.NOT_ACTIVE) {

            validTransition = true;
        }
        if (!validTransition) {
            throw new IllegalArgumentException("Invalid status transition");
        }
        alert.setStatus(newStatus);

        earthquakeAlertRepository.save(alert);
    }
}
