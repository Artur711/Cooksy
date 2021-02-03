import { TestBed } from '@angular/core/testing';

import { AlwaysAuthGuardGuard } from './always-auth-guard.guard';

describe('AlwaysAuthGuardGuard', () => {
  let guard: AlwaysAuthGuardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AlwaysAuthGuardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
