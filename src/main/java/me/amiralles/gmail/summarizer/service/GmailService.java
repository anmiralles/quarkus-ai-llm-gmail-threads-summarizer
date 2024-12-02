package me.amiralles.gmail.summarizer.service;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import com.google.api.services.gmail.model.Thread;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import me.amiralles.gmail.summarizer.config.GmailConfig;
import me.amiralles.gmail.summarizer.model.Attachment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GmailService {

    @Inject
    GmailConfig gmailConfig;

    public List<Thread> getThreads(String subject) {
        try {
            return gmailConfig
                    .gmailService()
                    .users().threads().list("me")
                    .setQ(subject)
                    .execute().getThreads();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Message> getMessages(Thread thread) {
        try {
            return gmailConfig
                    .gmailService()
                    .users().threads().get("me", thread.getId())
                    .execute().getMessages();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getMessagesContent(List<Message> messages) {
        return messages.stream().map(Message::getSnippet).collect(Collectors.toList());
    }

    public List<Attachment> getMessagesAttachments(List<Message> messages) {
        List<Attachment> attachments = new ArrayList<>();

        messages.forEach(message -> {
            // Inspect the payload
            MessagePart payload = message.getPayload();
            List<MessagePart> parts = payload.getParts();

            if (parts != null) {
                for (MessagePart part : parts) {
                    if (part.getFilename() != null && !part.getFilename().isEmpty()) {
                        String attachmentId = part.getBody().getAttachmentId();
                        String messageId = message.getId();
                        String attachmentUrl = "https://gmail.googleapis.com/gmail/v1/users/me/messages/"
                                + messageId + "/attachments/" + attachmentId;

//                        HttpRequest request = HttpRequest.newBuilder()
//                                .uri(URI.create(attachmentUrl))
//                                .header("Authorization", "Bearer " + gmailService.getCredentials().getAccessToken())
//                                .GET()
//                                .build();
                        attachments.add(new Attachment(part.getFilename(), attachmentUrl));
                    }
                }
            }
        });
        return attachments;
    }



}
