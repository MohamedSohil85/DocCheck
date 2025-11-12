package com.doccheck.agents.TreatmentPlanAgent.Tools;




import com.doccheck.agents.DiseaseAgent.agentConfig.DiseaseAgent;
import com.doccheck.agents.DrugAgent.agentConfig.DrugAgent;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class TreatmentPlanTool {

    private final DiseaseAgent diseaseAgent;
    private final DrugAgent drugAgent;

    public TreatmentPlanTool(DiseaseAgent diseaseAgent, DrugAgent drugAgent) {
        this.diseaseAgent = diseaseAgent;
        this.drugAgent = drugAgent;
    }

    @Tool("Get disease information and standard treatment guidance")
    public String getDiseaseInfo(@P("disease name") String diseaseName) {
        return diseaseAgent.chat("Provide detailed info and standard treatment for " + diseaseName);
    }

    @Tool("Get safe medications for a disease while considering allergies and current drugs")
    public String getSafeMedications(@P("disease") String disease,
                                     @P("allergies") String allergies,
                                     @P("current medications") String currentMeds) {
        return drugAgent.chat(String.format("""
                Suggest safe medications for %s.
                Avoid drugs that conflict with these allergies: %s
                and current medications: %s
                """, disease, allergies, currentMeds));
    }
    @Tool("provide disease information , treatment , and Drugs according to reported symptoms")
    public String getTreatment(@P("symptoms")String symptoms) {
        return diseaseAgent.chat(String.format("Provide Disease information and standard treatment and Drugs  %s.", symptoms));
    }
}

