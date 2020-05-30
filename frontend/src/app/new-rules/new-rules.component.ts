import { Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-new-rules',
  templateUrl: './new-rules.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./new-rules.component.scss']
})
export class NewRulesComponent implements OnInit {
  selectedIndex = 0;

  constructor() { }

  ngOnInit() {
  }

  tabChanged(tab: number) {
    this.selectedIndex = tab;
  }

}
