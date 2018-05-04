import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MaterieleditComponent } from './materieledit.component';

describe('MaterieleditComponent', () => {
  let component: MaterieleditComponent;
  let fixture: ComponentFixture<MaterieleditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MaterieleditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MaterieleditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
