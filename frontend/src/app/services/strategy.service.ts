import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PORT } from '../shared/constants';

export interface IChampion {
  name: string;
}

@Injectable({ providedIn: 'root' })
export class StrategyService {
  private readonly strategyInteraction1 = `http://localhost:${PORT}/api/strategy/interaction1`;
  private readonly strategyInteraction2 = `http://localhost:${PORT}/api/strategy/interaction2`;
  private readonly strategyInteraction3 = `http://localhost:${PORT}/api/strategy/interaction3`;
  private readonly strategyInteraction4 = `http://localhost:${PORT}/api/strategy/interaction4`;

  constructor(private http: HttpClient) {}

  getStrategy(champions: string[], prefer: string[]) {
    champions = champions.map(champ => {
      champ = (champ === 'AurelionSol') ? 'Aurelion Sol' : champ;
      champ = (champ === 'Chogath') ? "Cho'Gath" : champ;
      champ = (champ === 'DrMundo') ? 'Dr. Mundo' : champ;
      champ = (champ === 'Kaisa') ? "Kai'Sa" : champ;
      champ = (champ === 'Khazix') ? "Kha'Zix" : champ;
      champ = (champ === 'KogMaw') ? "Kog'Maw" : champ;
      champ = (champ === 'LeeSin') ? 'Lee Sin' : champ;
      champ = (champ === 'MasterYi') ? 'Master Yi' : champ;
      champ = (champ === 'MissFortune') ? 'Miss Fortune' : champ;
      champ = (champ === 'RekSai') ? "Rek'Sai" : champ;
      champ = (champ === 'TahmKench') ? 'Tahm Kench' : champ;
      champ = (champ === 'TwistedFate') ? 'Twisted Fate' : champ;
      champ = (champ === 'Velkoz') ? "Vel'Koz" : champ;
      champ = (champ === 'MonkeyKing') ? 'Wukong' : champ;
      champ = (champ === 'XinZhao') ? 'Xin Zhao' : champ;
      champ = (champ === 'AurelionSol') ? 'AurelionSol' : champ;
      champ = (champ === 'AurelionSol') ? 'AurelionSol' : champ;

      return champ;
    });

    return this.http.post<any>(
      this.strategyInteraction1,
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
        enemySupport: champions[9],
        allyTopPrefer: prefer[0],
        allyJunglePrefer: prefer[1],
        allyMidPrefer: prefer[2],
        allyADCPrefer: prefer[3],
        allySupportPrefer: prefer[4]
      }
    );
  }
}
