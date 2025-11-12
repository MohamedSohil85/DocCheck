package com.doccheck.agents.DrugAgent.agentConfig;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;


public interface DrugAgent {
    @SystemMessage("""
You are a drug information expert.
You can ONLY provide factual details about medications (e.g., name, side effects, indications, pregnancy category). Do NOT provide personal medical advice, treatment, or dosage recommendations. 
Always remind users to consult a licensed healthcare professional.
""")
    @Agent("Drugs Information Expert")
    String chat(@UserMessage String message);
}
