import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AttendanceService } from '../../services/attendance.service';
import { EventService } from '../../services/event.service';
import { AttendanceDTO, AttendanceStatus, ATTENDANCE_STATUS_LABELS, Attendance } from '../../models/attendance.model';
import { Event } from '../../models/event.model';

@Component({
  selector: 'app-attendance',
  templateUrl: './attendance.component.html',
  styleUrls: ['./attendance.component.css']
})
export class AttendanceComponent implements OnInit {
  eventId!: number;
  event: Event | null = null;
  attendances: AttendanceDTO[] = [];
  attendanceStatuses = Object.values(AttendanceStatus);
  statusLabels = ATTENDANCE_STATUS_LABELS;
  loading = false;
  error = '';

  constructor(
    private attendanceService: AttendanceService,
    private eventService: EventService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.eventId = +this.route.snapshot.params['id'];
    this.loadEvent();
    this.loadAttendances();
  }

  loadEvent(): void {
    this.eventService.getEventById(this.eventId).subscribe({
      next: (event) => {
        this.event = event;
      },
      error: (err) => {
        this.error = 'Failed to load event';
        console.error(err);
      }
    });
  }

  loadAttendances(): void {
    this.loading = true;
    this.error = '';
    this.attendanceService.getAttendanceForEvent(this.eventId).subscribe({
      next: (data) => {
        this.attendances = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load attendances';
        this.loading = false;
        console.error(err);
      }
    });
  }

  onStatusChange(attendance: AttendanceDTO, status: AttendanceStatus): void {
    attendance.status = status;
  }

  onCommentChange(attendance: AttendanceDTO, comment: string): void {
    attendance.comment = comment;
  }

  saveAttendances(): void {
    this.loading = true;
    this.error = '';

    const attendancesToSave: Attendance[] = this.attendances
      .filter(a => a.status !== null && a.status !== undefined)
      .map(a => ({
        id: a.id,
        eventId: this.eventId,
        playerId: a.player.id!,
        status: a.status!,
        comment: a.comment
      }));

    this.attendanceService.batchUpdateAttendance(attendancesToSave).subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/events']);
      },
      error: (err) => {
        this.error = 'Failed to save attendances';
        this.loading = false;
        console.error(err);
      }
    });
  }

  cancel(): void {
    this.router.navigate(['/events']);
  }
}