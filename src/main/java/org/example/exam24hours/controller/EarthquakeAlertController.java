package org.example.exam24hours.controller;

import org.example.exam24hours.dto.UpdateStatusDTO;
import org.example.exam24hours.model.EarthquakeAlert;
import org.example.exam24hours.repository.EarthquakeAlertRepository;
import org.example.exam24hours.service.EarthquakeAlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class EarthquakeAlertController {

    private final EarthquakeAlertService earthquakeAlertService;

    public EarthquakeAlertController(EarthquakeAlertService earthquakeAlertService) {
        this.earthquakeAlertService = earthquakeAlertService;
    }

    @GetMapping()
    public List<EarthquakeAlert> getAllAlerts(){
        return earthquakeAlertService.getAllAlerts();
    }

    @GetMapping("/{id}")
    public EarthquakeAlert getAlert(@PathVariable Long id) {
        return earthquakeAlertService.getAlert(id);
    }

    @GetMapping("/active")
    public List<EarthquakeAlert> getActiveAlerts(){
        return earthquakeAlertService.getActiveAlerts();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestBody UpdateStatusDTO dto){
        earthquakeAlertService.updateStatus(id, dto.getStatus());

        return ResponseEntity.ok().build();
    }
}
