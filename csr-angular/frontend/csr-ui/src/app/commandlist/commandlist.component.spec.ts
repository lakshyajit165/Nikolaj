import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommandlistComponent } from './commandlist.component';

describe('CommandlistComponent', () => {
  let component: CommandlistComponent;
  let fixture: ComponentFixture<CommandlistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommandlistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommandlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
