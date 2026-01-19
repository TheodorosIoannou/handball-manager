import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from '../../services/event.service';
import { Event, EventType } from '../../models/event.model';

@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
  styleUrls: ['./event-form.component.css']
})
export class EventFormComponent implements OnInit {
  eventForm: FormGroup;
  isEditMode = false;
  eventId?: number;
  loading = false;
  error = '';
  eventTypes = Object.values(EventType);

  constructor(
    private fb: FormBuilder,
    private eventService: EventService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.eventForm = this.fb.group({
      type: [EventType.TRAINING, Validators.required],
      title: ['', Validators.required],
      location: ['', Validators.required],
      dateTime: ['', Validators.required],
      durationMinutes: [90, [Validators.required, Validators.min(1)]],
      opponent: [''],
      notes: ['']
    });
  }

  ngOnInit(): void {
    this.eventId = this.route.snapshot.params['id'];
    if (this.eventId) {
      this.isEditMode = true;
      this.loadEvent(this.eventId);
    }
  }

  loadEvent(id: number): void {
    this.loading = true;
    this.eventService.getEventById(id).subscribe({
      next: (event) => {
        this.eventForm.patchValue(event);
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load event';
        this.loading = false;
        console.error(err);
      }
    });
  }

  onSubmit(): void {
    if (this.eventForm.invalid) {
      this.eventForm.markAllAsTouched();
      return;
    }

    this.loading = true;
    const event: Event = this.eventForm.value;

    const operation = this.isEditMode
      ? this.eventService.updateEvent(this.eventId!, event)
      : this.eventService.createEvent(event);

    operation.subscribe({
      next: () => this.router.navigate(['/events']),
      error: (err) => {
        this.error = err.error || 'Failed to save event';
        this.loading = false;
      }
    });
  }

  cancel(): void {
    this.router.navigate(['/events']);
  }
}
