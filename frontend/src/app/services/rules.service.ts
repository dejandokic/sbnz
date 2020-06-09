import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PORT } from '../shared/constants';

@Injectable({ providedIn: 'root' })
export class RulesService {
  private readonly newEarlyGameRule = `http://localhost:${PORT}/api/new-rule/early-game`;
  private readonly newEarlyJungleRule = `http://localhost:${PORT}/api/new-rule/jungle`;
  private readonly newTeamCompositionRule = `http://localhost:${PORT}/api/new-rule/team-composition`;

  constructor(private http: HttpClient) {}

  addEarlyRule(rule: any) {
    return this.http.post<any>(
      this.newEarlyGameRule,
      rule
    );
  }

  addEarlyJungleRule(rule: any) {
    return this.http.post<any>(
      this.newEarlyJungleRule,
      rule
    );
  }

  addTeamCompositionRule(rule: any) {
    return this.http.post<any>(
      this.newTeamCompositionRule,
      rule
    );
  }
}
