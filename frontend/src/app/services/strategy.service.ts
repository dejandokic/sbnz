import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PORT } from '../shared/constants';

export interface IChampion {
  name: string;
}

@Injectable({ providedIn: 'root' })
export class StrategyService {
  private readonly signUpLink = `http://localhost:${PORT}/champions`;

  constructor(private http: HttpClient) {}

  getStrategy(champions: string[]) {
    return this.http.post<any>(
      this.signUpLink,
      {
        allyTop: champions[0],
        allyJungle: champions[1],
        allyMid: champions[2],
        allyADC: champions[3],
        allySupport: champions[4],
        enemyTop: champions[5],
        enemyJungle: champions[6],
        enemyMid: champions[7],
        enemyADC: champions[8],
        enemySupport: champions[9]
      }
    );
  }
}
