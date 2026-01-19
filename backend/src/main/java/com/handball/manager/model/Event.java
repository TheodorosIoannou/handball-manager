package com.handball.manager.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public class Event {
    private Long id;
    
    @NotNull(message = "Event type is required")
    private EventType type;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Location is required")
    private String location;
    
    @NotNull(message = "Date and time is required")
    private LocalDateTime dateTime;
    
    @NotNull(message = "Duration is required")
    @Positive(message = "Duration must be greater than zero")
    private Integer durationMinutes;
    
    private String opponent;
    private String notes;
    private MatchResult matchResult;

    public enum EventType {
        TRAINING,
        MATCH
    }

    // Constructors
    public Event() {}

    public Event(Long id, EventType type, String title, String location, 
                 LocalDateTime dateTime, Integer durationMinutes, String opponent, String notes) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.location = location;
        this.dateTime = dateTime;
        this.durationMinutes = durationMinutes;
        this.opponent = opponent;
        this.notes = notes;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public MatchResult getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(MatchResult matchResult) {
        this.matchResult = matchResult;
    }
}