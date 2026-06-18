package org.example.exam24hours.repository;

import org.example.exam24hours.model.EarthquakeAlert;
import org.example.exam24hours.model.UserReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReportRepository extends JpaRepository<UserReport, Long> {

    List<UserReport> findByEarthquakeAlert(EarthquakeAlert alert);

    long countByEarthquakeAlert(EarthquakeAlert alert);
}
