import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CursuslistComponent } from './cursuslist.component';

describe('CursuslistComponent', () => {
  let component: CursuslistComponent;
  let fixture: ComponentFixture<CursuslistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CursuslistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CursuslistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
