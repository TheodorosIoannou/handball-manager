package com.handball.manager.model;

import jakarta.validation.constraints.NotNull;

public class Attendance {
    private Long id;
    
    @NotNull(message = "Event ID is required")
    private Long eventId;
    
    @NotNull(message = "Player ID is required")
    private Long playerId;
    
    @NotNull(message = "Status is required")
    private AttendanceStatus status;
    
    private String comment;

    public enum AttendanceStatus {
        PRESENT,
        ABSENT,
        INJURED
    }

    // Constructors
    public Attendance() {}

    public Attendance(Long id, Long eventId, Long playerId, 
                     AttendanceStatus status, String comment) {
        this.id = id;
        this.eventId = eventId;
        this.playerId = playerId;
        this.status = status;
        this.comment = comment;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}