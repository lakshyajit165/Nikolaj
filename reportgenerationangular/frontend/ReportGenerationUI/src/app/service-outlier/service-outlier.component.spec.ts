import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceOutlierComponent } from './service-outlier.component';

describe('ServiceOutlierComponent', () => {
  let component: ServiceOutlierComponent;
  let fixture: ComponentFixture<ServiceOutlierComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceOutlierComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceOutlierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
