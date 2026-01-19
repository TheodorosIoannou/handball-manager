package com.handball.manager.dto;

import com.handball.manager.model.Attendance;
import com.handball.manager.model.Player;

public class AttendanceDTO {
    private Long id;
    private Long eventId;
    private Player player;
    private Attendance.AttendanceStatus status;
    private String comment;

    public AttendanceDTO() {}

    public AttendanceDTO(Long id, Long eventId, Player player, 
                        Attendance.AttendanceStatus status, String comment) {
        this.id = id;
        this.eventId = eventId;
        this.player = player;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Attendance.AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(Attendance.AttendanceStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}