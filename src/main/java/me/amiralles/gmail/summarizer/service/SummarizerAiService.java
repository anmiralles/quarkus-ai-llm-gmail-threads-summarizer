package me.amiralles.gmail.summarizer.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import me.amiralles.gmail.summarizer.model.Summary;

import java.util.List;

@RegisterAiService(chatMemoryProviderSupplier = // disable chat memory
RegisterAiService.NoChatMemoryProviderSupplier.class)
public interface SummarizerAiService {

    @SystemMessage("""
                You are an advanced email summarizer AI. Your job is to summarize email threads accurately and concisely, while extracting any referenced resources, such as documents, links, or key topics.
                
                Your output must always be a JSON document and strictly follow this structure:
                {
                    "summary": "A concise and detailed summary of the email thread.",
                    "resources": [
                        {
                            "topic": "A brief description of the resource or its purpose",
                            "link": "The URL or reference, if available"
                        }
                    ]
                }
                If no resources are mentioned, return an empty 'resources' array.
                """)
    @UserMessage("""
                Your task is to provide a detailed summary of the following email threads delimited by '---'.
                Do not include the delimiter in your summary.
                
                Please return a JSON document as follows:
                {
                    "summary": "A concise and detailed summary of the email thread.",
                    "resources": [
                        {
                            "topic": "A brief description of the resource or its purpose",
                            "link": "The URL or reference, if available"
                        }
                    ]
                }
                Ensure that:
                1. The summary captures all key points and actions discussed in the thread.
                2. The 'resources' field lists each distinct resource discussed, grouped by topic and link where possible.
                3. If no resources are present, the 'resources' field should be an empty array.
                
                ---
                {emailContents}
                ---
                """)
    Summary summarizeThread(List<String> emailContents, String language);
}
