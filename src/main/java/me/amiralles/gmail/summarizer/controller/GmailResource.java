package me.amiralles.gmail.summarizer.controller;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.Thread;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import me.amiralles.gmail.summarizer.model.Summary;
import me.amiralles.gmail.summarizer.service.GmailService;
import me.amiralles.gmail.summarizer.service.SummarizerAiService;

import java.util.ArrayList;
import java.util.List;

@Path("/gmail")
public class GmailResource {

    @Inject
    GmailService gmailService;

    @Inject
    SummarizerAiService summarizerAiService;

    @GET
    @Path("/summarize")
    public List<Summary> summarizeThreads(@QueryParam("subject") String subject) throws Exception {
        List<Summary> summaries = new ArrayList<>();

        // Get threads of emails by subject
        List<Thread> threads = gmailService.getThreads(subject);

        if(threads!=null && !threads.isEmpty()) {
            for(Thread thread: threads) {
                Summary summary;

                // Get messages of thread
                List<Message> messages = gmailService.getMessages(thread);
                // Get messages content
                List<String> messagesContent = gmailService.getMessagesContent(messages);
                // Summarize messages content
                summaries.add(summarizerAiService.summarizeThread(messagesContent, "english"));
            }
        }

        return summaries;
    }
}
