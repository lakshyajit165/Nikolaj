import { TestBed } from '@angular/core/testing';

import { CommandService } from './command.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ICommand } from '../model/ICommand';

describe('CommandService', () => {
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;
  const testUrl = '/assets/data/testCommand.json';

  beforeEach(() => {
    TestBed.configureTestingModule({
    imports: [HttpClientTestingModule],
    providers: [CommandService]
  });
  // Inject the http service and test controller for each test
    httpClient = TestBed.get(HttpClient);
    httpTestingController = TestBed.get(HttpTestingController);
});

  it('should be created', () => {
    const service: CommandService = TestBed.get(CommandService);
    expect(service).toBeTruthy();
  });

  it('can test HttpClient.get', () => {
    const testData: ICommand = {id: '5d8b485bdb1ccd91cc24bd7c',
    name: 'invoice',
    tags: ['tag1', 'tag2'],
    desc: 'this command can be used to see invoice',
    last_updated_on: '22/01/2019',
    created_on: '21/01/2019',
    created_by: 'ABC',
    usage: 'invoice p1',
    status: 'active',
    parameters: [{
        parameterName: 'p1',
        required: 'required',
        default_value: null,
        description: 'first parameter for invoice command kkfk fjfjdk djjdjksj jfdjnjfjd'
    }]
};

    // Make an HTTP GET request
    httpClient.get<ICommand>(testUrl)
      .subscribe(data =>
        // When observable resolves, result should match test data
        expect(data).toEqual(testData)
      );

    // The following `expectOne()` will match the request's URL.
    // If no requests or multiple requests matched that URL
    // `expectOne()` would throw.
    const req = httpTestingController.expectOne('/assets/data/testCommand.json');

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('GET');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(testData);

    // Finally, assert that there are no outstanding requests.
    httpTestingController.verify();
  });

  it('test for 404 error', () => {
    const emsg = 'deliberate 404 error';

    httpClient.get<ICommand[]>(testUrl).subscribe(
      data => fail('should have failed with the 404 error'),
      (error: HttpErrorResponse) => {
        expect(error.status).toEqual(404, 'status');
        expect(error.error).toEqual(emsg, 'message');
      }
    );

    const req = httpTestingController.expectOne(testUrl);

    // Respond with mock error
    req.flush(emsg, { status: 404, statusText: 'Not Found' });
  });

  afterEach(() => {
    // After every test, assert that there are no more pending requests.
    httpTestingController.verify();
  });

});
