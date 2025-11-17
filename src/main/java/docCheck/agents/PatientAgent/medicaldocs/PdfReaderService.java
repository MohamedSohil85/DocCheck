package docCheck.agents.PatientAgent.medicaldocs;

import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;

public class PdfReaderService {

    private static final String OLLAMA_URL = "http://localhost:11434";


    public ChatModel languageModel() {
        return OllamaChatModel.builder()
                .baseUrl(OLLAMA_URL)
                .modelName("llama3.2")
                .temperature(0.7)
                .timeout(Duration.ofMinutes(5))
                .maxRetries(3)
                .build();
    }

    public String askQuestion(MultipartFile file, String question) throws IOException {


        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();



        // 1. EmbeddingStore + EmbeddingModel
        EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

// 2. Ingestor with Text-Dokument
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(DocumentSplitters.recursive(300, 50))
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();

        ingestor.ingest(Document.from(text));// only one Document


        // Chat-Setup
        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                .maxMessages(20)
                .build();
        chatMemory.add(new SystemMessage("You are an AI assistant. Only use the information from the provided PDF file to answer questions. " +
                "Do not make up or invent any information."));
        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(4)
                .minScore(0.75)//
                .build();

// Create a RetrievalAugmentor using your ContentRetriever
        RetrievalAugmentor retrievalAugmentor = DefaultRetrievalAugmentor.builder()
                .contentRetriever(contentRetriever)
                .build();

        var agent = AgenticServices.agentBuilder(ReportAnalysisAgent.class)
                .chatModel(languageModel())
                .retrievalAugmentor(retrievalAugmentor)
                .build();

        // --- Ask the question ---
        return agent.chat(question);


    }


}
