import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MatiereeditComponent } from './matiereedit.component';

describe('MatiereeditComponent', () => {
  let component: MatiereeditComponent;
  let fixture: ComponentFixture<MatiereeditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MatiereeditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MatiereeditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
