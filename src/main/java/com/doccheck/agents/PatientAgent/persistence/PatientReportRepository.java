package com.doccheck.agents.PatientAgent.persistence;

import com.doccheck.agents.PatientAgent.entity.PatientReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientReportRepository extends JpaRepository<PatientReport, Long> {
}
