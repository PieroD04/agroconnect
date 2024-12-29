import { TestBed } from '@angular/core/testing';

import { BreederApiService } from './breeder-api.service';

describe('BreederApiService', () => {
  let service: BreederApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BreederApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
