package sk.kasv.babcak.cityreport.dto;

import lombok.Data;
import sk.kasv.babcak.cityreport.models.Report.ReportPriority;
import sk.kasv.babcak.cityreport.models.Report.ReportStatus;

@Data
public class UpdateReportRequest {
    private String title;
    private String description;
    private String location;
    private ReportStatus status;
    private ReportPriority priority;
    private Long departmentId;
}