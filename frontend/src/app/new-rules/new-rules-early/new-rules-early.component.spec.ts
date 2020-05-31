import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewRulesEarlyComponent } from './new-rules-early.component';

describe('NewRulesEarlyComponent', () => {
  let component: NewRulesEarlyComponent;
  let fixture: ComponentFixture<NewRulesEarlyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewRulesEarlyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewRulesEarlyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
