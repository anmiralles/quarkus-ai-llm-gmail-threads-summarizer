package me.amiralles.gmail.summarizer.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public record Resource (String topic, String link) {
    @JsonCreator
    public Resource {}
}
