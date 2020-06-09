import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { RulesService } from 'src/app/services/rules.service';

@Component({
  selector: 'app-new-rules-mid-late',
  templateUrl: './new-rules-mid-late.component.html',
  styleUrls: ['./new-rules-mid-late.component.scss']
})
export class NewRulesMidLateComponent implements OnInit {

  champRole = ['juggernaut', 'diver', 'assassin', 'skirmisher', 'marksman', 'burst',
                'battlemage', 'artillery', 'vanguard', 'warden', 'catcher', 'enchanter'];
  teamComposition = ['attack', 'protect', 'catch', 'poke', 'splitPush'];

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
    {description: 'Split push', value: 'splitPush'}
  ];

  currentSelected = {
    allyRole: 'juggernaut',
    teamComposition: 'attack',
  };

  constructor(private rulesService: RulesService) { }

  ngOnInit() {
    this.newRule = new FormGroup({
      allyRole: new FormControl(this.currentSelected.allyRole, Validators.required),
      teamComposition: new FormControl(this.currentSelected.teamComposition, Validators.required),
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

    this.newRule.get('teamComposition').valueChanges.subscribe((value: any) => {
      this.teamComposition.forEach((comp) => {
        if (comp === value) {
          this.currentSelected.teamComposition = comp;
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
    const playStyles = this.newRule.get('playStyles').value;
    const advice = this.newRule.get('advice').value;
    const ruleName = this.newRule.get('ruleName').value;

    const newRule = {
      allyType: allyRole,
      teamComp: teamComposition,
      skillsName: ruleName,
      hardCC: (playStyles.indexOf('hardCC') > -1) ? true : false,
      hardEngage: (playStyles.indexOf('hardEngage') > -1) ? true : false,
      poke: (playStyles.indexOf('poke') > -1) ? true : false,
      waveclear: (playStyles.indexOf('waveclear') > -1) ? true : false,
      disengage: (playStyles.indexOf('disengage') > -1) ? true : false,
      sustain: (playStyles.indexOf('sustain') > -1) ? true : false,
      utility: (playStyles.indexOf('utility') > -1) ? true : false,
      mobility: (playStyles.indexOf('mobility') > -1) ? true : false,
      splitPush: (playStyles.indexOf('splitPush') > -1) ? true : false,
      advice
    };

    console.log(newRule);

    this.rulesService.addTeamCompositionRule(newRule).subscribe(
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
