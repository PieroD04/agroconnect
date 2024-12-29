import { TestBed } from '@angular/core/testing';

import { AnimalApiService } from './animal-api.service';

describe('AnimalApiService', () => {
  let service: AnimalApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnimalApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
