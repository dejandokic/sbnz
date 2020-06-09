import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { RulesService } from 'src/app/services/rules.service';

@Component({
  selector: 'app-new-rules-early',
  templateUrl: './new-rules-early.component.html',
  styleUrls: ['./new-rules-early.component.scss']
})
export class NewRulesEarlyComponent implements OnInit {

  champRole = ['juggernaut', 'diver', 'assassin', 'skirmisher', 'marksman', 'burst',
                'battlemage', 'artillery', 'vanguard', 'warden', 'catcher', 'enchanter'];
  playType = ['aggro', 'def'];

  newRule: FormGroup;

  public checks: Array<any> = [
    {description: 'Hard CC', value: 'hardCC'},
    {description: 'Hard Engage', value: 'hardEngage'},
    {description: 'Disengage', value: 'disengage'},
    {description: 'Poke', value: 'poke'},
    {description: 'Waveclear', value: 'waveclear'},
    {description: 'Sustain', value: 'sustain'},
    {description: 'Utility', value: 'utility'}
  ];

  currentSelected = {
    allyRole: 'juggernaut',
    enemyRole: 'juggernaut',
    playType: 'aggro'
  };

  constructor(private rulesService: RulesService) { }

  ngOnInit() {
    this.newRule = new FormGroup({
      allyRole: new FormControl(this.currentSelected.allyRole, Validators.required),
      enemyRole: new FormControl(this.currentSelected.enemyRole, Validators.required),
      playType: new FormControl(this.currentSelected.playType, Validators.required),
      advice: new FormControl('', Validators.required),
      ruleName: new FormControl('', Validators.required),
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
    const playStyles = this.newRule.get('playStyles').value;
    const advice = this.newRule.get('advice').value;
    const ruleName = this.newRule.get('ruleName').value;

    const newRule = {
      allyType: allyRole,
      enemyType: enemyRole,
      skillsName: ruleName,
      playType,
      advice,
      hardCC: (playStyles.indexOf('hardCC') > -1) ? true : false,
      hardEngage: (playStyles.indexOf('hardEngage') > -1) ? true : false,
      poke: (playStyles.indexOf('poke') > -1) ? true : false,
      waveclear: (playStyles.indexOf('waveclear') > -1) ? true : false,
      disengage: (playStyles.indexOf('disengage') > -1) ? true : false,
      sustain: (playStyles.indexOf('sustain') > -1) ? true : false,
      utility: (playStyles.indexOf('utility') > -1) ? true : false
    };

    this.rulesService.addEarlyRule(newRule).subscribe(
      resData => {
        console.log(resData);
        this.newRule.reset();
      },
      error => {
        console.log(error);
      }
    );
  }

}
