import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PORT } from '../shared/constants';

@Injectable({ providedIn: 'root' })
export class RulesService {
  private readonly newEarlyGameRule = `http://localhost:${PORT}/early-game`;
  private readonly newMidLateGameRule = `http://localhost:${PORT}/mid-late-game`;

  constructor(private http: HttpClient) {}

  addEarlyRule(rule: any) {
    return this.http.post<any>(
      this.newEarlyGameRule,
      {
        rule
      }
    );
  }

  addMidLateRule(rule: any) {
    return this.http.post<any>(
      this.newMidLateGameRule,
      {
        rule
      }
    );
  }
}
