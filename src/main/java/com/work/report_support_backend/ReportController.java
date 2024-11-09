/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.work.report_support_backend;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ferdi
 */
@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Report>> createReport(@RequestParam(required = true) String partnerName, @RequestParam(required = false) String project) throws ParseException {

        Report report = new Report();

        if (partnerName == null || partnerName.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Partner name is required", null));
        }

        String normalizedProject = (project == null || project.trim().isEmpty())
                ? "OTHERS"
                : project.trim().toUpperCase();

        switch (normalizedProject) {
            case "MBA" ->
                report = reportService.createReport(partnerName, Project.MBA);
            case "VSI" ->
                report = reportService.createReport(partnerName, Project.VSI);
            default ->
                report = reportService.createReport(partnerName, Project.OTHERS);

        }

        return ResponseEntity.ok(new ApiResponse<>(true, "Successfully added the data", report));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReportSummary>>> getAllReports() {

        List<ReportSummary> reports = reportService.getAllReports();

        if (reports.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "No reports found", reports));
        }

        return ResponseEntity.ok(new ApiResponse<>(true, "Fetched all reports successfully", reports));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<?>> updateReport(@RequestParam(required = true) UUID uid, @RequestParam(required = true) int supportTime) {

        try {
            if (uid == null) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse<>(false, "Id is required", null));
            }

            Report updateReport = reportService.findReportById(uid);

            if (updateReport == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "Report with ID " + uid + " not found", null));
            }

            reportService.endReport(updateReport, supportTime);
            return ResponseEntity.ok(new ApiResponse<>(true, "Update successful", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "An unexpected error occurred", null));
        }
    }

}
