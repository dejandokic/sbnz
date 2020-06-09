import { Component, OnInit, OnDestroy } from '@angular/core';
import { IChampion } from '../services/strategy.service';
import * as data from '../shared/champions.json';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { StrategyService } from '../services/strategy.service';
import { Subscription } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit, OnDestroy {
  champs: any = (data as any).default;
  currentSelected = {
    allyTop: {name: 'Aatrox'},
    allyMid: {name: 'Ahri'},
    allyJungle: {name: 'Aatrox'},
    allySupport: {name: 'Alistar'},
    allyADC: {name: 'Aphelios'},
    enemyTop: {name: 'Aatrox'},
    enemyMid: {name: 'Ahri'},
    enemyJungle: {name: 'Aatrox'},
    enemyADC: {name: 'Aphelios'},
    enemySupport: {name: 'Alistar'},
    allyTopPrefer: {name: 'aggro'},
    allyJunglePrefer: {name: 'aggro'},
    allyMidPrefer: {name: 'aggro'},
    allyADCPrefer: {name: 'aggro'},
    allySupportPrefer: {name: 'aggro'}
  };

  interactionOneResults = {
    top: '',
    jungle: '',
    mid: '',
    adc: '',
    support: '',
    team: ''
  };
  champForm: FormGroup;


  iter2Current = {
    bottomLaneState: '',
    topLaneState: '',
    midLaneState: '',
    topEnemyInfo: '',
    midEnemyInfo: '',
    bottomEnemyInfo: '',
    enemyJunglerInfo: ''
  };

  iter2Form: FormGroup;

  iter2Results = {
    top: '',
    mid: '',
    bottom: ''
  };

  iter3Form: FormGroup;

  iter3Current = {
    topAllyKills: '',
    topEnemyKills: '',
    topAllyTowers: '',
    topEnemyTowers: '',
    midAllyKills: '',
    midEnemyKills: '',
    midAllyTowers: '',
    midEnemyTowers: '',
    bottomAllyKills: '',
    bottomEnemyKills: '',
    bottomAllyTowers: '',
    bottomEnemyTowers: '',
    allyObjectives: '',
    enemyObjectives: ''
  };

  iter3Results = {
    top: '',
    jungle: '',
    mid: '',
    bottom: ''
  };

  iter4Form: FormGroup;

  iter4Current = {
    killsLead: '',
    towersLead: '',
    objectivesLead: ''
  };

  iter4Result = '';


  private userSub: Subscription;
  isAuthenticated = false;

  champions: any = {
    top: [],
    mid: [],
    jungle: [],
    adc: [],
    support: []
  };

  constructor(private strategyService: StrategyService, private authService: AuthService) {}

  ngOnInit() {
    this.champForm = new FormGroup({
      allyTop: new FormControl('', Validators.required),
      allyJungle: new FormControl('', Validators.required),
      allyMid: new FormControl('', Validators.required),
      allyADC: new FormControl('', Validators.required),
      allySupport: new FormControl('', Validators.required),
      enemyTop: new FormControl('', Validators.required),
      enemyJungle: new FormControl('', Validators.required),
      enemyMid: new FormControl('', Validators.required),
      enemyADC: new FormControl('', Validators.required),
      enemySupport: new FormControl('', Validators.required),
      allyTopPrefer: new FormControl('', Validators.required),
      allyJunglePrefer: new FormControl('', Validators.required),
      allyMidPrefer: new FormControl('', Validators.required),
      allyADCPrefer: new FormControl('', Validators.required),
      allySupportPrefer: new FormControl('', Validators.required)
    });

    this.iter2Form = new FormGroup({
      topLaneState: new FormControl('', Validators.required),
      midLaneState: new FormControl('', Validators.required),
      bottomLaneState: new FormControl('', Validators.required),
      topEnemyInfo: new FormControl('', Validators.required),
      midEnemyInfo: new FormControl('', Validators.required),
      bottomEnemyInfo: new FormControl('', Validators.required),
      enemyJunglerInfo: new FormControl('', Validators.required)
    });

    this.iter3Form = new FormGroup({
      topAllyKills: new FormControl('', Validators.required),
      topEnemyKills: new FormControl('', Validators.required),
      topAllyTowers: new FormControl('', Validators.required),
      topEnemyTowers: new FormControl('', Validators.required),
      midAllyKills: new FormControl('', Validators.required),
      midEnemyKills: new FormControl('', Validators.required),
      midAllyTowers: new FormControl('', Validators.required),
      midEnemyTowers: new FormControl('', Validators.required),
      bottomAllyKills: new FormControl('', Validators.required),
      bottomEnemyKills: new FormControl('', Validators.required),
      bottomAllyTowers: new FormControl('', Validators.required),
      bottomEnemyTowers: new FormControl('', Validators.required),
      allyObjectives: new FormControl('', Validators.required),
      enemyObjectives: new FormControl('', Validators.required)
    });

    this.iter4Form = new FormGroup({
      killsLead: new FormControl('', Validators.required),
      towersLead: new FormControl('', Validators.required),
      objectivesLead: new FormControl('', Validators.required)
    });

    this.userSub = this.authService.user.subscribe(user => {
      this.isAuthenticated = !!user;
    });

    this.champForm.get('allyTop').valueChanges.subscribe((value: any) => {
      this.champions.top.forEach((champ) => {
        if (champ.name === value) {
          this.currentSelected.allyTop = champ;
        }
      });
    });

    this.champForm.get('allyJungle').valueChanges.subscribe((value: any) => {
      this.champions.jungle.forEach((champ) => {
        if (champ.name === value) {
          this.currentSelected.allyJungle = champ;
        }
      });
    });

    this.champForm.get('allyMid').valueChanges.subscribe((value: any) => {
      this.champions.mid.forEach((champ) => {
        if (champ.name === value) {
          this.currentSelected.allyMid = champ;
        }
      });
    });

    this.champForm.get('allyADC').valueChanges.subscribe((value: any) => {
      this.champions.adc.forEach((champ) => {
        if (champ.name === value) {
          this.currentSelected.allyADC = champ;
        }
      });
    });

    this.champForm.get('allySupport').valueChanges.subscribe((value: any) => {
      this.champions.support.forEach((champ) => {
        if (champ.name === value) {
          this.currentSelected.allySupport = champ;
        }
      });
    });

    this.champForm.get('enemyTop').valueChanges.subscribe((value: any) => {
      this.champions.top.forEach((champ) => {
        if (champ.name === value) {
          this.currentSelected.enemyTop = champ;
        }
      });
    });

    this.champForm.get('enemyJungle').valueChanges.subscribe((value: any) => {
      this.champions.jungle.forEach((champ) => {
        if (champ.name === value) {
          this.currentSelected.enemyJungle = champ;
        }
      });
    });

    this.champForm.get('enemyMid').valueChanges.subscribe((value: any) => {
      this.champions.mid.forEach((champ) => {
        if (champ.name === value) {
          this.currentSelected.enemyMid = champ;
        }
      });
    });

    this.champForm.get('enemyADC').valueChanges.subscribe((value: any) => {
      this.champions.adc.forEach((champ) => {
        if (champ.name === value) {
          this.currentSelected.enemyADC = champ;
        }
      });
    });

    this.champForm.get('enemySupport').valueChanges.subscribe((value: any) => {
      this.champions.support.forEach((champ) => {
        if (champ.name === value) {
          this.currentSelected.enemySupport = champ;
        }
      });
    });

    this.champForm.get('allyTopPrefer').valueChanges.subscribe((value: any) => {
      this.currentSelected.allyTopPrefer = value;
    });

    this.champForm.get('allyJunglePrefer').valueChanges.subscribe((value: any) => {
      this.currentSelected.allyJunglePrefer = value;
    });

    this.champForm.get('allyMidPrefer').valueChanges.subscribe((value: any) => {
      this.currentSelected.allyMidPrefer = value;
    });

    this.champForm.get('allyADCPrefer').valueChanges.subscribe((value: any) => {
      this.currentSelected.allyADCPrefer = value;
    });

    this.champForm.get('allySupportPrefer').valueChanges.subscribe((value: any) => {
      this.currentSelected.allySupportPrefer = value;
    });

    // iter2
    this.iter2Form.get('topLaneState').valueChanges.subscribe((value: any) => {
      this.iter2Current.topLaneState = value;
    });

    this.iter2Form.get('midLaneState').valueChanges.subscribe((value: any) => {
      this.iter2Current.midLaneState = value;
    });

    this.iter2Form.get('bottomLaneState').valueChanges.subscribe((value: any) => {
      this.iter2Current.bottomLaneState = value;
    });

    this.iter2Form.get('topEnemyInfo').valueChanges.subscribe((value: any) => {
      this.iter2Current.topEnemyInfo = value;
    });

    this.iter2Form.get('midEnemyInfo').valueChanges.subscribe((value: any) => {
      this.iter2Current.midEnemyInfo = value;
    });

    this.iter2Form.get('bottomEnemyInfo').valueChanges.subscribe((value: any) => {
      this.iter2Current.bottomEnemyInfo = value;
    });

    this.iter2Form.get('enemyJunglerInfo').valueChanges.subscribe((value: any) => {
      this.iter2Current.enemyJunglerInfo = value;
    });

    // iter 3

    this.iter3Form.get('topAllyKills').valueChanges.subscribe((value: any) => {
      this.iter3Current.topAllyKills = value;
    });

    this.iter3Form.get('topEnemyKills').valueChanges.subscribe((value: any) => {
      this.iter3Current.topEnemyKills = value;
    });

    this.iter3Form.get('topAllyTowers').valueChanges.subscribe((value: any) => {
      this.iter3Current.topAllyTowers = value;
    });

    this.iter3Form.get('topEnemyTowers').valueChanges.subscribe((value: any) => {
      this.iter3Current.topEnemyTowers = value;
    });

    this.iter3Form.get('midAllyKills').valueChanges.subscribe((value: any) => {
      this.iter3Current.midAllyKills = value;
    });

    this.iter3Form.get('midEnemyKills').valueChanges.subscribe((value: any) => {
      this.iter3Current.midEnemyKills = value;
    });

    this.iter3Form.get('midAllyTowers').valueChanges.subscribe((value: any) => {
      this.iter3Current.midAllyTowers = value;
    });

    this.iter3Form.get('midEnemyTowers').valueChanges.subscribe((value: any) => {
      this.iter3Current.midEnemyTowers = value;
    });

    this.iter3Form.get('bottomAllyKills').valueChanges.subscribe((value: any) => {
      this.iter3Current.bottomAllyKills = value;
    });

    this.iter3Form.get('bottomEnemyKills').valueChanges.subscribe((value: any) => {
      this.iter3Current.bottomEnemyKills = value;
    });

    this.iter3Form.get('bottomAllyTowers').valueChanges.subscribe((value: any) => {
      this.iter3Current.bottomAllyTowers = value;
    });

    this.iter3Form.get('bottomEnemyTowers').valueChanges.subscribe((value: any) => {
      this.iter3Current.bottomEnemyTowers = value;
    });

    this.iter3Form.get('allyObjectives').valueChanges.subscribe((value: any) => {
      this.iter3Current.allyObjectives = value;
    });

    this.iter3Form.get('enemyObjectives').valueChanges.subscribe((value: any) => {
      this.iter3Current.enemyObjectives = value;
    });

    // iter4
    this.iter4Form.get('killsLead').valueChanges.subscribe((value: any) => {
      this.iter4Current.killsLead = value;
    });

    this.iter4Form.get('towersLead').valueChanges.subscribe((value: any) => {
      this.iter4Current.towersLead = value;
    });

    this.iter4Form.get('objectivesLead').valueChanges.subscribe((value: any) => {
      this.iter4Current.objectivesLead = value;
    });


    this.champs.forEach((ch: any) => {
      const champ: IChampion = { name: ch.name };
      if (ch.lane && ch.lane.adc) {
        this.champions.adc.push(champ);
      }
      if (ch.lane && ch.lane.mid) {
        this.champions.mid.push(champ);
      }
      if (ch.lane && ch.lane.top) {
        this.champions.top.push(champ);
      }
      if (ch.lane && ch.lane.jungle) {
        this.champions.jungle.push(champ);
      }
      if (ch.lane && ch.lane.support) {
        this.champions.support.push(champ);
      }
    });

    this.champForm.patchValue({
      allyTop: this.champions.top[0].name,
      allyJungle: this.champions.jungle[0].name,
      allyMid: this.champions.mid[0].name,
      allyADC: this.champions.adc[0].name,
      allySupport: this.champions.support[0].name,
      enemyTop: this.champions.top[0].name,
      enemyJungle: this.champions.jungle[0].name,
      enemyMid: this.champions.mid[0].name,
      enemyADC: this.champions.adc[0].name,
      enemySupport: this.champions.support[0].name,
      allyTopPrefer: 'aggro',
      allyJunglePrefer: 'aggro',
      allyMidPrefer: 'aggro',
      allyADCPrefer: 'aggro',
      allySupportPrefer: 'aggro'
    });

    this.iter2Form.patchValue({
      topLaneState: '3',
      topEnemyInfo: '1',
      midLaneState: '3',
      midEnemyInfo: '1',
      bottomLaneState: '3',
      bottomEnemyInfo: '1',
      enemyJunglerInfo: '1'
    });

    this.iter3Form.patchValue({
      topAllyKills: 0,
      topEnemyKills: 0,
      topAllyTowers: 0,
      topEnemyTowers: 0,
      midAllyKills: 0,
      midEnemyKills: 0,
      midAllyTowers: 0,
      midEnemyTowers: 0,
      bottomAllyKills: 0,
      bottomEnemyKills: 0,
      bottomAllyTowers: 0,
      bottomEnemyTowers: 0,
      allyObjectives: 0,
      enemyObjectives: 0
    });

    this.iter4Form.patchValue({
      killsLead: 'Ally team',
      towersLead: 'Ally team',
      objectivesLead: 'Ally team'
    });
  }

  ngOnDestroy(): void {
    this.userSub.unsubscribe();
  }


  onSubmit() {
    if (this.champForm.invalid) {
      return;
    }

    const allyTop = this.champForm.get('allyTop').value;
    const allyJungle = this.champForm.get('allyJungle').value;
    const allyMid = this.champForm.get('allyMid').value;
    const allyADC = this.champForm.get('allyADC').value;
    const allySupport = this.champForm.get('allySupport').value;

    const enemyTop = this.champForm.get('enemyTop').value;
    const enemyJungle = this.champForm.get('enemyJungle').value;
    const enemyMid = this.champForm.get('enemyMid').value;
    const enemyADC = this.champForm.get('enemyADC').value;
    const enemySupport = this.champForm.get('enemySupport').value;

    const allyTopPrefer = this.champForm.get('allyTopPrefer').value;
    const allyJunglePrefer = this.champForm.get('allyJunglePrefer').value;
    const allyMidPrefer = this.champForm.get('allyMidPrefer').value;
    const allyADCPrefer = this.champForm.get('allyADCPrefer').value;
    const allySupportPrefer = this.champForm.get('allySupportPrefer').value;


    const champsArray = [allyTop, allyJungle, allyMid, allyADC, allySupport, enemyTop, enemyJungle, enemyMid, enemyADC, enemySupport];
    const prefers = [allyTopPrefer, allyJunglePrefer, allyMidPrefer, allyADCPrefer, allySupportPrefer];

    this.strategyService.getStrategy(champsArray, prefers).subscribe(
      resData => {
        let content = [];
        let lane = [];
        let message = '';
        let laneMessage = '';

        resData.forEach((data: string) => {
          content = data.split(']');
          message = content[1];
          lane = content[0].split(',');
          laneMessage = lane[0];
          laneMessage = laneMessage.substr(1);
          console.log(laneMessage, '  ', message);
          if (laneMessage === 'top') {
            this.interactionOneResults.top = message;
          } else if (laneMessage === 'jungle') {
            this.interactionOneResults.jungle = message;
          } else if (laneMessage === 'mid') {
            this.interactionOneResults.mid = message;
          } else if (laneMessage === 'adc') {
            this.interactionOneResults.adc = message;
          } else if (laneMessage === 'support') {
            this.interactionOneResults.support = message;
          } else if (laneMessage === 'ALL') {
            this.interactionOneResults.team = message;
          }
        });
      },
      error => {
        console.log(error);
      }
    );
  }

  onSubmitIter2() {
    if (this.iter2Form.invalid) {
      return;
    }

    const topLaneState = parseInt(this.iter2Form.get('topLaneState').value);
    const topEnemyInfo = parseInt(this.iter2Form.get('topEnemyInfo').value);
    const midLaneState = parseInt(this.iter2Form.get('midLaneState').value);
    const midEnemyInfo = parseInt(this.iter2Form.get('midEnemyInfo').value);
    const bottomLaneState = parseInt(this.iter2Form.get('bottomLaneState').value);
    const bottomEnemyInfo = parseInt(this.iter2Form.get('bottomEnemyInfo').value);
    const enemyJunglerInfo = parseInt(this.iter2Form.get('enemyJunglerInfo').value);

    const info = [topLaneState, topEnemyInfo, midLaneState, midEnemyInfo, bottomLaneState, bottomEnemyInfo, enemyJunglerInfo];
    console.log(info);
    this.strategyService.iter2(info).subscribe(
      resData => {
        let content = [];
        let lane = [];
        let message = '';
        let laneMessage = '';

        resData.forEach((data: string) => {
          content = data.split(']');
          message = content[1];
          lane = content[0].split(',');
          laneMessage = lane[0];
          laneMessage = laneMessage.substr(1);
          console.log(laneMessage, '  ', message);
          if (laneMessage === 'top') {
            this.iter2Results.top = message;
          } else if (laneMessage === 'mid') {
            this.iter2Results.mid = message;
          } else if (laneMessage === 'adc') {
            this.iter2Results.bottom = message;
          }
        });
      },
      error => {
        console.log(error);
      }
    );
  }

  onSubmitIter3() {
    if (this.iter3Form.invalid) {
      return;
    }

    const topAllyKills = this.iter3Form.get('topAllyKills').value;
    const topEnemyKills = this.iter3Form.get('topEnemyKills').value;
    const topAllyTowers = this.iter3Form.get('topAllyTowers').value;
    const topEnemyTowers = this.iter3Form.get('topEnemyTowers').value;
    const midAllyKills = this.iter3Form.get('midAllyKills').value;
    const midEnemyKills = this.iter3Form.get('midEnemyKills').value;
    const midAllyTowers = this.iter3Form.get('midAllyTowers').value;
    const midEnemyTowers = this.iter3Form.get('midEnemyTowers').value;
    const bottomAllyKills = this.iter3Form.get('bottomAllyKills').value;
    const bottomEnemyKills = this.iter3Form.get('bottomEnemyKills').value;
    const bottomAllyTowers = this.iter3Form.get('bottomAllyTowers').value;
    const bottomEnemyTowers = this.iter3Form.get('bottomEnemyTowers').value;
    const allyObjectives = this.iter3Form.get('allyObjectives').value;
    const enemyObjectives = this.iter3Form.get('enemyObjectives').value;

    const info = [topAllyKills, topEnemyKills, topAllyTowers, topEnemyTowers, midAllyKills, midEnemyKills, midAllyTowers,
      midEnemyTowers, bottomAllyKills, bottomEnemyKills, bottomAllyTowers, bottomEnemyTowers, allyObjectives, enemyObjectives];

    this.strategyService.iter3(info).subscribe(
      resData => {
        let content = [];
        let lane = [];
        let message = '';
        let laneMessage = '';

        this.iter3Results = {
          top: '',
          jungle: '',
          mid: '',
          bottom: ''
        };

        resData.forEach((data: string) => {
          content = data.split(']');
          message = content[1];
          lane = content[0].split(',');
          laneMessage = lane[0];
          laneMessage = laneMessage.substr(1);
          console.log(laneMessage, '  ', message);
          if (laneMessage === 'top') {
            this.iter3Results.top += message;
          } else if (laneMessage === 'mid') {
            this.iter3Results.mid += message;
          } else if (laneMessage === 'adc') {
            this.iter3Results.bottom += message;
          } else if (laneMessage === 'jungle') {
            this.iter3Results.jungle += message;
          }
        });
      },
      error => {
        console.log(error);
      }
    );
  }

  onSubmitIter4() {
    if (this.iter4Form.invalid) {
      return;
    }
    const killsLead = (this.iter4Form.get('killsLead').value === 'Ally team') ? true : false;
    const towersLead = (this.iter4Form.get('towersLead').value === 'Ally team') ? true : false;
    const objectivesLead = (this.iter4Form.get('objectivesLead').value === 'Ally team') ? true : false;

    const info = [killsLead, towersLead, objectivesLead];

    this.strategyService.iter4(info).subscribe(
      resData => {
        let content = [];
        let lane = [];
        let message = '';

        resData.forEach((data: string) => {
          content = data.split(']');
          message = content[1];
          lane = content[0].split(',');
          this.iter4Result = message;
        });
      },
      error => {
        console.log(error);
      }
    );
  }

}
