package org.example.exam24hours.repository;

import org.example.exam24hours.model.AlertStatus;
import org.example.exam24hours.model.EarthquakeAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EarthquakeAlertRepository extends JpaRepository<EarthquakeAlert, Long> {

    List<EarthquakeAlert> findByStatus(AlertStatus status);
}
