import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModuleeditComponent } from './moduleedit.component';

describe('ModuleeditComponent', () => {
  let component: ModuleeditComponent;
  let fixture: ComponentFixture<ModuleeditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModuleeditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModuleeditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
