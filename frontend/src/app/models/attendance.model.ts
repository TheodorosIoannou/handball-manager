import { Player } from './player.model';

export interface Attendance {
  id?: number;
  eventId: number;
  playerId: number;
  status: AttendanceStatus;
  comment?: string;
}

export interface AttendanceDTO {
  id?: number;
  eventId: number;
  player: Player;
  status?: AttendanceStatus;
  comment?: string;
}

export enum AttendanceStatus {
  PRESENT = 'PRESENT',
  ABSENT = 'ABSENT',
  INJURED = 'INJURED'
}

export const ATTENDANCE_STATUS_LABELS: { [key in AttendanceStatus]: string } = {
  [AttendanceStatus.PRESENT]: 'Present',
  [AttendanceStatus.ABSENT]: 'Absent',
  [AttendanceStatus.INJURED]: 'Injured'
};