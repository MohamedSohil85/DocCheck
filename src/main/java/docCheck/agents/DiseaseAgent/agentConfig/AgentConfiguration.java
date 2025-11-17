package docCheck.agents.DiseaseAgent.agentConfig;

import docCheck.agents.DrugsAgent.tools.DrugInfoTool;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.agent.tool.ToolSpecifications;
import dev.langchain4j.agentic.AgenticServices;
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
    public DiseaseAgent diseaseAgent(DrugInfoTool drugInfoTool , ChatModel languageModel) {



        List<ToolSpecification> tools = ToolSpecifications.toolSpecificationsFrom(drugInfoTool);

        return AgenticServices.agentBuilder(DiseaseAgent.class)
                .chatModel(languageModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .tools(tools)
                .build();


    }

}
