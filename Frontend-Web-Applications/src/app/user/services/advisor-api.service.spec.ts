import { TestBed } from '@angular/core/testing';

import { AdvisorApiService } from './advisor-api.service';

describe('AdvisorApiService', () => {
  let service: AdvisorApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdvisorApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
