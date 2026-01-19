package com.handball.manager.controller;

import com.handball.manager.dto.AttendanceDTO;
import com.handball.manager.model.Attendance;
import com.handball.manager.service.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:4200")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceForEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(attendanceService.getAttendanceForEvent(eventId));
    }

    @PostMapping
    public ResponseEntity<Attendance> createAttendance(@Valid @RequestBody Attendance attendance) {
        Attendance created = attendanceService.createOrUpdateAttendance(attendance);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable Long id, 
                                                       @Valid @RequestBody Attendance attendance) {
        attendance.setId(id);
        Attendance updated = attendanceService.createOrUpdateAttendance(attendance);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Attendance>> batchUpdateAttendance(
            @Valid @RequestBody List<Attendance> attendances) {
        List<Attendance> updated = attendanceService.batchUpdate(attendances);
        return ResponseEntity.ok(updated);
    }
}