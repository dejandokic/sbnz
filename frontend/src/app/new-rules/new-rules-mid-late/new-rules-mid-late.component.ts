import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { RulesService } from 'src/app/services/rules.service';

@Component({
  selector: 'app-new-rules-mid-late',
  templateUrl: './new-rules-mid-late.component.html',
  styleUrls: ['./new-rules-mid-late.component.scss']
})
export class NewRulesMidLateComponent implements OnInit {

  champRole = ['Juggernaut', 'Diver', 'Assassin', 'Skirmisher', 'Marksman', 'Burst',
                'Battlemage', 'Artillery', 'Vanguard', 'Warden', 'Catcher', 'Enchanter'];
  lanes = ['Top', 'Jungle', 'Mid', 'Bottom'];
  playType = ['Aggro', 'Defensive'];
  teamComposition = ['Attack', 'Protect', 'Catch', 'Poke', 'Split push'];
  gamePhase = ['Mid', 'Late'];

  newRule: FormGroup;

  public checks: Array<any> = [
    {description: 'Hard CC', value: 'hardCC'},
    {description: 'Hard Engage', value: 'hardEngage'},
    {description: 'Disengage', value: 'disengage'},
    {description: 'Poke', value: 'poke'},
    {description: 'Waveclear', value: 'waveclear'},
    {description: 'Sustain', value: 'sustain'},
    {description: 'Utility', value: 'utility'},
    {description: 'Mobility', value: 'mobility'},
    {description: 'AOE Damage', value: 'aoeDamage'},
    {description: 'Split push', value: 'splitPush'}

  ];

  currentSelected = {
    allyRole: 'Juggernaut',
    teamComposition: 'Attack',
    playType: 'Aggro',
    lane: 'Top',
    gamePhase: 'Mid'
  };

  constructor(private rulesService: RulesService) { }

  ngOnInit() {
    this.newRule = new FormGroup({
      allyRole: new FormControl(this.currentSelected.allyRole, Validators.required),
      teamComposition: new FormControl(this.currentSelected.teamComposition, Validators.required),
      playType: new FormControl(this.currentSelected.playType, Validators.required),
      lane: new FormControl(this.currentSelected.lane, Validators.required),
      gamePhase: new FormControl(this.currentSelected.gamePhase, Validators.required),
      advice: new FormControl('', Validators.required),
      playStyles: new FormArray([])
    });

    this.newRule.get('allyRole').valueChanges.subscribe((value: any) => {
      this.champRole.forEach((role) => {
        if (role === value) {
          this.currentSelected.allyRole = role;
        }
      });
    });

    this.newRule.get('teamComposition').valueChanges.subscribe((value: any) => {
      this.teamComposition.forEach((comp) => {
        if (comp === value) {
          this.currentSelected.teamComposition = comp;
        }
      });
    });

    this.newRule.get('gamePhase').valueChanges.subscribe((value: any) => {
      this.gamePhase.forEach((phase) => {
        if (phase === value) {
          this.currentSelected.gamePhase = phase;
        }
      });
    });

    this.newRule.get('playType').valueChanges.subscribe((value: any) => {
      this.playType.forEach((type) => {
        if (type === value) {
          this.currentSelected.playType = type;
        }
      });
    });

    this.newRule.get('lane').valueChanges.subscribe((value: any) => {
      this.lanes.forEach((lane) => {
        if (lane === value) {
          this.currentSelected.lane = lane;
        }
      });
    });
  }

  onCheckChange(event) {
    const formArray: FormArray = this.newRule.get('playStyles') as FormArray;
    if (event.target.checked) {
      formArray.push(new FormControl(event.target.value));
    } else {
      let i = 0;
      formArray.controls.forEach((ctrl: FormControl) => {
        if (ctrl.value === event.target.value) {
          formArray.removeAt(i);
          return;
        }
        i++;
      });
    }
  }

  onSubmit() {
    if (this.newRule.invalid) {
      return;
    }

    const allyRole = this.newRule.get('allyRole').value;
    const teamComposition = this.newRule.get('teamComposition').value;
    const playType = this.newRule.get('playType').value;
    const lane = this.newRule.get('lane').value;
    const playStyles = this.newRule.get('playStyles').value;
    const advice = this.newRule.get('advice').value;
    const gamePhase = this.newRule.get('gamePhase').value;

    const newRule = {
      allyRole,
      teamComposition,
      playType,
      lane,
      playStyles,
      advice,
      gamePhase
    };

    console.log(newRule);

    this.rulesService.addMidLateRule(newRule).subscribe(
      resData => {
        console.log(resData);
      },
      error => {
        console.log(error);
      }
    );
  }

}
