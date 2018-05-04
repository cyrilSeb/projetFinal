import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormateureditComponent } from './formateuredit.component';

describe('FormateureditComponent', () => {
  let component: FormateureditComponent;
  let fixture: ComponentFixture<FormateureditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormateureditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormateureditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
