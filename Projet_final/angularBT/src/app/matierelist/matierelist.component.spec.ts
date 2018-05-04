import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MatierelistComponent } from './matierelist.component';

describe('MatierelistComponent', () => {
  let component: MatierelistComponent;
  let fixture: ComponentFixture<MatierelistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MatierelistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MatierelistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
