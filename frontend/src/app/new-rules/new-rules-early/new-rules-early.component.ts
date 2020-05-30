import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { RulesService } from 'src/app/services/rules.service';

@Component({
  selector: 'app-new-rules-early',
  templateUrl: './new-rules-early.component.html',
  styleUrls: ['./new-rules-early.component.scss']
})
export class NewRulesEarlyComponent implements OnInit {

  champRole = ['Juggernaut', 'Diver', 'Assassin', 'Skirmisher', 'Marksman', 'Burst',
                'Battlemage', 'Artillery', 'Vanguard', 'Warden', 'Catcher', 'Enchanter'];
  lanes = ['Top', 'Jungle', 'Mid', 'Bottom'];
  playType = ['Aggro', 'Defensive'];

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
    enemyRole: 'Juggernaut',
    playType: 'Aggro',
    lane: 'Top'
  };

  constructor(private rulesService: RulesService) { }

  ngOnInit() {
    this.newRule = new FormGroup({
      allyRole: new FormControl(this.currentSelected.allyRole, Validators.required),
      enemyRole: new FormControl(this.currentSelected.enemyRole, Validators.required),
      playType: new FormControl(this.currentSelected.playType, Validators.required),
      lane: new FormControl(this.currentSelected.lane, Validators.required),
      playStyles: new FormArray([])
    });

    this.newRule.get('allyRole').valueChanges.subscribe((value: any) => {
      this.champRole.forEach((role) => {
        if (role === value) {
          this.currentSelected.allyRole = role;
        }
      });
    });

    this.newRule.get('enemyRole').valueChanges.subscribe((value: any) => {
      this.champRole.forEach((role) => {
        if (role === value) {
          this.currentSelected.enemyRole = role;
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
    const enemyRole = this.newRule.get('enemyRole').value;
    const playType = this.newRule.get('playType').value;
    const lane = this.newRule.get('lane').value;
    const playStyles = this.newRule.get('playStyles').value;

    const newRule = {
      allyRole,
      enemyRole,
      playType,
      lane,
      playStyles
    };

    console.log(newRule);

    this.rulesService.addEarlyRule(newRule).subscribe(
      resData => {
        console.log(resData);
      },
      error => {
        console.log(error);
      }
    );
  }

}
