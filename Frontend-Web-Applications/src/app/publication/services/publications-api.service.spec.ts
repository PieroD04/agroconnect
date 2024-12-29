import { TestBed } from '@angular/core/testing';

import { PublicationsApiService } from './publications-api.service';

describe('PublicationsApiService', () => {
  let service: PublicationsApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PublicationsApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
