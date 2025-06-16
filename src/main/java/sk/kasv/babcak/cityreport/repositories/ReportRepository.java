package sk.kasv.babcak.cityreport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sk.kasv.babcak.cityreport.models.Report;
import sk.kasv.babcak.cityreport.models.Report.ReportPriority;
import sk.kasv.babcak.cityreport.models.Report.ReportStatus;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByCitizenId(Long citizenId);
    List<Report> findByDepartmentId(Long departmentId);
    List<Report> findByStatus(ReportStatus status);
    List<Report> findByPriority(ReportPriority priority);

    @Query("SELECT r FROM Report r WHERE r.citizen.id = :citizenId AND r.status = :status")
    List<Report> findByCitizenIdAndStatus(@Param("citizenId") Long citizenId, @Param("status") ReportStatus status);

    @Query("SELECT r FROM Report r JOIN r.assignedTechnicians t WHERE t.id = :technicianId")
    List<Report> findByAssignedTechnicianId(@Param("technicianId") Long technicianId);
}