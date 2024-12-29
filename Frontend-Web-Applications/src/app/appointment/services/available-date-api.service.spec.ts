import { TestBed } from '@angular/core/testing';

import { AvailableDateApiService } from "./available-date-api.service";

describe('AvailabeDateApiService', () => {
  let service: AvailableDateApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AvailableDateApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
