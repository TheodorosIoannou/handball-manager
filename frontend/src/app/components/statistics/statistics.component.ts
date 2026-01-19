import { Component, OnInit } from '@angular/core';
import { StatisticsService } from '../../services/statistics.service';
import { Statistics } from '../../models/statistics.model';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {
  statistics: Statistics | null = null;
  loading = false;
  error = '';

  constructor(private statisticsService: StatisticsService) {}

  ngOnInit(): void {
    this.loadStatistics();
  }

  loadStatistics(): void {
    this.loading = true;
    this.error = '';
    this.statisticsService.getStatistics().subscribe({
      next: (data) => {
        this.statistics = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load statistics';
        this.loading = false;
        console.error(err);
      }
    });
  }

  getWinPercentage(): number {
    if (!this.statistics || this.statistics.totalMatches === 0) return 0;
    return (this.statistics.wins / this.statistics.totalMatches) * 100;
  }

  getDrawPercentage(): number {
    if (!this.statistics || this.statistics.totalMatches === 0) return 0;
    return (this.statistics.draws / this.statistics.totalMatches) * 100;
  }

  getLossPercentage(): number {
    if (!this.statistics || this.statistics.totalMatches === 0) return 0;
    return (this.statistics.losses / this.statistics.totalMatches) * 100;
  }
}