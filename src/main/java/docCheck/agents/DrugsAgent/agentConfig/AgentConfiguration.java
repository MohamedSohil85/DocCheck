package docCheck.agents.DrugsAgent.agentConfig;

import docCheck.agents.DrugsAgent.tools.DrugInfoTool;
import docCheck.agents.DrugsAgent.tools.DrugsDescriptionTool;
import docCheck.agents.DrugsAgent.tools.DrugsInteractionTool;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.agent.tool.ToolSpecifications;
import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.agentic.supervisor.SupervisorAgent;
import dev.langchain4j.agentic.supervisor.SupervisorResponseStrategy;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.Duration;
import java.util.List;

@Configuration
public class AgentConfiguration {

    private static final String OLLAMA_URL = "http://localhost:11434";





    @Bean
    public ChatModel languageModel() {
        return OllamaChatModel.builder()
                .baseUrl(OLLAMA_URL)
                .modelName("llama3.2")
                .temperature(0.7)
                .timeout(Duration.ofMinutes(5))
                .maxRetries(3)
                .build();
    }



    @Bean
    public DrugAgent drugAgent(DrugInfoTool drugInfoTool, DrugsInteractionTool drugsInteractionTool, DrugsDescriptionTool drugsDescription, ChatModel languageModel) {



        List<ToolSpecification> tools = ToolSpecifications.toolSpecificationsFrom(
                List.of(drugInfoTool, drugsInteractionTool, drugsDescription)
        );

        return AgenticServices.agentBuilder(DrugAgent.class)
                .chatModel(languageModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .tools(tools)
                .build();


    }


    @Bean
    public SupervisorAgent supervisorAgent(DrugAgent drugAgent,ChatModel chatModel) {



        return AgenticServices.supervisorBuilder()
                .chatModel(chatModel)
                .subAgents(drugAgent)
                .responseStrategy(SupervisorResponseStrategy.SUMMARY)
                .build();


    }

}

