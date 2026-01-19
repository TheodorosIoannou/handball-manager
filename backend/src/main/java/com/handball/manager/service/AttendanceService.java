package com.handball.manager.service;

import com.handball.manager.dto.AttendanceDTO;
import com.handball.manager.model.Attendance;
import com.handball.manager.model.Player;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    private final Map<Long, Attendance> attendances = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final PlayerService playerService;

    public AttendanceService(PlayerService playerService) {
        this.playerService = playerService;
    }

    public List<AttendanceDTO> getAttendanceForEvent(Long eventId) {
        List<Player> allPlayers = playerService.getAllPlayers();
        Map<Long, Attendance> eventAttendances = attendances.values().stream()
                .filter(a -> a.getEventId().equals(eventId))
                .collect(Collectors.toMap(Attendance::getPlayerId, a -> a));

        return allPlayers.stream()
                .map(player -> {
                    Attendance attendance = eventAttendances.get(player.getId());
                    return new AttendanceDTO(
                            attendance != null ? attendance.getId() : null,
                            eventId,
                            player,
                            attendance != null ? attendance.getStatus() : null,
                            attendance != null ? attendance.getComment() : null
                    );
                })
                .collect(Collectors.toList());
    }

    public Attendance createOrUpdateAttendance(Attendance attendance) {
        if (attendance.getId() == null) {
            Long id = idGenerator.getAndIncrement();
            attendance.setId(id);
        }
        attendances.put(attendance.getId(), attendance);
        return attendance;
    }

    public List<Attendance> batchUpdate(List<Attendance> attendanceList) {
        return attendanceList.stream()
                .map(this::createOrUpdateAttendance)
                .collect(Collectors.toList());
    }

    public List<Attendance> getAllAttendances() {
        return new ArrayList<>(attendances.values());
    }
}