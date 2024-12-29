import { TestBed } from '@angular/core/testing';

import { CageApiService } from './cage-api.service';

describe('CageApiService', () => {
  let service: CageApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CageApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
