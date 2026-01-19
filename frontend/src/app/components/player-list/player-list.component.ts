import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PlayerService } from '../../services/player.service';
import { Player, POSITION_LABELS } from '../../models/player.model';

@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.css']
})
export class PlayerListComponent implements OnInit {
  players: Player[] = [];
  positionLabels = POSITION_LABELS;
  loading = false;
  error = '';

  constructor(
    private playerService: PlayerService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadPlayers();
  }

  loadPlayers(): void {
    this.loading = true;
    this.error = '';
    this.playerService.getAllPlayers().subscribe({
      next: (data) => {
        this.players = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load players';
        this.loading = false;
        console.error(err);
      }
    });
  }

  navigateToAdd(): void {
    this.router.navigate(['/players/add']);
  }

  navigateToEdit(id: number): void {
    this.router.navigate(['/players/edit', id]);
  }

  deactivatePlayer(id: number): void {
    if (confirm('Are you sure you want to deactivate this player?')) {
      this.playerService.deactivatePlayer(id).subscribe({
        next: () => this.loadPlayers(),
        error: (err) => {
          this.error = 'Failed to deactivate player';
          console.error(err);
        }
      });
    }
  }

  deletePlayer(id: number): void {
    if (confirm('Are you sure you want to delete this player? This action cannot be undone.')) {
      this.playerService.deletePlayer(id).subscribe({
        next: () => this.loadPlayers(),
        error: (err) => {
          this.error = 'Failed to delete player';
          console.error(err);
        }
      });
    }
  }
}