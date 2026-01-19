package com.handball.manager.service;

import com.handball.manager.dto.StatisticsDTO;
import com.handball.manager.model.Attendance;
import com.handball.manager.model.Event;
import com.handball.manager.model.MatchResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {

    private final EventService eventService;
    private final AttendanceService attendanceService;

    public StatisticsService(EventService eventService, AttendanceService attendanceService) {
        this.eventService = eventService;
        this.attendanceService = attendanceService;
    }

    public StatisticsDTO calculateStatistics() {
        List<Event> allEvents = eventService.getAllEvents();
        
        long totalMatches = allEvents.stream()
                .filter(e -> e.getType() == Event.EventType.MATCH && e.getMatchResult() != null)
                .count();
        
        long wins = allEvents.stream()
                .filter(e -> e.getType() == Event.EventType.MATCH && e.getMatchResult() != null)
                .filter(e -> e.getMatchResult().getResult().equals("WIN"))
                .count();
        
        long draws = allEvents.stream()
                .filter(e -> e.getType() == Event.EventType.MATCH && e.getMatchResult() != null)
                .filter(e -> e.getMatchResult().getResult().equals("DRAW"))
                .count();
        
        long losses = allEvents.stream()
                .filter(e -> e.getType() == Event.EventType.MATCH && e.getMatchResult() != null)
                .filter(e -> e.getMatchResult().getResult().equals("LOSS"))
                .count();
        
        double trainingAttendanceRate = calculateTrainingAttendanceRate(allEvents);
        
        return new StatisticsDTO(totalMatches, wins, draws, losses, trainingAttendanceRate);
    }

    private double calculateTrainingAttendanceRate(List<Event> events) {
        List<Attendance> allAttendances = attendanceService.getAllAttendances();
        
        long trainingAttendances = allAttendances.stream()
                .filter(a -> events.stream()
                        .anyMatch(e -> e.getId().equals(a.getEventId()) 
                                && e.getType() == Event.EventType.TRAINING))
                .count();
        
        if (trainingAttendances == 0) {
            return 0.0;
        }
        
        long presentCount = allAttendances.stream()
                .filter(a -> events.stream()
                        .anyMatch(e -> e.getId().equals(a.getEventId()) 
                                && e.getType() == Event.EventType.TRAINING))
                .filter(a -> a.getStatus() == Attendance.AttendanceStatus.PRESENT)
                .count();
        
        return (double) presentCount / trainingAttendances * 100;
    }
}