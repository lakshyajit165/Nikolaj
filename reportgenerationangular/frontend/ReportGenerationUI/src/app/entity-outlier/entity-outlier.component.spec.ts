import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EntityOutlierComponent } from './entity-outlier.component';

describe('EntityOutlierComponent', () => {
  let component: EntityOutlierComponent;
  let fixture: ComponentFixture<EntityOutlierComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EntityOutlierComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EntityOutlierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
