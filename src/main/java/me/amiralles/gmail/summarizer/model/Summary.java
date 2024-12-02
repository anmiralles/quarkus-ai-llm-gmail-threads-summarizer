package me.amiralles.gmail.summarizer.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public record Summary(String summary, List<Resource> resources) {
    @JsonCreator
    public Summary {}
}

