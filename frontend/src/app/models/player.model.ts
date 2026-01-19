export interface Player {
  id?: number;
  firstName: string;
  lastName: string;
  position: Position;
  shirtNumber: number;
  active: boolean;
}

export enum Position {
  GOALKEEPER = 'GOALKEEPER',
  LEFT_WING = 'LEFT_WING',
  RIGHT_WING = 'RIGHT_WING',
  LEFT_BACK = 'LEFT_BACK',
  RIGHT_BACK = 'RIGHT_BACK',
  CENTER_BACK = 'CENTER_BACK',
  PIVOT = 'PIVOT'
}

export const POSITION_LABELS: { [key in Position]: string } = {
  [Position.GOALKEEPER]: 'Goalkeeper',
  [Position.LEFT_WING]: 'Left Wing',
  [Position.RIGHT_WING]: 'Right Wing',
  [Position.LEFT_BACK]: 'Left Back',
  [Position.RIGHT_BACK]: 'Right Back',
  [Position.CENTER_BACK]: 'Center Back',
  [Position.PIVOT]: 'Pivot'
};