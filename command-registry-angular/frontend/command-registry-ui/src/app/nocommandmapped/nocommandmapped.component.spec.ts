import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NocommandmappedComponent } from './nocommandmapped.component';

describe('NocommandmappedComponent', () => {
  let component: NocommandmappedComponent;
  let fixture: ComponentFixture<NocommandmappedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NocommandmappedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NocommandmappedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
