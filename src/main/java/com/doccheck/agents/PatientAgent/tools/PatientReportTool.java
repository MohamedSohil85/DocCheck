package com.doccheck.agents.PatientAgent.tools;

import com.doccheck.agents.PatientAgent.entity.PatientReport;
import com.doccheck.agents.PatientAgent.persistence.PatientReportRepository;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PatientReportTool {

    private final PatientReportRepository patientReportRepository;

    public PatientReportTool(PatientReportRepository patientReportRepository) {
        this.patientReportRepository = patientReportRepository;
    }

    @Tool("find Patient by Id")
    public PatientReport findPatientById(@P("get all information of Patient with Id") Long id) {
        return patientReportRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Patient with Id :"+id+" not found"));
    }

    // find Patient by Name and date of Birth

    //find lab Result of Patient by Name
}
