package docCheck.agents.TreatmentPlanAgent.agentConfig;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

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
}
