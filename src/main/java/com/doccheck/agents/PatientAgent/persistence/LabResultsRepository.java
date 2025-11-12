package com.doccheck.agents.PatientAgent.persistence;

import com.doccheck.agents.PatientAgent.entity.LabResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabResultsRepository extends JpaRepository<LabResults, Long> {
}
