import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EventService } from '../../services/event.service';
import { MatchResult } from '../../models/event.model';

@Component({
  selector: 'app-match-result',
  templateUrl: './match-result.component.html',
  styleUrls: ['./match-result.component.css']
})
export class MatchResultComponent implements OnInit {
  resultForm: FormGroup;
  eventId!: number;
  loading = false;
  error = '';

  constructor(
    private fb: FormBuilder,
    private eventService: EventService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.resultForm = this.fb.group({
      homeScore: [0, [Validators.required, Validators.min(0)]],
      awayScore: [0, [Validators.required, Validators.min(0)]]
    });
  }

  ngOnInit(): void {
    this.eventId = Number(this.route.snapshot.params['id']);
    if (!this.eventId) {
      this.error = 'Invalid event ID';
    }
  }

  onSubmit(): void {
    if (this.resultForm.invalid) {
      this.resultForm.markAllAsTouched();
      return;
    }

    this.loading = true;
    const result: MatchResult = this.resultForm.value;

this.eventService.updateMatchResult(this.eventId, result).subscribe({
      next: () => {
        this.router.navigate(['/events']);
      },
      error: (err) => {
        this.error = err.error || 'Failed to save match result';
        this.loading = false;
        console.error(err);
      }
    });
  }

  cancel(): void {
    this.router.navigate(['/events']);
  }
}
