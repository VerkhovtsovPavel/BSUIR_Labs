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

    samples: Sample[] = [];

    addSample(sample: Sample): Observable<Boolean> {
        // add authorization header with jwt token
        let headers = new Headers({ 'Token' : this.authenticationService.token, 'content-type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        // get users from api
        return this.http.post('http://localhost:5467/v1/samples/new', JSON.stringify({ id: Date.now().toString(), user_id: this.authenticationService.token,  x: sample.xs, y: sample.ys, e: sample.es, times: sample.times }), options)
            .map((response: Response) => response.json());
    }

    getSample(id: String): Observable<Sample> {
         // add authorization header with jwt token
        let headers = new Headers({ 'Token' : this.authenticationService.token, 'content-type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        // get users from api
        return this.http.get('http://localhost:5467/v1/samples/'+id, options)
            .map((response: Response) => response.json());
    }

    getAllSamples(): Observable<Array<Sample>> {
         // add authorization header with jwt token
        let headers = new Headers({ 'Token' : this.authenticationService.token, 'content-type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        // get users from api
        return this.http.get('http://localhost:5467/v1/samples/all', options)
            .map((response: Response) => {
                let samples = response.json();
                this.samples = samples;
                return samples;
                });
    }
}