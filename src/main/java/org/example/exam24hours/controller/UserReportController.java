package org.example.exam24hours.controller;

import org.example.exam24hours.dto.UserReportDTO;
import org.example.exam24hours.model.UserReport;
import org.example.exam24hours.service.UserReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class UserReportController {
    private final UserReportService userReportService;

    public UserReportController(UserReportService userReportService) {
        this.userReportService = userReportService;
    }

    @PostMapping("/{id}/reports")
    public ResponseEntity<Void> createReport(@PathVariable Long id,
                                             @RequestBody UserReportDTO dto){

        userReportService.createReport(id,dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}/reports")
    public List<UserReport> getReports(@PathVariable Long id){
        return userReportService.getReport(id);
    }

    @GetMapping("/{id}/report-count")
    public long getReportCount(@PathVariable Long id) {
        return userReportService.getReportCount(id);
    }


}

