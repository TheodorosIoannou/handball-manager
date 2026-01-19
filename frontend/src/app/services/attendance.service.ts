import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Attendance, AttendanceDTO } from '../models/attendance.model';

@Injectable({
  providedIn: 'root'
})
export class AttendanceService {
  private apiUrl = 'http://localhost:8080/api/attendance';

  constructor(private http: HttpClient) {}

  getAttendanceForEvent(eventId: number): Observable<AttendanceDTO[]> {
    return this.http.get<AttendanceDTO[]>(`${this.apiUrl}/event/${eventId}`);
  }

  createAttendance(attendance: Attendance): Observable<Attendance> {
    return this.http.post<Attendance>(this.apiUrl, attendance);
  }

  updateAttendance(id: number, attendance: Attendance): Observable<Attendance> {
    return this.http.put<Attendance>(`${this.apiUrl}/${id}`, attendance);
  }

  batchUpdateAttendance(attendances: Attendance[]): Observable<Attendance[]> {
    return this.http.post<Attendance[]>(`${this.apiUrl}/batch`, attendances);
  }
}