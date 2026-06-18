package org.example.exam24hours.controller;

import org.example.exam24hours.dto.UpdateStatusDTO;
import org.example.exam24hours.service.EarthquakeAlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alerts")
public class EarthquakeController {
    private final EarthquakeAlertService earthquakeAlertService;

    public EarthquakeController(EarthquakeAlertService earthquakeAlertService) {
        this.earthquakeAlertService = earthquakeAlertService;
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestBody UpdateStatusDTO dto) {
        earthquakeAlertService.updateStatus(id, dto.getStatus());
        return ResponseEntity.ok().build();
    }
}
