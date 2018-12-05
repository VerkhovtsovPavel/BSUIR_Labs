import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operator/map';

import { AuthenticationService } from './authentication.service';
import { Sample } from '../models/sample';

@Injectable()
export class SampleService {
    constructor(
        private http: Http,
        private authenticationService: AuthenticationService) {
    }

    addSample(sample: Sample): Observable<Boolean> {
        // add authorization header with jwt token
        let headers = new Headers({ 'Token' : this.authenticationService.token, 'content-type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        // get users from api
        return this.http.post('http://localhost:5467/v1/samples/new', JSON.stringify({ id: Date.now().toString(), xs: sample.xs, ys: sample.ys, es: sample.es, times: sample.times }), options)
            .map((response: Response) => response.json());
    }
}