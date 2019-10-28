import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketdetailcardComponent } from './ticketdetailcard.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MaterialModule } from '../material/material.module';
import { Router } from '@angular/router';

class RouterStub {
  getCurrentNavigation() {
    return {
       extras: {
          state: {
            id: '5d8ef5f76fd09656f117cf4e',
            description: 'Get Space rocket',
            usermail: 'user13@gmail.com',
            status: 'engaged',
            rating: 0,
            timeCreated: '2019-09-23T00:00:00.000+0000',
            timeResolved: '2019-12-23T00:00:00.000+0000',
            assignMeTime: '2019-09-23T00:00:00.000+0000',
            commandsUsed: ['add'],
            type: 'query',
            solvedBy: 'csr1@gmail.com',
            tags: ['invoice'],
          }
        }
      };
    }
 }

describe('TicketdetailcardComponent', () => {
  let component: TicketdetailcardComponent;
  let fixture: ComponentFixture<TicketdetailcardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ RouterTestingModule, HttpClientTestingModule, MaterialModule ],
      declarations: [ TicketdetailcardComponent ],
      schemas: [
        NO_ERRORS_SCHEMA
      ],
      providers: [ {provide: Router, useClass: RouterStub}]
    })
    .compileComponents();
  }));

  beforeEach(() => {

    fixture = TestBed.createComponent(TicketdetailcardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
