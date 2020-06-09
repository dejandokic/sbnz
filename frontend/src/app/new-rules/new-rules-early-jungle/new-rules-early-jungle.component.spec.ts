import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewRulesEarlyJungleComponent } from './new-rules-early-jungle.component';

describe('NewRulesEarlyJungleComponent', () => {
  let component: NewRulesEarlyJungleComponent;
  let fixture: ComponentFixture<NewRulesEarlyJungleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewRulesEarlyJungleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewRulesEarlyJungleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
