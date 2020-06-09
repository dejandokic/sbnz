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
      champ = (champ === 'Leblanc') ? 'LeBlanc' : champ;

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

  iter2(info) {
    return this.http.post<any>(
      this.strategyInteraction2,
      {
        topLaneState: info[0],
        topEnemyInfo: info[1],
        midLaneState: info[2],
        midEnemyInfo: info[3],
        bottomLaneState: info[4],
        bottomEnemyInfo: info[5],
        enemyJunglerInfo: info[6]
      }
    );
  }

  iter3(info) {
    console.log(info);
    return this.http.post<any>(
      this.strategyInteraction3,
      {
        topAllyKills: info[0],
        topEnemyKills: info[1],
        topAllyTowers: info[2],
        topEnemyTowers: info[3],
        midAllyKills: info[4],
        midEnemyKills: info[5],
        midAllyTowers: info[6],
        midEnemyTowers: info[7],
        bottomAllyKills: info[8],
        bottomEnemyKills: info[9],
        bottomAllyTowers: info[10],
        bottomEnemyTowers: info[11],
        allyObjectives: info[12],
        enemyObjectives: info[13]
      }
    );
  }

  iter4(info) {
    return this.http.post<any>(
      this.strategyInteraction4,
      {
        teamKillsLead: info[0],
        teamTowersLead: info[1],
        teamObjectivesLead: info[2]
      }
    );
  }
}
