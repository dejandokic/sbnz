import { Component, OnInit } from '@angular/core';
import { IChampion } from '../services/strategy.service';
import * as data from '../shared/champions.json';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { StrategyService } from '../services/strategy.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {
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
    enemySupport: {name: 'Alistar'}
  };

  champions: any = {
    top: [],
    mid: [],
    jungle: [],
    adc: [],
    support: []
  };
  champForm: FormGroup;

  constructor(private strategyService: StrategyService) {}

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
      enemySupport: this.champions.support[0].name
    });
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

    const champsArray = [allyTop, allyJungle, allyMid, allyADC, allySupport, enemyTop, enemyJungle, enemyMid, enemyADC, enemySupport];

    this.strategyService.getStrategy(champsArray).subscribe(
      resData => {
        console.log(resData);
      },
      error => {
        console.log(error);
      }
    );
  }



}