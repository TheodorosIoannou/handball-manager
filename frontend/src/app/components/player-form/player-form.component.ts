import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PlayerService } from '../../services/player.service';
import { Player, Position, POSITION_LABELS } from '../../models/player.model';

@Component({
  selector: 'app-player-form',
  templateUrl: './player-form.component.html',
  styleUrls: ['./player-form.component.css']
})
export class PlayerFormComponent implements OnInit {
  playerForm: FormGroup;
  isEditMode = false;
  playerId?: number;
  positions = Object.values(Position);
  positionLabels = POSITION_LABELS;
  error = '';
  loading = false;

  constructor(
    private fb: FormBuilder,
    private playerService: PlayerService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.playerForm = this.fb.group({
      firstName: ['', [Validators.required, Validators.minLength(2)]],
      lastName: ['', [Validators.required, Validators.minLength(2)]],
      position: [Position.GOALKEEPER, Validators.required],
      shirtNumber: [1, [Validators.required, Validators.min(1), Validators.max(99)]],
      active: [true]
    });
  }

  ngOnInit(): void {
    this.playerId = this.route.snapshot.params['id'];
    if (this.playerId) {
      this.isEditMode = true;
      this.loadPlayer(this.playerId);
    }
  }

  loadPlayer(id: number): void {
    this.loading = true;
    this.playerService.getPlayerById(id).subscribe({
      next: (player) => {
        this.playerForm.patchValue(player);
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load player';
        this.loading = false;
        console.error(err);
      }
    });
  }

  onSubmit(): void {
    if (this.playerForm.invalid) {
      this.markFormGroupTouched(this.playerForm);
      return;
    }

    this.loading = true;
    this.error = '';
    const player: Player = this.playerForm.value;

    const operation = this.isEditMode
      ? this.playerService.updatePlayer(this.playerId!, player)
      : this.playerService.createPlayer(player);

    operation.subscribe({
      next: () => {
        this.router.navigate(['/players']);
      },
      error: (err) => {
        this.error = err.error || 'Failed to save player';
        this.loading = false;
        console.error(err);
      }
    });
  }

  cancel(): void {
    this.router.navigate(['/players']);
  }

  private markFormGroupTouched(formGroup: FormGroup): void {
    Object.keys(formGroup.controls).forEach(key => {
      formGroup.get(key)?.markAsTouched();
    });
  }
}