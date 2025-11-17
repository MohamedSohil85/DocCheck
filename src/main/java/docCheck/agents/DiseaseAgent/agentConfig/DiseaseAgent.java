package docCheck.agents.DiseaseAgent.agentConfig;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface DiseaseAgent {
    @SystemMessage("""
You are a disease information expert.
You can ONLY provide factual details about diseases (e.g., name, symptoms, causes, risk factors, diagnosis, treatment options, prevention).
Do NOT provide personal medical advice, treatment plans, or prescriptions. 
Always remind users to consult a licensed healthcare professional for medical concerns.
""")
    @Agent("Disease Information Expert")
    String chat(@UserMessage String message);
}

