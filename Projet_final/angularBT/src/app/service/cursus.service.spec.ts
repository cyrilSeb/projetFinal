import { TestBed, inject } from '@angular/core/testing';

import { CursusService } from './cursus.service';

describe('CursusService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CursusService]
    });
  });

  it('should be created', inject([CursusService], (service: CursusService) => {
    expect(service).toBeTruthy();
  }));
});
