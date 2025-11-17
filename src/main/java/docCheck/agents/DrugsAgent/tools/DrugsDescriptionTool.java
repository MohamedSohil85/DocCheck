package docCheck.agents.DrugsAgent.tools;

import docCheck.entity.DrugsDescription;
import docCheck.persistence.DrugsDescriptionRepository;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class DrugsDescriptionTool {

    private final DrugsDescriptionRepository descriptionRepository;


    public DrugsDescriptionTool(DrugsDescriptionRepository descriptionRepository) {
        this.descriptionRepository = descriptionRepository;
    }

    @Tool("show Description of Drug by Name")
    public String describeByDrugsName(@P("Name of Drug") String drugsName) {
        return descriptionRepository
                .findAll()
                .stream()
                .map(DrugsDescription::getDrugsDescription)
                .filter(drugsDescription -> drugsDescription.startsWith(drugsName))
                .findAny().orElse("Drug not found !");
    }
}
