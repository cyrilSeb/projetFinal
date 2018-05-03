import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditsalleComponent } from './editsalle.component';

describe('EditsalleComponent', () => {
  let component: EditsalleComponent;
  let fixture: ComponentFixture<EditsalleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditsalleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditsalleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
