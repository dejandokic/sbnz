import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewRulesComponent } from './new-rules.component';

describe('NewRulesComponent', () => {
  let component: NewRulesComponent;
  let fixture: ComponentFixture<NewRulesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewRulesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewRulesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
