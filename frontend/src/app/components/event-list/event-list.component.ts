import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EventService } from '../../services/event.service';
import { Event, EVENT_TYPE_LABELS, EventType } from '../../models/event.model';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent implements OnInit {
  events: Event[] = [];
  eventTypeLabels = EVENT_TYPE_LABELS;
  eventType = EventType;
  loading = false;
  error = '';

  constructor(
    private eventService: EventService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadEvents();
  }

  loadEvents(): void {
    this.loading = true;
    this.error = '';
    this.eventService.getAllEvents().subscribe({
      next: (data) => {
        this.events = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load events';
        this.loading = false;
        console.error(err);
      }
    });
  }

  navigateToAdd(): void {
    this.router.navigate(['/events/add']);
  }

  navigateToEdit(id: number): void {
    this.router.navigate(['/events/edit', id]);
  }

  navigateToAttendance(id: number): void {
    this.router.navigate(['/events', id, 'attendance']);
  }

  navigateToResult(id: number): void {
    this.router.navigate(['/events', id, 'result']);
  }

  deleteEvent(id: number): void {
    if (confirm('Are you sure you want to delete this event?')) {
      this.eventService.deleteEvent(id).subscribe({
        next: () => this.loadEvents(),
        error: (err) => {
          this.error = 'Failed to delete event';
          console.error(err);
        }
      });
    }
  }

  formatDateTime(dateTime: string): string {
    return new Date(dateTime).toLocaleString();
  }

  isPastEvent(dateTime: string): boolean {
    return new Date(dateTime) < new Date();
  }

  getResultDisplay(event: Event): string {
    if (!event.matchResult) return 'No result';
    const { homeScore, awayScore } = event.matchResult;
    return `${homeScore} - ${awayScore}`;
  }

  getResultClass(event: Event): string {
    if (!event.matchResult) return '';
    const { homeScore, awayScore } = event.matchResult;
    if (homeScore > awayScore) return 'win';
    if (homeScore < awayScore) return 'loss';
    return 'draw';
  }
}