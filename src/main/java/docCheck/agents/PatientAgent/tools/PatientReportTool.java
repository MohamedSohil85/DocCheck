package docCheck.agents.PatientAgent.tools;

import docCheck.entity.PatientReport;
import docCheck.persistence.PatientReportRepository;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

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
    @Tool("find Patient by his Name and his Date of Birth")
    public PatientReport findPatient(@P("get Patient By his Name")String name, @P("get Patient by his Date of Birth")LocalDate dateOfBirth){
        return patientReportRepository.findPatientReportByPatientNameEqualsIgnoreCaseAndDateOfBirth(name, dateOfBirth).orElseThrow(()-> new ResourceNotFoundException("Patient with Name :"+name+" not found"));
    }

    //find lab Result of Patient by Name

    @Tool("get Results of Lab by Patient Name ")
    public List findLabResult(@P("find Result of Labs By Name of Patient")String name){
        return patientReportRepository.findAll().stream().filter(patientReport -> patientReport.getPatientName().equalsIgnoreCase(name)).map(PatientReport::getLabResults).toList();
    }
}
