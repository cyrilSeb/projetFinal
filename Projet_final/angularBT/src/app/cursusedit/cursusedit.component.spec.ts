import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CursuseditComponent } from './cursusedit.component';

describe('CursuseditComponent', () => {
  let component: CursuseditComponent;
  let fixture: ComponentFixture<CursuseditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CursuseditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CursuseditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
