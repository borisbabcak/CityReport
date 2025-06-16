package sk.kasv.babcak.cityreport.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kasv.babcak.cityreport.dto.CreateReportRequest;
import sk.kasv.babcak.cityreport.dto.UpdateReportRequest;
import sk.kasv.babcak.cityreport.models.Report;
import sk.kasv.babcak.cityreport.repositories.ReportRepository;
import sk.kasv.babcak.cityreport.services.ReportService;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@Tag(name = "Report Controller", description = "API for managing municipal incentives")
public class ReportController {

    @Autowired private ReportRepository reportRepository;
    @Autowired private ReportService reportService;

    @Operation(summary = "Get a list of all reports")
    @GetMapping
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @Operation(summary = "Get report by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Long id) {
        return reportRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create new report")
    @PostMapping
    public ResponseEntity<?> createReport(@RequestBody CreateReportRequest request) {
        try {
            Report savedReport = reportService.createReport(request);
            return new ResponseEntity<>(savedReport, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating report: " + e.getMessage());
        }
    }

    @Operation(summary = "Update an existing report")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReport(@PathVariable Long id, @RequestBody UpdateReportRequest request) {
        try {
            Report updatedReport = reportService.updateReport(id, request);
            return ResponseEntity.ok(updatedReport);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating report: " + e.getMessage());
        }
    }

    @Operation(summary = "Delete report")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        if (!reportRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reportRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Assign a technician to a report")
    @PostMapping("/{reportId}/assign-technician/{technicianId}")
    public ResponseEntity<?> assignTechnician(
            @Parameter(description = "ID of report") @PathVariable Long reportId,
            @Parameter(description = "ID of technician") @PathVariable Long technicianId) {
        try {
            Report updatedReport = reportService.assignTechnician(reportId, technicianId);
            return ResponseEntity.ok(updatedReport);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}