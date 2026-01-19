import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlayerListComponent } from './components/player-list/player-list.component';
import { PlayerFormComponent } from './components/player-form/player-form.component';
import { EventListComponent } from './components/event-list/event-list.component';
import { EventFormComponent } from './components/event-form/event-form.component';
import { AttendanceComponent } from './components/attendance/attendance.component';
import { MatchResultComponent } from './components/match-result/match-result.component';
import { StatisticsComponent } from './components/statistics/statistics.component';

const routes: Routes = [
  { path: '', redirectTo: '/events', pathMatch: 'full' },
  { path: 'players', component: PlayerListComponent },
  { path: 'players/add', component: PlayerFormComponent },
  { path: 'players/edit/:id', component: PlayerFormComponent },
  { path: 'events', component: EventListComponent },
  { path: 'events/add', component: EventFormComponent },
  { path: 'events/edit/:id', component: EventFormComponent },
  { path: 'events/:id/attendance', component: AttendanceComponent },
  { path: 'events/:id/result', component: MatchResultComponent },
  { path: 'statistics', component: StatisticsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }