package docCheck.agents.DrugsAgent.tools;


import docCheck.entity.DrugsBank;
import docCheck.persistence.DrugsRepository;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DrugInfoTool {

private final DrugsRepository drugsRepository;

    public DrugInfoTool(DrugsRepository drugsRepository) {
        this.drugsRepository = drugsRepository;
    }

@Tool("retrieve all Drags records from the Database")
    public List<DrugsBank>findAllDrugsBank() {
    return drugsRepository.findAll();
}

@Tool("find a Drug Info by Name")
    public String findDrugByName(@P("Name of Drug") String name) {
        return drugsRepository.findAll().stream()
                .filter(drug -> drug.getDrug_name().equalsIgnoreCase(name))
                .findAny()
                .map(drug -> "Name of Drug: " + drug.getDrug_name() + ", " +
                        "Side Effects: " + drug.getSide_effects() + ", " +
                        "Medical Condition: " + drug.getMedical_condition() + ", " +
                        "Description: " + drug.getMedical_condition_description() + ", " +
                        "Pregnancy Category: " + drug.getPregnancy_category())
                .orElse("No drug found with name: " + name);
    }
@Tool("find Drugs by medical condition")
    public List<String> findDrugsByMedicalCondition(@P("medical condition")String medicalCondition) {

    return drugsRepository.findAll().stream()
                .filter(drugsBank -> drugsBank.getMedical_condition().equalsIgnoreCase(medicalCondition))
                .map(drug -> "Name of Drug: " + drug.getDrug_name()+" ,"+
                        "Side Effects: " + drug.getSide_effects() + ", " +
                        "Medical Condition: " + drug.getMedical_condition() + ", " +
                        "Description: " + drug.getMedical_condition_description() +" ,"+
                        "Alcohol: "+drug.getAlcohol()+" ,"+
                        "Pregnancy Category :"+drug.getPregnancy_category()+" ,"+
                        "prescription(Rx) and over-the-counter :"+drug.getRx_otc())
                .toList();

}



}
