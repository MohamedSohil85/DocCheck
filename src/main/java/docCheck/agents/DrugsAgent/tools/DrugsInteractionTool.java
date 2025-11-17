package docCheck.agents.DrugsAgent.tools;

import docCheck.entity.DrugsInteraction;
import docCheck.persistence.DrugsInteractionRepository;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class DrugsInteractionTool {

    private final DrugsInteractionRepository interactionRepository;


    public DrugsInteractionTool(DrugsInteractionRepository interactionRepository) {
        this.interactionRepository = interactionRepository;
    }

    @Tool("The effect of the first drug or treatment on the activity or efficiency of the second drug")
    public String DrugsInteraction(@P("name of first Drug") String firstDrug,@P("name of second Drug") String secondDrug) {

        Predicate<DrugsInteraction>drugsInteractionPredicate= drugsInteraction -> drugsInteraction.getFirstDrug().equalsIgnoreCase(firstDrug) && drugsInteraction.getSecondDrug().equalsIgnoreCase(secondDrug);

        return interactionRepository
                .findAll()
                .stream()
                .filter(drugsInteractionPredicate)
                .map(DrugsInteraction::getInteractionDescription)
                .findAny().orElse("can not find drug in database");
    }
}
