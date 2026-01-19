export interface Event {
  id?: number;
  type: EventType;
  title: string;
  location: string;
  dateTime: string;
  durationMinutes: number;
  opponent?: string;
  notes?: string;
  matchResult?: MatchResult;
}

export enum EventType {
  TRAINING = 'TRAINING',
  MATCH = 'MATCH'
}

export interface MatchResult {
  id?: number;
  homeScore: number;
  awayScore: number;
  summary?: string;
}

export const EVENT_TYPE_LABELS: { [key in EventType]: string } = {
  [EventType.TRAINING]: 'Training',
  [EventType.MATCH]: 'Match'
};