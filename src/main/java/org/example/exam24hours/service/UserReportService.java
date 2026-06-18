package org.example.exam24hours.service;

import org.example.exam24hours.dto.UserReportDTO;
import org.example.exam24hours.model.EarthquakeAlert;
import org.example.exam24hours.model.UserReport;
import org.example.exam24hours.repository.EarthquakeAlertRepository;
import org.example.exam24hours.repository.UserReportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserReportService {
    private final UserReportRepository userReportRepository;
    private final EarthquakeAlertRepository earthquakeAlertRepository;

    public UserReportService(UserReportRepository userReportRepository,
                             EarthquakeAlertRepository earthquakeAlertRepository) {
        this.userReportRepository = userReportRepository;
        this.earthquakeAlertRepository = earthquakeAlertRepository;
    }

    public void createReport(Long alertId, UserReportDTO dto) {
        EarthquakeAlert alert = earthquakeAlertRepository
                .findById(alertId)
                .orElseThrow();

        UserReport report = new UserReport();
        report.setIntensity(dto.getIntensity());
        report.setCreatedAt(LocalDateTime.now());
        report.setEarthquakeAlert(alert);

        userReportRepository.save(report);
    }

    public List<UserReport> getReport(Long alertId) {
        EarthquakeAlert alert = earthquakeAlertRepository
                .findById(alertId)
                .orElseThrow();

        return  userReportRepository.findByEarthquakeAlert(alert);
    }

    public long getReportCount(Long alertId) {
        EarthquakeAlert alert = earthquakeAlertRepository
                .findById(alertId)
                .orElseThrow();

        return userReportRepository.countByEarthquakeAlert(alert);
    }

}