import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MaterialModule } from '../material/material.module';
import { NgxPaginationModule } from 'ngx-pagination';
import { CommandFilterPipe } from '../pipes/command-filter.pipe';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { CommandService } from '../services/command.service';
import { CommandListComponent } from './command-list.component';
import { CommandsComponent } from '../commands/commands.component';

describe('CommandListComponent', () => {
  let component: CommandListComponent;
  let fixture: ComponentFixture<CommandListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommandListComponent, CommandFilterPipe, CommandsComponent ],
      imports: [
        MaterialModule,
        NgxPaginationModule,
        FormsModule,
        HttpClientTestingModule

      ],
      providers: [
        { provide: CommandService }
      ]

    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommandListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });


  // it(`should have 5 commands`, async () => {
  //   expect(component.commands.length).toEqual(5);
  // });

  it('should create CommandListComponent', () => {
    expect(component).toBeTruthy();
  });

});
