import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OpenticketComponent } from './openticket.component';

describe('OpenticketComponent', () => {
  let component: OpenticketComponent;
  let fixture: ComponentFixture<OpenticketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OpenticketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OpenticketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
