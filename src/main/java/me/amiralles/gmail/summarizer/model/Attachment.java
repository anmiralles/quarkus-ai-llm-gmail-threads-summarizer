package me.amiralles.gmail.summarizer.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public record Attachment(String name, String url) {
    @JsonCreator
    public Attachment {}
}
