package docCheck.persistence;

import docCheck.entity.PatientReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PatientReportRepository extends JpaRepository<PatientReport, Long> {

    Optional<PatientReport> findPatientReportByPatientNameEqualsIgnoreCaseAndDateOfBirth(String patientName, LocalDate dateOfBirth);
}
