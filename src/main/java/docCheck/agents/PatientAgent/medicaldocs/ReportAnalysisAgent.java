package docCheck.agents.PatientAgent.medicaldocs;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;


public interface ReportAnalysisAgent {

    @SystemMessage("""
        You are a medical document analysis agent. 
        Only answer using the information from the provided PDF.
        If information is missing, say: 'Information not found in the PDF.'
    """)
    @Agent
    String chat(@UserMessage String question);
}
