package com.doccheck.agents.DiseaseAgent.tools;

import com.doccheck.agents.DiseaseAgent.entity.DiseaseSymptomsInfo;
import com.doccheck.agents.DiseaseAgent.persistence.DiseaseSymptomsInfoRepository;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiseaseInfoTool {

    private final DiseaseSymptomsInfoRepository symptomsInfoRepository;

    public DiseaseInfoTool(DiseaseSymptomsInfoRepository symptomsInfoRepository) {
        this.symptomsInfoRepository = symptomsInfoRepository;
    }
    @Tool("find Disease by Name , and retrieve all Infos about it")
    public String getDiseaseInfoByName(@P("Name of Disease") String diseaseName) {
        return symptomsInfoRepository.findByDisease(diseaseName)
                .map(this::formatDiseaseInfo)
                .orElse("No information found for the disease: " + diseaseName);
    }

    @Tool("find Diseases by symptoms")
    public List<String>getDiseasesBySymptoms(@P("symptoms") String symptoms) {
       return symptomsInfoRepository.findBySymptomsContainingIgnoreCase(symptoms)
               .stream().map(DiseaseSymptomsInfo::getDisease).toList();
    }
    @Tool("Treatment of Disease")
    public String getTreatmentOfDisease(@P("Name of Disease")String disease){
        return symptomsInfoRepository.findAll()
                .stream()
                .filter(diseaseSymptomsInfo -> diseaseSymptomsInfo.getDisease().equalsIgnoreCase(disease))
                .map(DiseaseSymptomsInfo::getTreatment).findFirst().orElse("there is no Disease with name "+disease);
    }
    private String formatDiseaseInfo(DiseaseSymptomsInfo d) {
        return String.format("""
                Disease: %s
                Overview: %s
                Symptoms: %s
                Causes: %s
                Risk Factors: %s
                Diagnosis: %s
                Treatment: %s
                Medication: %s
                Remedies: %s
                Link: %s
                """,
                d.getDisease(),
                d.getOverview(),
                d.getSymptoms(),
                d.getCauses(),
                d.getRiskFactors(),
                d.getDiagnosis(),
                d.getTreatment(),
                d.getMedication(),
                d.getRemedies(),
                d.getLink());

}
}
