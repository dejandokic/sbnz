import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewRulesMidLateComponent } from './new-rules-mid-late.component';

describe('NewRulesMidLateComponent', () => {
  let component: NewRulesMidLateComponent;
  let fixture: ComponentFixture<NewRulesMidLateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewRulesMidLateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewRulesMidLateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
